package hu.unideb.webdev.service;


import hu.unideb.webdev.dao.LanguageDao;
import hu.unideb.webdev.exceptions.UnknownLanguageException;
import hu.unideb.webdev.model.Language;
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
public class LanguageServiceImplTest {

    @InjectMocks
    private LanguageServiceImpl service;
    @Mock
    private LanguageDao dao;

    @Test
    public void readAllLanguageTest(){
        when(dao.readAll()).thenReturn(getDefaultLanguage());

        Collection<Language> present = service.getAllLanguages();

        assertThat(getDefaultLanguage(),is(present));
    }

    @Test
    public void recordLanguageTest(){
        Language language = getLanguage();

        service.recordLanguage(language);

        verify(dao, times(1)).createLanguage(language);
    }

    @Test
    public void deleteLanguageTest() throws UnknownLanguageException{
        Language language  = getLanguage();
        service.deleteLanguage(language);

        verify(dao, times(1)).deleteLanguage(language);
    }

    @Test
    public void deleteLanguageBadLanguage() throws UnknownLanguageException{
        doThrow(UnknownLanguageException.class).when(dao).deleteLanguage(any());

        assertThrows(UnknownLanguageException.class, ()->
        {
            service.deleteLanguage(getUnknownLanguageException());
        });
    }

    @Test
    public void updateLanguageTest() throws UnknownLanguageException{
        Language language = getLanguage();
        Language updatedLanguage = getUpdatedLanguage();

        service.updateLanguage(getLanguage(),getUpdatedLanguage());

        verify(dao, times(1)).updateLanguage(language,updatedLanguage);

    }

    @Test
    public void updateLanguageBadLanguage() throws UnknownLanguageException{
        doThrow(UnknownLanguageException.class).when(dao).updateLanguage(any(),any());

        assertThrows(UnknownLanguageException.class, ()->
        {
            service.updateLanguage(getLanguage(),getUpdatedLanguage());
        });
    }

    private Language getUpdatedLanguage() {
        return new Language(
                "English"
        );
    }

    private Language getUnknownLanguageException() {
        return new Language(
                "Russian"
        );
    }

    private Language getLanguage() {
        return new Language(
                "English"
        );
    }


    private Collection<Language> getDefaultLanguage() {
        return Arrays.asList(
                new Language(
                        "English"
                ),
                new Language(
                        "German"
                ),
                new Language(
                        "Chinese"
                )
        );
    }
}
