package hu.unideb.webdev.dao.repository;

import hu.unideb.webdev.dao.entity.CategoryEntitiy;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface CategoryRepository extends CrudRepository<CategoryEntitiy, Integer> {

Collection<CategoryEntitiy> findByName (String name);

Collection<CategoryEntitiy> findByCategoryIdAndName(int categoryId, String categoryName);

}
