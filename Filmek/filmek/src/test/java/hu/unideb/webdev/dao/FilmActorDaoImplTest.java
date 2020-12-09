package hu.unideb.webdev.dao;
import hu.unideb.webdev.dao.entity.ActorEntity;
import hu.unideb.webdev.dao.entity.FilmEntity;

import hu.unideb.webdev.dao.repository.FilmActorRepository;
import hu.unideb.webdev.exceptions.UnknownActorException;
import hu.unideb.webdev.exceptions.UnknownFilmException;
import hu.unideb.webdev.model.FilmActor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class FilmActorDaoImplTest {
    @Spy
    @InjectMocks
    private FilmActorDaoImpl dao;

    @Mock
    private FilmActorRepository filmActorRepository;


    @Test
    public void createFilmActorTest() throws UnknownActorException, UnknownFilmException {

    doReturn(ActorEntity.builder().firstName("").build())
            .when(dao).queryActorName(anyString());
    doReturn(FilmEntity.builder().title("").build())
            .when(dao).queryFilmTitle(anyString());

        dao.createFilmActor(getFilmActor());
        verify(filmActorRepository,times(1)).save(any());
    }

    private FilmActor getFilmActor() {
        return new FilmActor(
                "Bud",
                "Doom5"
        );
    }


}
