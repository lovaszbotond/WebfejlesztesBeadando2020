package hu.unideb.webdev.service;

import hu.unideb.webdev.exceptions.UnknownFilmException;
import hu.unideb.webdev.exceptions.UnknownLanguageException;
import hu.unideb.webdev.model.Film;

import java.util.Collection;

public interface FilmService {

    Collection<Film> getAllFilms();
    void recordFilm(Film film) throws UnknownLanguageException;
    void deleteFilm(Film film) throws UnknownFilmException, UnknownLanguageException;
    void updateFirstMatch(Film film, Film updated) throws UnknownFilmException, UnknownLanguageException;
    void deleteFilmId(Short filmId) throws UnknownFilmException;
}
