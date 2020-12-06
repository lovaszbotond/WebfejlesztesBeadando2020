package hu.unideb.webdev.service;

import hu.unideb.webdev.exceptions.UnknownCategoryException;
import hu.unideb.webdev.model.Category;

import java.util.Collection;

public interface CategoryService {

    Collection<Category> getAllCategories();
    void recordCategory(Category category) throws UnknownCategoryException;
    void deleteCategory(Category category) throws UnknownCategoryException;
    void updateCategory(Category category, Category updatedCategory) throws UnknownCategoryException;

    }

