package hu.unideb.webdev.model;

import hu.unideb.webdev.dao.entity.LanguageEntity;
import lombok.*;

@AllArgsConstructor
@ToString
@Getter
@Builder
@EqualsAndHashCode

public class Film {

  //  @Setter
  //  private Short id;
    private String title;
    private String description;
    private int releaseYear;
    private int language;
    private int rentalDuration;
    private Double rentalRate;
    private Short length;
    private Double replacementCost;
    private Rating rating;
    private String specialFeatures;

}
