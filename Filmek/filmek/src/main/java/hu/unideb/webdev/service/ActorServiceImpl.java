package hu.unideb.webdev.service;

import hu.unideb.webdev.dao.ActorDao;
import hu.unideb.webdev.exceptions.UnknownActorException;
import hu.unideb.webdev.model.Actor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService {

    private final ActorDao actorDao;

    @Override
    public Collection<Actor> getAllActors(){
        return actorDao.readAll();
    }

    @Override
    public void recordActor(Actor actor) throws UnknownActorException{
        actorDao.createActor(actor);
    }

    @Override
    public void deleteActor(Actor actor)throws UnknownActorException{
        actorDao.deleteActor(actor);
    }

    @Override
    public void updateActor(Actor actor, Actor updatedActor) throws UnknownActorException{
        actorDao.updateActor(actor, updatedActor);
    }
}
