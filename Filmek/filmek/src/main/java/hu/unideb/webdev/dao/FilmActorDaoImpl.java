package hu.unideb.webdev.dao;

import hu.unideb.webdev.dao.entity.ActorEntity;
import hu.unideb.webdev.dao.entity.FilmActorEntity;
import hu.unideb.webdev.dao.entity.FilmEntity;
import hu.unideb.webdev.dao.repository.ActorRepository;
import hu.unideb.webdev.dao.repository.FilmActorRepository;
import hu.unideb.webdev.dao.repository.FilmRepository;
import hu.unideb.webdev.exceptions.UnknownActorException;
import hu.unideb.webdev.exceptions.UnknownFilmException;
import hu.unideb.webdev.model.FilmActor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class FilmActorDaoImpl implements FilmActorDao{

    private final FilmActorRepository filmActorRepository;
    private final FilmRepository filmRepository;
    private final ActorRepository actorRepository;

    @Override
    public Collection<FilmActor> readAll() {
        return StreamSupport.stream(filmActorRepository.findAll().spliterator(), false).map(entity -> new FilmActor(
                entity.getKey().getFilmId().getTitle(),
                entity.getKey().getActorId().getFirstName() + " " +entity.getKey().getActorId().getLastName()
        )).collect(Collectors.toList());
    }

    @Override
    public void createFilmActor(FilmActor filmActor) throws UnknownFilmException, UnknownActorException {
        FilmActorEntity filmActorEntity;

        filmActorEntity = FilmActorEntity.builder()
                .key(new FilmActorEntity.Key(queryFilmTitle(filmActor.getFilmTitle()),queryActorName(filmActor.getActorName())))
                .build();

        try{
            filmActorRepository.save(filmActorEntity);
        }
        catch (Exception e){
            log.error(e.getMessage());
        }
    }
    @Override
    public void deleteFilmActor(FilmActor filmActor) throws UnknownActorException{
    Optional<FilmActorEntity> filmActorEntity = filmActorRepository.findByKey_FilmId_TitleAndKey_ActorId_FirstName(
            filmActor.getFilmTitle(),filmActor.getActorName()
    ).stream().filter(entity -> entity.getKey().getFilmId().getTitle().equals(filmActor.getFilmTitle())
    && entity.getKey().getActorId().getFirstName().equals(filmActor.getActorName())).findFirst();

        if(!filmActorEntity.isPresent()){
            throw new UnknownActorException("Unknown Actor: " + filmActor.toString());
        }
        log.info("FilmActorEntity: {}", filmActorEntity);

        try{
            filmActorRepository.delete(filmActorEntity.get());
        }
        catch(Exception e){
            log.error(e.getMessage());
        }
    }
    @Override
    public void updateFilmActor(FilmActor filmActor, FilmActor updatedFilmActor)throws UnknownActorException, UnknownFilmException{
        Optional<FilmActorEntity> filmActorEntity = filmActorRepository.findByKey_FilmId_TitleAndKey_ActorId_FirstName(
                filmActor.getFilmTitle(),filmActor.getActorName()
        ).stream().filter(entity -> entity.getKey().getFilmId().getTitle().equals(filmActor.getFilmTitle())
                && entity.getKey().getActorId().getFirstName().equals(filmActor.getActorName())).findFirst();

        if(!filmActorEntity.isPresent())
        {
            throw new UnknownActorException("Unknown Actor: " + filmActor.toString());
        }
        log.info("The original Filmcategory: " + filmActorEntity);
        filmActorEntity.get().getKey().setFilmId(queryFilmTitle(updatedFilmActor.getFilmTitle()));
        filmActorEntity.get().getKey().setActorId(queryActorName(updatedFilmActor.getActorName()));
        log.info("The new Filmcategory: "+ filmActorEntity.get().getKey());

        try{
            filmActorRepository.save(filmActorEntity.get());
        }catch(Exception e){
            log.error(e.getMessage());
        }


    }

    protected FilmEntity queryFilmTitle(String title) throws UnknownFilmException{
        Optional<FilmEntity> filmEntity = filmRepository.findByTitle(title).stream()
                .filter(entity -> entity.getTitle().equals(title)).findFirst();

        if(!filmEntity.isPresent())
        {
            throw new UnknownFilmException("Unknown Film Title: " + title);
        }
        return filmEntity.get();
    }

    protected ActorEntity queryActorName(String name) throws UnknownActorException{
        Optional<ActorEntity> actorEntity = actorRepository.findByFirstName(name).stream()
                .filter(entity -> entity.getFirstName().equals(name)).findFirst();

        if(!actorEntity.isPresent())
        {
            throw new UnknownActorException("Unknown Actor: " + name);
        }
        return actorEntity.get();
    }
}
