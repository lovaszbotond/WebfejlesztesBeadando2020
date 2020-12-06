package hu.unideb.webdev.dao.repository;

import hu.unideb.webdev.dao.entity.LanguageEntity;
import hu.unideb.webdev.model.Language;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.UUID;


public interface LanguageRepository extends CrudRepository<LanguageEntity, Integer> {
    Collection<LanguageEntity> findByLanguageId(int LanguageId);
    Collection<LanguageEntity> findByName(String name);
}
