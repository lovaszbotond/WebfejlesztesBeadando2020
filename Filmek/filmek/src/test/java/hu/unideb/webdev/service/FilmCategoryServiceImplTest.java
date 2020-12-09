package hu.unideb.webdev.service;

import hu.unideb.webdev.dao.FilmCategoryDao;
import hu.unideb.webdev.exceptions.UnknownCategoryException;
import hu.unideb.webdev.exceptions.UnknownFilmException;
import hu.unideb.webdev.model.FilmCategory;
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
public class FilmCategoryServiceImplTest {

    @InjectMocks
    private FilmCategoryServiceImpl service;

    @Mock
    private FilmCategoryDao dao;

    @Test
    public void readAllFilmCategoryTest(){
        when(dao.readAll()).thenReturn(getDefaultFilmCategory());

        Collection<FilmCategory> present = service.getAllFilmCategories();

        assertThat(getDefaultFilmCategory(),is(present));
    }

    @Test
    public void recordFilmCategoryTest() throws UnknownFilmException, UnknownCategoryException{
        FilmCategory filmCategory = getFilmCategory();

        service.recordFilmCategory(filmCategory);

        verify(dao, times(1)).createFilmCategory(filmCategory);
    }

    @Test
    public void recordFilmCategoryBadFilm() throws UnknownFilmException, UnknownCategoryException{
        doThrow(UnknownFilmException.class).when(dao).createFilmCategory(any());

        assertThrows(UnknownFilmException.class, ()->
        {
            service.recordFilmCategory(getUnknownFilmException());
        });
    }

    @Test
    public void recordFilmCategoryBadCategory() throws UnknownFilmException, UnknownCategoryException{
        doThrow(UnknownCategoryException.class).when(dao).createFilmCategory(any());

        assertThrows(UnknownCategoryException.class, ()->
        {
            service.recordFilmCategory(getUnknownCategoryException());
        });
    }

    @Test
    public void deleteFilmCategoryTest() throws UnknownCategoryException, UnknownFilmException{
        FilmCategory filmCategory = getFilmCategory();

        service.deleteFilmCategory(filmCategory);

        verify(dao, times(1)).deleteFilmCategory(filmCategory);
    }

    @Test
    public void deleteFilmCategoryBadFilm() throws UnknownFilmException, UnknownCategoryException{
        doThrow(UnknownFilmException.class).when(dao).createFilmCategory(any());

        assertThrows(UnknownFilmException.class, ()->
        {
            service.recordFilmCategory(getUnknownFilmException());
        });
    }

    @Test
    public void deleteFilmCategoryBadCategory() throws UnknownCategoryException,UnknownFilmException{
        doThrow(UnknownFilmException.class).when(dao).deleteFilmCategory(any());

        assertThrows(UnknownFilmException.class, ()->
        {
            service.deleteFilmCategory(getUnknownFilmException());
        });
    }

    @Test
    public void updateFilmCategoryTest() throws UnknownFilmException, UnknownCategoryException{
        FilmCategory filmCategory = getFilmCategory();
        FilmCategory updatedFilmCategory = getUpdatedFilmCategory();

        service.updateFilmCategory(filmCategory,updatedFilmCategory);

        verify(dao, times(1)).updateFilmCategory(filmCategory,updatedFilmCategory);

    }

    @Test
    public void updateFilmCategoryBadFilm() throws UnknownCategoryException,UnknownFilmException{
        doThrow(UnknownFilmException.class).when(dao).updateFilmCategory(any(),any());

        assertThrows(UnknownFilmException.class, ()->
        {
            service.updateFilmCategory(getFilmCategory(),getUpdatedFilmCategory());
        });
    }

     @Test
     public void updateFilmCategoryBadCategory() throws UnknownFilmException,UnknownCategoryException{
         doThrow(UnknownCategoryException.class).when(dao).updateFilmCategory(any(),any());

         assertThrows(UnknownCategoryException.class, ()->
         {
             service.updateFilmCategory(getFilmCategory(),getUpdatedFilmCategory());
         });
     }

    private FilmCategory getUpdatedFilmCategory()  {
        return new FilmCategory(
                "Doom5",
                "Horror"
        );
    }

    private FilmCategory getUnknownFilmException() throws UnknownFilmException,UnknownCategoryException {
        return new FilmCategory(
                "asd",
                "Mese"
        );
    }
    private FilmCategory getUnknownCategoryException() throws UnknownFilmException,UnknownCategoryException {
        return new FilmCategory(
                "asd",
                "Mese"
        );
    }

    private FilmCategory getFilmCategory() throws UnknownCategoryException,UnknownFilmException {
        return new FilmCategory(
                "Blitz",
                "Horror"
        );
    }

    private Collection<FilmCategory> getDefaultFilmCategory() {
        return Arrays.asList(
                new FilmCategory(
                        "Macskajaj",
                        "Comedy"
                ),
                new FilmCategory(
                        "Egy",
                        "Kettő"
                )
        );
    }
}
