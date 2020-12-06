package hu.unideb.webdev.dao;

import hu.unideb.webdev.exceptions.UnknownActorException;
import hu.unideb.webdev.exceptions.UnknownFilmException;
import hu.unideb.webdev.model.FilmActor;
import java.util.Collection;

public interface FilmActorDao {
    Collection<FilmActor> readAll();

    void createFilmActor(FilmActor filmActor) throws UnknownActorException, UnknownFilmException;

    void deleteFilmActor(FilmActor filmActor) throws UnknownActorException;

    void updateFilmActor(FilmActor filmActor, FilmActor updatedFilmActor) throws UnknownActorException, UnknownFilmException;
}
