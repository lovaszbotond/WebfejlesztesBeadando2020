package hu.unideb.webdev.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FilmCategoryUpdateDto extends FilmCategoryDto {

    private String updatedFilmTitle;
    private String updatedCategoryName;
}
