package hu.unideb.webdev.service;

import hu.unideb.webdev.dao.FilmActorDao;
import hu.unideb.webdev.exceptions.UnknownActorException;
import hu.unideb.webdev.exceptions.UnknownFilmException;
import hu.unideb.webdev.model.FilmActor;
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
public class FilmActorServiceImplTest {

    @InjectMocks
    private FilmActorServiceImpl service;

    @Mock
    private FilmActorDao dao;

    @Test
    public void readAllFilmActorTest(){
        when(dao.readAll()).thenReturn(getDefaultFilmActor());

        Collection<FilmActor> present = service.getAllFilmActors();

        assertThat(getDefaultFilmActor(),is(present));
    }

    @Test
    public void recordFilmActorTest() throws UnknownFilmException, UnknownActorException {
        FilmActor filmActor = getFilmActor();

        service.recordFilmActor(filmActor);

        verify(dao, times(1)).createFilmActor(filmActor);
    }

    @Test
    public void recordFilmActorBadFilm() throws UnknownActorException, UnknownFilmException{
        doThrow(UnknownFilmException.class).when(dao).createFilmActor(any());

        assertThrows(UnknownFilmException.class, ()->
        {
            service.recordFilmActor(getUnknownFilmException());
        });
    }

    @Test
    public void recordFilmActorBadActor() throws UnknownFilmException,UnknownActorException{
        doThrow(UnknownActorException.class).when(dao).createFilmActor(any());

        assertThrows(UnknownActorException.class, ()->
        {
            service.recordFilmActor(getUnknownActorException());
        });
    }

    @Test
    public void deleteFilmActorTest() throws UnknownActorException{
        FilmActor filmActor = getFilmActor();

        service.deleteFilmActor(filmActor);

        verify(dao, times(1)).deleteFilmActor(filmActor);
    }

    @Test
    public void updateFilmActorTest() throws UnknownActorException,UnknownFilmException{
        FilmActor filmActor = getFilmActor();
        FilmActor updatedFilmActor = getUpdatedFilmActor();

        service.updateFilmActor(filmActor,updatedFilmActor);

        verify(dao, times(1)).updateFilmActor(filmActor,updatedFilmActor);
    }

    @Test
    public void updateFilmActorBadFilm() throws UnknownFilmException,UnknownActorException{
        doThrow(UnknownFilmException.class).when(dao).updateFilmActor(any(),any());

        assertThrows(UnknownFilmException.class, ()->
        {
            service.updateFilmActor(getFilmActor(),getUpdatedFilmActor());
        });
    }

    @Test
    public void updateFilmActorBadActor() throws UnknownActorException,UnknownFilmException{
        doThrow(UnknownActorException.class).when(dao).updateFilmActor(any(),any());

        assertThrows(UnknownActorException.class, ()->
        {
            service.updateFilmActor(getFilmActor(),getUpdatedFilmActor());
        });
    }

    private FilmActor getUpdatedFilmActor() {
        return new FilmActor(
                "THORA",
                "Doom5"
        );
    }

    private FilmActor getUnknownActorException() {
        return new FilmActor(
                "Steven",
                "Enkicsiponim"
        );
    }

    private FilmActor getUnknownFilmException() {
        return new FilmActor(
                "Steven",
                "Enkicsiponim"
        );
    }

    private FilmActor getFilmActor() {
        return new FilmActor(
                "THORA",
                "Doom5"
        );
    }

    private Collection<FilmActor> getDefaultFilmActor() {
        return Arrays.asList(
                new FilmActor(
                        "Janos",
                        "Doom5"
                ),
                new FilmActor(
                        "Peter",
                        "Enkicsiponim"
                )
        );
    }
}
