package hu.unideb.webdev.dao.entity;

import hu.unideb.webdev.dao.entity.compositekeys.FilmActorEntityCompositKeys;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
@Entity
//@IdClass(FilmActorEntityCompositKeys.class)
@Table(name = "film_actor", schema = "sakila")
public class FilmActorEntity {

    @EmbeddedId()
    private Key key;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Embeddable
    public static class Key implements java.io.Serializable {

        @ManyToOne
        @JoinColumn(name = "film_id")
        private FilmEntity filmId;

        @ManyToOne
        @JoinColumn(name = "actor_id")
        private ActorEntity actorId;

    }
}
