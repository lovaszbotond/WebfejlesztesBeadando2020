package hu.unideb.webdev.dao;
import hu.unideb.webdev.dao.entity.LanguageEntity;
import hu.unideb.webdev.dao.repository.FilmRepository;
import hu.unideb.webdev.exceptions.UnknownLanguageException;
import hu.unideb.webdev.model.Film;
import hu.unideb.webdev.model.Rating;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class FilmDaoImplTest {

    @Spy
    @InjectMocks
    private FilmDaoImpl dao;

    @Mock
    private FilmRepository filmRepository;

    @Test
    public void createFilmTest() throws UnknownLanguageException{

        doReturn(LanguageEntity.builder().languageId(2).build())
                .when(dao).queryLanguage(anyInt());


        dao.createFilm(getFilm());
        verify(filmRepository,times(1)).save(any());
    }


    private Film getFilm() {
        return new Film(
                "Tesztelek",
                "Erdekes",
                2010,
                2,
                2,
                3.2,
                (short) 130,
                2.3,
                Rating.G,
                "Behind The Scenes"
        );
    }
}
