package hu.unideb.webdev.exceptions;

import hu.unideb.webdev.model.Film;

import lombok.Data;

@Data
public class UnknownFilmException extends Exception {

    private Film film;

    public UnknownFilmException(Film language){
        this.film = film;
    }


    public UnknownFilmException(String message, Film film){
        super(message);
        this.film = film;
    }

    public UnknownFilmException(String message){
        super(message);
    }
}
