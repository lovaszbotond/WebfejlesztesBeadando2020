package hu.unideb.webdev.dao;

import hu.unideb.webdev.dao.entity.CategoryEntitiy;
import hu.unideb.webdev.dao.repository.CategoryRepository;
import hu.unideb.webdev.exceptions.UnknownCategoryException;
import hu.unideb.webdev.model.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryDaoImpl implements CategoryDao {

    private final CategoryRepository categoryRepository;

    @Override
    public Collection<Category> readAll() {
        log.info("Read Start:");
        return StreamSupport.stream(categoryRepository.findAll().spliterator(), false).map(entity -> new Category(
                entity.getCategoryId(),
                entity.getName()
        )).collect((Collectors.toList()));
    }

    //create
    @Override
    public void createCategory(Category category){
        CategoryEntitiy categoryEntitiy;

       categoryEntitiy = CategoryEntitiy.builder()
               .categoryId(category.getCategoryId())
               .name(category.getName())
                .build();

        log.info("CategoryEntity: {}", categoryEntitiy);
        try{
            categoryRepository.save(categoryEntitiy);
        }catch(Exception e){
            log.error(e.getMessage());
        }
    }

    //delete
    @Override
    public void deleteCategory(Category category) throws UnknownCategoryException{
        Optional<CategoryEntitiy> categoryEntitiy = categoryRepository.findByCategoryIdAndName(category.getCategoryId(),category.getName())
                .stream().filter(entity -> entity.getCategoryId() == category.getCategoryId() && entity.getName().equals(category.getName()))
                .findFirst();
        if(!categoryEntitiy.isPresent())
        {
            throw new UnknownCategoryException("Unknown Category: " + categoryEntitiy.toString());
        }
        try{
            categoryRepository.delete(categoryEntitiy.get());
        }catch(Exception e)
        {
            log.error(e.getMessage());
        }
    }

    //update
    @Override
    public void updateCategory(Category category, Category updatedCategory) throws UnknownCategoryException {
        Optional<CategoryEntitiy> categoryEntitiy = categoryRepository.findByCategoryIdAndName(category.getCategoryId(),category.getName())
                .stream().filter(entity -> entity.getCategoryId() == category.getCategoryId() && entity.getName().equals(category.getName()))
                .findFirst();

        if(!categoryEntitiy.isPresent())
        {
            throw new UnknownCategoryException("Unkonwn Category: " + category);
        }
        log.info("The original Category: " + categoryEntitiy.toString());
        categoryEntitiy.get().setCategoryId(updatedCategory.getCategoryId());
        categoryEntitiy.get().setName(updatedCategory.getName());
        log.info("The new Category is: " + categoryEntitiy.toString());
        try{
            categoryRepository.save(categoryEntitiy.get());
        }catch(Exception e){
            log.error(e.getMessage());
        }
    }

}
