package edu.tseidler.service;

public enum Color {
    GREEN("\u001B[32m"), RED("\u001B[31m"), RESET("\u001B[0m");

    private final String code;

    Color(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
