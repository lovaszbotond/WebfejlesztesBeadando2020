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
public class FilmUpdateDto extends FilmDto {

   // @Setter
   // private Short updatedId;
    private String updatedTitle;
    private String updatedDescription;
    private int updatedReleaseYear;
    private int updatedLanguage;

    private int updatedRentalDuration;
    private Double updatedRentalRate;
    private Short updatedLength;
    private Double updatedReplacementCost;
    private Rating updatedRating;
    private String updatedSpecialFeatures;


}
