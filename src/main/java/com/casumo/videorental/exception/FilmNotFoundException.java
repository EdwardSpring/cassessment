package com.casumo.videorental.exception;

public class FilmNotFoundException extends RuntimeException {

    public FilmNotFoundException() {
        super("Film not found");
    }

    public FilmNotFoundException(String msg) {
        super(msg);
    }
}
