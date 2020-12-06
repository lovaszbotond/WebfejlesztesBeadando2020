package hu.unideb.webdev.service;

import hu.unideb.webdev.dao.FilmCategoryDao;
import hu.unideb.webdev.exceptions.UnknownCategoryException;
import hu.unideb.webdev.exceptions.UnknownFilmException;
import hu.unideb.webdev.model.FilmCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilmCategoryServiceImpl implements FilmCategoryService{

    private final FilmCategoryDao filmCategoryDao;

    @Override
    public Collection<FilmCategory> getAllFilmCategories(){
        return filmCategoryDao.readAll();
    }

    @Override
    public void recordFilmCategory(FilmCategory filmCategory) throws UnknownCategoryException, UnknownFilmException {
        filmCategoryDao.createFilmCategory(filmCategory);
    }

    @Override
    public void deleteFilmCategory(FilmCategory filmCategory) throws UnknownCategoryException, UnknownFilmException {
        filmCategoryDao.deleteFilmCategory(filmCategory);
    }

    @Override
    public void updateFilmCategory(FilmCategory filmCategory,FilmCategory updatedFilmCategory) throws UnknownCategoryException, UnknownFilmException {
        filmCategoryDao.updateFilmCategory(filmCategory,updatedFilmCategory);
    }

}
