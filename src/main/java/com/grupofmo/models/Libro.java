package com.grupofmo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 160)
    private String titulo;

    @Column(nullable = false, length = 120)
    private String autor;

    @Column(nullable = false, unique = true, length = 30)
    private String isbn;

    @Column(nullable = false)
    private Integer anioPublicacion;

    @Column(nullable = false, length = 120)
    private String editorial;

    @Column(nullable = false)
    private boolean disponible = true;

    public Libro() {
    }

    public Libro(Long id, String titulo, String autor, String isbn, Integer anioPublicacion, String editorial, boolean disponible) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.anioPublicacion = anioPublicacion;
        this.editorial = editorial;
        this.disponible = disponible;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public Integer getAnioPublicacion() {
        return anioPublicacion;
    }

    public String getEditorial() {
        return editorial;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setAnioPublicacion(Integer anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
