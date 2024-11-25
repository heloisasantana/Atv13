package com.example.atv13.model;

public enum MenuOption {
    LIVRO("Gerenciar Livros"),
    REVISTA("Gerenciar Revistas");

    private final String title;

    MenuOption(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static MenuOption fromTitle(String title) {
        for (MenuOption option : values()) {
            if (option.getTitle().equalsIgnoreCase(title)) {
                return option;
            }
        }
        throw new IllegalArgumentException("Opção de menu desconhecida: " + title);
    }
}
