package hu.unideb.webdev.service;

import hu.unideb.webdev.exceptions.UnknownCategoryException;
import hu.unideb.webdev.exceptions.UnknownFilmException;
import hu.unideb.webdev.model.FilmCategory;

import java.util.Collection;

public interface FilmCategoryService {
    Collection<FilmCategory> getAllFilmCategories();
    void recordFilmCategory(FilmCategory filmCategory) throws UnknownCategoryException, UnknownFilmException;
    void deleteFilmCategory(FilmCategory filmCategory) throws UnknownCategoryException, UnknownFilmException;
    void updateFilmCategory(FilmCategory filmCategory, FilmCategory updatedFilmCategory) throws UnknownCategoryException, UnknownFilmException;
}
