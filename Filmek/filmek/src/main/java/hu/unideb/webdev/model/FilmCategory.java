package hu.unideb.webdev.model;

import lombok.*;

@AllArgsConstructor
@ToString
@Builder
@Getter
@EqualsAndHashCode
public class FilmCategory {

    private String filmTitle;
    private String categoryName;

}
