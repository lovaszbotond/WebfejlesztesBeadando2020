package hu.unideb.webdev.controller.dto;

import hu.unideb.webdev.dao.entity.LanguageEntity;
import hu.unideb.webdev.model.Rating;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FilmDto {

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
