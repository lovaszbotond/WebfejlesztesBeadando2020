package hu.unideb.webdev.dao.entity;

import com.sun.istack.Nullable;
import hu.unideb.webdev.dao.StringListConverter;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
@Entity
@Table(name = "film", schema = "sakila")
public class FilmEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Short id;

    @Column (name="title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "release_year")
    private int releaseYear;

    @ManyToOne
    @JoinColumn(name="language_id")
    private LanguageEntity language;

    @ManyToOne
    @Nullable
    @JoinColumn( nullable = true,name="original_language_id")
    private LanguageEntity originalLanguage;

    @Column(name = "rental_duration")
    private int rentalDuration;

    @Column(name = "rental_rate")
    private Double rentalRate;

    @Column(name = "length")
    private Short length;

    @Column(name= "replacement_cost")
    private Double replacementCost;

    @Column (name = "rating")
    private String rating;

    //@Convert(converter = StringListConverter.class)
    @Column(name = "special_features")
    private String specialFeatures;

}
