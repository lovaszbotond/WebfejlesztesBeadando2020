package hu.unideb.webdev.service;

import hu.unideb.webdev.exceptions.UnknownActorException;
import hu.unideb.webdev.exceptions.UnknownFilmException;
import hu.unideb.webdev.model.FilmActor;

import java.util.Collection;

public interface FilmActorService {
    Collection<FilmActor> getAllFilmActors();
    void recordFilmActor(FilmActor filmActor) throws UnknownActorException, UnknownFilmException;
    void deleteFilmActor(FilmActor filmActor) throws UnknownActorException, UnknownFilmException;
    void updateFilmActor(FilmActor filmActor, FilmActor updatedFilmActor) throws UnknownActorException, UnknownFilmException;

}
