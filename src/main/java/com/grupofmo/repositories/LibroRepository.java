package com.grupofmo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import com.grupofmo.models.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    boolean existsByIsbn(String isbn);
}
