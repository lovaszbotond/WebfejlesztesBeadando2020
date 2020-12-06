package hu.unideb.webdev.dao.repository;

import hu.unideb.webdev.dao.entity.FilmCategoryEntity;
import hu.unideb.webdev.dao.entity.FilmEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface FilmCategoryRepository extends CrudRepository<FilmCategoryEntity, Integer> {

    Collection<FilmCategoryEntity> findByKey_FilmId_TitleAndKey_CategoryId_Name(String title, String name);

}
