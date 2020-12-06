package hu.unideb.webdev.service;

import hu.unideb.webdev.dao.FilmActorDao;
import hu.unideb.webdev.exceptions.UnknownActorException;
import hu.unideb.webdev.exceptions.UnknownFilmException;
import hu.unideb.webdev.model.FilmActor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilmActorServiceImpl implements FilmActorService{
private final FilmActorDao filmActorDao;

    @Override
    public Collection<FilmActor> getAllFilmActors(){
        return filmActorDao.readAll();
    }

    @Override
    public void recordFilmActor(FilmActor filmActor) throws UnknownActorException, UnknownFilmException {
        filmActorDao.createFilmActor(filmActor);
    }

    @Override
    public void deleteFilmActor(FilmActor filmActor) throws UnknownActorException{
        filmActorDao.deleteFilmActor(filmActor);
    }

    @Override
    public void updateFilmActor(FilmActor filmActor,FilmActor updatedFilmActor) throws UnknownActorException, UnknownFilmException {
        filmActorDao.updateFilmActor(filmActor,updatedFilmActor);
    }

}
