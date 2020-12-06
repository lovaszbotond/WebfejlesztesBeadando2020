package hu.unideb.webdev.dao;

import hu.unideb.webdev.exceptions.UnknownCategoryException;
import hu.unideb.webdev.exceptions.UnknownFilmException;
import hu.unideb.webdev.model.FilmCategory;
import java.util.Collection;

public interface FilmCategoryDao {
    Collection<FilmCategory> readAll();

    void createFilmCategory(FilmCategory filmCategory) throws UnknownCategoryException, UnknownFilmException;

    void deleteFilmCategory(FilmCategory filmCategory) throws UnknownFilmException, UnknownCategoryException;

    void updateFilmCategory(FilmCategory filmCategory, FilmCategory updatedFilmCategory) throws UnknownFilmException, UnknownCategoryException;
}
