package com.grupofmo.services;




import org.springframework.stereotype.Service;

import com.grupofmo.dto.LibroRequest;
import com.grupofmo.models.Libro;
import com.grupofmo.repositories.LibroRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LibroService {

    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public List<Libro> listar() {
        return libroRepository.findAll();
    }

    public Libro obtenerPorId(Long id) {
        return libroRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Libro no encontrado con id: " + id));
    }

    public Libro crear(LibroRequest request) {
        if (libroRepository.existsByIsbn(request.isbn())) {
            throw new IllegalArgumentException("Ya existe un libro con ese ISBN");
        }

        Libro libro = new Libro();
        libro.setTitulo(request.titulo());
        libro.setAutor(request.autor());
        libro.setIsbn(request.isbn());
        libro.setAnioPublicacion(request.anioPublicacion());
        libro.setEditorial(request.editorial());
        libro.setDisponible(request.disponible() == null || request.disponible());

        return libroRepository.save(libro);
    }

    public Libro actualizar(Long id, LibroRequest request) {
        Libro libro = obtenerPorId(id);

        if (!libro.getIsbn().equals(request.isbn()) && libroRepository.existsByIsbn(request.isbn())) {
            throw new IllegalArgumentException("Ya existe otro libro con ese ISBN");
        }

        libro.setTitulo(request.titulo());
        libro.setAutor(request.autor());
        libro.setIsbn(request.isbn());
        libro.setAnioPublicacion(request.anioPublicacion());
        libro.setEditorial(request.editorial());

        if (request.disponible() != null) {
            libro.setDisponible(request.disponible());
        }

        return libroRepository.save(libro);
    }

    public void eliminar(Long id) {
        Libro libro = obtenerPorId(id);
        libroRepository.delete(libro);
    }
}
