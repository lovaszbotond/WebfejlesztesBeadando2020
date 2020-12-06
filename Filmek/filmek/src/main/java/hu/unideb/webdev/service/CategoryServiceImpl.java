package hu.unideb.webdev.service;

import hu.unideb.webdev.dao.CategoryDao;
import hu.unideb.webdev.exceptions.UnknownCategoryException;
import hu.unideb.webdev.model.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryDao categoryDao;

    @Override
    public Collection<Category> getAllCategories(){
        return categoryDao.readAll();
    }

    @Override
    public void recordCategory(Category category) throws UnknownCategoryException{
        categoryDao.createCategory(category);
    }

    @Override
    public void deleteCategory(Category category) throws UnknownCategoryException{
        categoryDao.deleteCategory(category);
    }

    @Override
    public void updateCategory(Category category, Category updatedCategory) throws UnknownCategoryException{
        categoryDao.updateCategory(category,updatedCategory);
    }

}
