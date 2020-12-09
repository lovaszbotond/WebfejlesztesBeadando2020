package hu.unideb.webdev.service;

import hu.unideb.webdev.dao.CategoryDao;
import hu.unideb.webdev.exceptions.UnknownCategoryException;
import hu.unideb.webdev.model.Category;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;


@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {

    @InjectMocks
    private CategoryServiceImpl service;

     @Mock
     private CategoryDao dao;


     @Test
     public void readAllCategoryTest(){
         when(dao.readAll()).thenReturn(getDefaultCategory());

         Collection<Category> present = service.getAllCategories();

         assertThat(getDefaultCategory(),is(present));
     }

     @Test
     public void recordCategoryTest() throws UnknownCategoryException{
         Category category = getCategory();

         service.recordCategory(category);

         verify(dao, times(1)).createCategory(category);

     }

     @Test
     public void recordCategoryBadCategory() throws UnknownCategoryException{
         doThrow(UnknownCategoryException.class).when(dao).createCategory(any());

         assertThrows(UnknownCategoryException.class, ()->
         {
             service.recordCategory(getUnknownCategoryException());
         });
     }

     @Test
     public void deleteCategoryTest() throws UnknownCategoryException{
         Category category = getCategory();

         service.deleteCategory(category);

         verify(dao, times(1)).deleteCategory(category);

     }

     @Test
     public void deleteCategoryBadCategory() throws UnknownCategoryException{
         doThrow(UnknownCategoryException.class).when(dao).deleteCategory(any());

         assertThrows(UnknownCategoryException.class, ()->
         {
             service.deleteCategory(getUnknownCategoryException());
         });
     }

     @Test
     public void updateCategoryTest() throws UnknownCategoryException{
         Category category = getCategory();
         Category updatedCategory = getUpdatedCategory();
         service.updateCategory(category,updatedCategory);

         verify(dao, times(1)).updateCategory(category,updatedCategory);
     }

     @Test
     public void updateCategoryBadCategory() throws UnknownCategoryException{
         doThrow(UnknownCategoryException.class).when(dao).updateCategory(any(),any());

         assertThrows(UnknownCategoryException.class, ()->
         {
             service.updateCategory(getCategory(),getUpdatedCategory());
         });
     }

    private Category getUpdatedCategory() {
         return new Category(
                 11,
                 "Drama"
         );
    }

    private Category getUnknownCategoryException() {
         return new Category(
                 20,
                 "Nincs"
         );
    }

    private Category getCategory() {
         return new Category(
                 7,
                 "Drama"
         );
    }

    private Collection<Category> getDefaultCategory() {
        return Arrays.asList(
                new Category(
                        12,
                        "Horror"
                ),
                new Category(
                        13,
                        "Vígjáték"
                )
        );
     }
}
