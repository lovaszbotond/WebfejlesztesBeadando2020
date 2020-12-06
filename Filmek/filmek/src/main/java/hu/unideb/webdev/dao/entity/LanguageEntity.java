package hu.unideb.webdev.dao.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
@Entity
@Table(name = "language", schema = "sakila")
public class LanguageEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   // @GenericGenerator(name="uuid",strategy = "uuid")
    @Column(name="language_id")
    private int languageId;

    @Column(name = "name")
    private String name;
}
