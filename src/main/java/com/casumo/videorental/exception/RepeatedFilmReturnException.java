package com.casumo.videorental.exception;

public class RepeatedFilmReturnException extends RuntimeException {

    public RepeatedFilmReturnException() {
        super("Film has already been returned");
    }

    public RepeatedFilmReturnException(String filmName) {
        super(filmName.concat(" has already been returned"));
    }
}
