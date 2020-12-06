package hu.unideb.webdev.model;

import lombok.*;

@AllArgsConstructor
@ToString
@Getter
@Builder
@EqualsAndHashCode
public class Category {

    private int categoryId;
    private String name;

}
