package hu.unideb.webdev.dao;

import hu.unideb.webdev.dao.entity.ActorEntity;
import hu.unideb.webdev.dao.repository.ActorRepository;
import hu.unideb.webdev.exceptions.UnknownActorException;
import hu.unideb.webdev.model.Actor;
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
public class ActorDaoImpl implements ActorDao {

    private final ActorRepository actorRepository;

    //read
    @Override
    public Collection<Actor> readAll() {
        log.info("Read Start:");
        return StreamSupport.stream(actorRepository.findAll().spliterator(),false).map(entity -> new Actor(
                entity.getFirstName(),
                entity.getLastName()
        )).collect(Collectors.toList());

    }
    //create
    @Override
    public void createActor(Actor actor){
        ActorEntity actorEntity;

        actorEntity = ActorEntity.builder()
                .firstName(actor.getFirstName())
                .lastName(actor.getLastName())
                .build();
        log.info("ActorEntity: {}", actorEntity);
        try{
            actorRepository.save(actorEntity);
        }catch(Exception e)
        {
            log.error(e.getMessage());
        }
    }
    //delete
    @Override
    public void deleteActor(Actor actor) throws UnknownActorException {
        Optional<ActorEntity> actorEntity = actorRepository.findByFirstNameAndLastName(actor.getFirstName(),actor.getLastName())
        .stream().filter(entity -> entity.getFirstName().equals(actor.getFirstName()) && entity.getLastName().equals(actor.getLastName()))
        .findFirst();
        if(!actorEntity.isPresent())
        {
            throw new UnknownActorException("Unknown Actor: " + actorEntity.toString());
        }
        try{
            actorRepository.delete(actorEntity.get());
        }catch(Exception e)
        {
            log.error(e.getMessage());
        }
    }

    //update
    @Override
    public void updateActor(Actor actor, Actor updatedActor) throws UnknownActorException {
        Optional<ActorEntity> actorEntity = actorRepository.findByFirstNameAndLastName(actor.getFirstName(),actor.getLastName())
                .stream().filter(entity -> entity.getFirstName().equals(actor.getFirstName()) && entity.getLastName().equals(actor.getLastName()))
                .findFirst();
        if(!actorEntity.isPresent())
        {
            throw new UnknownActorException("Unkonwn Actor: " + actor);
        }

        log.info("The original Actor: " + actorEntity);
        actorEntity.get().setFirstName(updatedActor.getFirstName());
        actorEntity.get().setLastName(updatedActor.getLastName());
        log.info("The new Actor is: " + actorEntity.toString());

        try{
            actorRepository.save(actorEntity.get());
        }catch(Exception e){
            log.error(e.getMessage());
        }
    }


}
