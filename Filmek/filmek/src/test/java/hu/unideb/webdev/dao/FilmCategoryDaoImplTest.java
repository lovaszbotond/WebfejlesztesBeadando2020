package hu.unideb.webdev.dao;

import hu.unideb.webdev.dao.entity.ActorEntity;
import hu.unideb.webdev.dao.entity.CategoryEntitiy;
import hu.unideb.webdev.dao.entity.FilmEntity;
import hu.unideb.webdev.dao.repository.FilmActorRepository;
import hu.unideb.webdev.dao.repository.FilmCategoryRepository;
import hu.unideb.webdev.exceptions.UnknownCategoryException;
import hu.unideb.webdev.exceptions.UnknownFilmException;
import hu.unideb.webdev.model.FilmCategory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class FilmCategoryDaoImplTest {

    @Spy
    @InjectMocks
    private FilmCategoryDaoImpl dao;

    @Mock
    private FilmCategoryRepository filmCategoryRepository;

    @Test
    public void createFilmCategoryTest() throws UnknownFilmException, UnknownCategoryException {

        doReturn(FilmEntity.builder().title("").build()).when(dao).queryFilmTitle(anyString());

        doReturn(CategoryEntitiy.builder().name("").build()).when(dao).queryCategoryName(anyString());

        dao.createFilmCategory(getFilmCategory());
        verify(filmCategoryRepository,times(1)).save(any());
    }

    private FilmCategory getFilmCategory() {
    return new FilmCategory(
            "Enkicsiponim",
            "Horror"
    );
    }


}
