package com.example.atv13.model;

public class Livro extends Exemplar {
    private int numeroDePaginas;

    public Livro(String titulo, String autor, int numeroDePaginas) {
        super(titulo, autor);
        this.numeroDePaginas = numeroDePaginas;
    }

    public int getNumeroDePaginas() {
        return numeroDePaginas;
    }

    public void setNumeroDePaginas(int numeroDePaginas) {
        this.numeroDePaginas = numeroDePaginas;
    }

    @Override
    public String toString() {
        return super.toString() + " | Páginas: " + numeroDePaginas;
    }
}

