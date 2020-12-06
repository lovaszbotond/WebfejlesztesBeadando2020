package hu.unideb.webdev.exceptions;

import hu.unideb.webdev.model.Actor;
import lombok.Data;

@Data
public class UnknownActorException extends Exception {

    private Actor actor;

    public UnknownActorException(Actor actor){
        this.actor=actor;
    }


    public UnknownActorException(String message, Actor actor){
        super(message);
        this.actor=actor;
    }

    public UnknownActorException(String message){
        super(message);
    }
}

