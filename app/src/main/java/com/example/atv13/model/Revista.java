package com.example.atv13.model;

public class Revista extends Exemplar {
    private int edicao;

    public Revista(String titulo, String autor, int edicao) {
        super(titulo, autor);
        this.edicao = edicao;
    }

    public int getEdicao() {
        return edicao;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }

    @Override
    public String toString() {
        return super.toString() + " | Edição: " + edicao;
    }
}
