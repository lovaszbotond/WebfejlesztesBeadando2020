package hu.unideb.webdev.dao.repository;

import hu.unideb.webdev.dao.entity.FilmActorEntity;
import hu.unideb.webdev.dao.entity.FilmCategoryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface FilmActorRepository extends CrudRepository<FilmActorEntity, Integer> {
    Collection<FilmActorEntity> findByKey_FilmId_TitleAndKey_ActorId_FirstName(String title, String name);
}