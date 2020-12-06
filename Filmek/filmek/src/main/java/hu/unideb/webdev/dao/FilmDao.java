package hu.unideb.webdev.dao;

import hu.unideb.webdev.exceptions.UnknownFilmException;
import hu.unideb.webdev.exceptions.UnknownLanguageException;
import hu.unideb.webdev.model.Film;

import java.util.Collection;

public interface FilmDao {
    Collection<Film> readAll();

    void createFilm(Film film) throws UnknownLanguageException;

    void deleteFilm(Film film) throws UnknownLanguageException, UnknownFilmException;

    void deleteFilmById(Short filmId) throws UnknownFilmException;

    void updateFirstMatch(Film film, Film updated) throws UnknownFilmException, UnknownLanguageException;
}
