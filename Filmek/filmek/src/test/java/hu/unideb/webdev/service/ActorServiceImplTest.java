package hu.unideb.webdev.service;

import hu.unideb.webdev.dao.ActorDao;
import hu.unideb.webdev.exceptions.UnknownActorException;
import hu.unideb.webdev.model.Actor;
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
public class ActorServiceImplTest {

    @InjectMocks
    private ActorServiceImpl service;

    @Mock
    private ActorDao dao;

    @Test
    public void readAllActorTest(){
        when(dao.readAll()).thenReturn(getDefaultActor());

        Collection<Actor> present = service.getAllActors();

        assertThat(getDefaultActor(),is(present));
    }

    @Test
    public void recordActorTest() throws UnknownActorException{
        Actor actor = getActor();

        service.recordActor(actor);

        verify(dao, times(1)).createActor(actor);

    }

    @Test
    public void recordActorBadActor() throws UnknownActorException{
        doThrow(UnknownActorException.class).when(dao).createActor(any());

        assertThrows(UnknownActorException.class, ()->
        {
            service.recordActor(getUnknownActorException());
        });
    }

    @Test
    public void deleteActorTest() throws UnknownActorException{
        Actor actor = getActor();

        service.deleteActor(actor);

        verify(dao, times(1)).deleteActor(actor);
    }

    @Test
    public void deleteActorBadActor() throws UnknownActorException{
        doThrow(UnknownActorException.class).when(dao).deleteActor(any());

        assertThrows(UnknownActorException.class, ()->
        {
            service.deleteActor(getUnknownActorException());
        });
    }

    @Test
    public void updateActorTest() throws UnknownActorException{
        Actor actor = getActor();
        Actor updatedActor = getUpdatedActor();
        service.updateActor(actor,updatedActor);

        verify(dao, times(1)).updateActor(actor,updatedActor);
    }

    @Test
    public void updateActorBadActor() throws UnknownActorException{
        doThrow(UnknownActorException.class).when(dao).updateActor(any(),any());

        assertThrows(UnknownActorException.class, ()->
        {
            service.updateActor(getActor(),getUpdatedActor());
        });
    }

    private Actor getUpdatedActor(){
        return new Actor(
                "Macska",
                "Jancsi"
        );
    }


    private Actor getUnknownActorException() {
        return new Actor(
                "asd",
                "Asd"
        );
    }

    private Actor getActor() {
        return new Actor(
                "Steven",
                "Mihály"
        );
    }

    private Collection<Actor> getDefaultActor() {
        return Arrays.asList(
                new Actor(
                        "Steven",
                        "Hawking"
                        ),
                new Actor(
                        "Bud",
                        "Spencer"
                )
        );
    }


}
