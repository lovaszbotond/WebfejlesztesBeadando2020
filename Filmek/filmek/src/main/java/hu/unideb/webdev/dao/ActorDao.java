package hu.unideb.webdev.dao;

import hu.unideb.webdev.exceptions.UnknownActorException;
import hu.unideb.webdev.model.Actor;

import java.util.Collection;

public interface ActorDao {
    Collection<Actor> readAll();

    void createActor(Actor actor) throws UnknownActorException;

    void deleteActor(Actor actor) throws UnknownActorException;

    void updateActor(Actor actor, Actor updatedActor) throws UnknownActorException;
}
