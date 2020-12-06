package hu.unideb.webdev.dao.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
@Entity
@Table(name = "actor", schema = "sakila")
public class ActorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="actor_id")
    private Short actorId;

    @Column(name="first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
}
