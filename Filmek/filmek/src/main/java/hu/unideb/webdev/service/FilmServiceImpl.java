package hu.unideb.webdev.service;

import hu.unideb.webdev.dao.FilmDao;
import hu.unideb.webdev.exceptions.UnknownFilmException;
import hu.unideb.webdev.exceptions.UnknownLanguageException;
import hu.unideb.webdev.model.Film;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {

    private final FilmDao filmDao;

    @Override
    public Collection<Film> getAllFilms()
    {
        return filmDao.readAll();
    }

    @Override
    public void recordFilm(Film film) throws UnknownLanguageException {
        filmDao.createFilm(film);
    }

    @Override
    public void deleteFilm(Film film) throws UnknownLanguageException, UnknownFilmException {
         filmDao.deleteFilm(film);
    }

    @Override
    public void deleteFilmId(Short filmId) throws UnknownFilmException{
        filmDao.deleteFilmById(filmId);
    }

    @Override
    public void updateFirstMatch(Film film,Film updated) throws UnknownFilmException, UnknownLanguageException {
        filmDao.updateFirstMatch(film,updated);
    }
}
