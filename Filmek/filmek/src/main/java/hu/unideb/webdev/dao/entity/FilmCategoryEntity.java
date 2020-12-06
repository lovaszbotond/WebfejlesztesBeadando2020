package hu.unideb.webdev.dao.entity;

import hu.unideb.webdev.dao.entity.compositekeys.FilmActorEntityCompositKeys;
import hu.unideb.webdev.dao.entity.compositekeys.FilmCategoryEntityCompositeKeys;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
@Entity
//@IdClass(FilmCategoryEntityCompositeKeys.class)
@Table(name = "film_category", schema = "sakila")
public class FilmCategoryEntity {

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
        @JoinColumn(name = "category_id")
        private CategoryEntitiy categoryId;

    }
}
