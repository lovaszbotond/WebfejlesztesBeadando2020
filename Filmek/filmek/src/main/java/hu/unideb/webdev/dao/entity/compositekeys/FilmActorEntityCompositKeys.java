package hu.unideb.webdev.dao.entity.compositekeys;

import hu.unideb.webdev.dao.entity.ActorEntity;
import hu.unideb.webdev.dao.entity.FilmEntity;

import java.io.Serializable;

public class FilmActorEntityCompositKeys implements Serializable {

    private ActorEntity actorId;
    private FilmEntity filmId;
}
