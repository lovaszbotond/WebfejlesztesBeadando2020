package hu.unideb.webdev.dao;

import hu.unideb.webdev.exceptions.UnknownCategoryException;
import hu.unideb.webdev.model.Category;
import java.util.Collection;

public interface CategoryDao {
    Collection<Category> readAll();

    void createCategory(Category category) throws UnknownCategoryException;

    void deleteCategory(Category category) throws  UnknownCategoryException;

    void updateCategory(Category category,Category updatedCategory) throws UnknownCategoryException;
}
