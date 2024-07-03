package com.casumo.videorental.exception;

public class FilmOutOfStockException extends RuntimeException {

    public FilmOutOfStockException() {
        super("Film is out of stock");
    }

    public FilmOutOfStockException(String filmName) {
        super(filmName.concat(" is out of stock"));
    }
}
