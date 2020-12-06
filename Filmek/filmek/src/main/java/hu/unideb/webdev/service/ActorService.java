package hu.unideb.webdev.service;

import hu.unideb.webdev.exceptions.UnknownActorException;
import hu.unideb.webdev.model.Actor;

import java.util.Collection;

public interface ActorService {
    Collection<Actor> getAllActors();
    void recordActor(Actor actor) throws UnknownActorException;
    void deleteActor(Actor actor) throws UnknownActorException;
    void updateActor(Actor actor, Actor updatedActor) throws UnknownActorException;
}
