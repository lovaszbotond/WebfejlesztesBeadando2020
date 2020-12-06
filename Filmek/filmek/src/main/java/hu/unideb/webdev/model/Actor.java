package hu.unideb.webdev.model;

import lombok.*;

@AllArgsConstructor
@ToString
@Getter
@Builder
@EqualsAndHashCode
public class Actor {

    private String firstName;
    private String lastName;

}
