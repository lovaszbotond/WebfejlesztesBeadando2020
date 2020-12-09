package hu.unideb.webdev.service;

import hu.unideb.webdev.dao.FilmDao;
import hu.unideb.webdev.exceptions.UnknownFilmException;
import hu.unideb.webdev.exceptions.UnknownLanguageException;
import hu.unideb.webdev.model.Film;
import hu.unideb.webdev.model.Rating;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;


@ExtendWith(MockitoExtension.class)

public class FilmServiceImplTest {

    @InjectMocks
    private FilmServiceImpl service;

    @Mock
    private FilmDao dao;

    @Test
    public void recordFilmTest() throws UnknownLanguageException{
        Film film = getFilm();

        service.recordFilm(film);

        verify(dao, times(1)).createFilm(film);
    }

    @Test
    public void recordFilmBadLanguage() throws UnknownLanguageException{
        doThrow(UnknownLanguageException.class).when(dao).createFilm(any());

        assertThrows(UnknownLanguageException.class, ()->
        {
            service.recordFilm(getUnknownLanguageException());
        });

    }

    @Test
    public void deleteFilmTest() throws UnknownLanguageException, UnknownFilmException{
        Film film = getFilm();
        service.deleteFilm(film);

        verify(dao, times(1)).deleteFilm(film);
    }

    @Test
    public void deleteFilmBadLanguage() throws UnknownFilmException, UnknownLanguageException{
        doThrow(UnknownLanguageException.class).when(dao).deleteFilm(any());

        assertThrows(UnknownLanguageException.class, ()->
        {
            service.deleteFilm(getUnknownLanguageException());
        });
    }

    @Test
    public void deleteFilmBadFilm() throws UnknownLanguageException, UnknownFilmException{
        doThrow(UnknownLanguageException.class).when(dao).deleteFilm(any());
        assertThrows(UnknownLanguageException.class, ()->
        {
            service.deleteFilm(getUnknownFilmException());
        });
    }

    @Test
    public void updateFilmTest() throws UnknownFilmException, UnknownLanguageException{
        Film film = getFilm();
        Film updatedFilm = getUpdatedFilm();
        service.updateFirstMatch(film,updatedFilm);

        verify(dao, times(1)).updateFirstMatch(film,updatedFilm);
    }

    @Test
    public void updateFilmBadLanguage() throws UnknownLanguageException, UnknownFilmException{
        doThrow(UnknownLanguageException.class).when(dao).updateFirstMatch(any(),any());

        assertThrows(UnknownLanguageException.class, ()->
        {
            service.updateFirstMatch(getFilm(),getUpdatedFilm());
        });
    }

    @Test
    public void updateFilmBadFilm() throws UnknownLanguageException, UnknownFilmException{
        doThrow(UnknownFilmException.class).when(dao).updateFirstMatch(any(),any());

        assertThrows(UnknownFilmException.class, ()->
        {
            service.updateFirstMatch(getFilm(),getUpdatedFilm());
        });
    }

    @Test
    public void readAllFilm(){
        when(dao.readAll()).thenReturn(getDefaultFilm());

        Collection<Film> present = service.getAllFilms();

        assertThat(getDefaultFilm(),is(present));


    }

    private Collection<Film> getDefaultFilm() {
        return Arrays.asList(
                new Film(
                        "Doom1000",
                        "Erdekes",
                        2010,
                        3,
                        2,
                        3.2,
                        (short) 130,
                        2.3,
                        Rating.G,
                        "Behind The Scenes"
                ),
                new Film(
                        "Doom10001",
                        "Erdekes",
                        2011,
                        4,
                        3,
                        3.22,
                        (short) 131,
                        2.32,
                        Rating.G,
                        "Behind The Scenes"
                ),
                new Film(
                        "Doom1000212",
                        "Erdekes12",
                        2015,
                        2,
                        4,
                        3.51,
                        (short) 110,
                        2.3123,
                        Rating.G,
                        "Behind The Scenes"
                )
        );
    }

    private Film getUpdatedFilm() {
        return new Film(
                "Doom1000",
                "Erdekes",
                2010,
                331,
                2,
                3.2,
                (short) 130,
                2.3,
                Rating.G,
                "Behind The Scenes"
        );
    }

    private Film getUnknownFilmException() {
        return new Film(
                "Doom1000",
                "Erdekes",
                2010,
                331,
                2,
                3.2,
                (short) 130,
                2.3,
                Rating.G,
                "Behind The Scenes"
        );
    }

    private Film getUnknownLanguageException() {
        return new Film(
                "Tesztelek",
                "Erdekes",
                2010,
                331,
                2,
                3.2,
                (short) 130,
                2.3,
                Rating.G,
                "Behind The Scenes"
        );
    }


    private Film getFilm(){
        return new Film(
                "Tesztelek",
                "Erdekes",
                2010,
                3,
                2,
                3.2,
                (short) 130,
                2.3,
                Rating.G,
                "Behind The Scenes"
        );
    }

}
