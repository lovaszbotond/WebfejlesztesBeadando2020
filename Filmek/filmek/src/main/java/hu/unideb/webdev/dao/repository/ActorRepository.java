package hu.unideb.webdev.dao.repository;

import hu.unideb.webdev.dao.entity.ActorEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ActorRepository extends CrudRepository<ActorEntity, Integer> {
    Collection<ActorEntity> findByFirstNameAndLastName(String firstName, String lastName);
    Collection<ActorEntity> findByFirstName(String name);
}