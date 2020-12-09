package hu.unideb.webdev.dao;
import hu.unideb.webdev.dao.repository.ActorRepository;
import hu.unideb.webdev.model.Actor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ActorDaoImplTest {

    @Spy
    @InjectMocks
    private ActorDaoImpl dao;

    @Mock
    private ActorRepository actorRepository;


    @Test
    public void createActorTest(){
        dao.createActor(getActor());
        verify(actorRepository,times(1)).save(any());
    }

    private Actor getActor() {
        return new Actor(
                "Mihály",
                "Medve"
        );
    }

}
