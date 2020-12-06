package hu.unideb.webdev.model;

import lombok.*;

@AllArgsConstructor
@ToString
@Getter
@Builder
@EqualsAndHashCode
public class FilmActor {

    private String actorName;
    private String filmTitle;

}
