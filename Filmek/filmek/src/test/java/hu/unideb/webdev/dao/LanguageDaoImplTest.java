package hu.unideb.webdev.dao;

import hu.unideb.webdev.dao.repository.LanguageRepository;
import hu.unideb.webdev.exceptions.UnknownLanguageException;
import hu.unideb.webdev.model.Language;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LanguageDaoImplTest {

    @Spy
    @InjectMocks
    private LanguageDaoImpl dao;

    @Mock
    private LanguageRepository languageRepository;


    @Test
    public void createLanguageTest(){


      //  Language language = getLanguage();

        dao.createLanguage(getLanguage());
        verify(languageRepository,times(1)).save(any());
    }

    private Language getLanguage() {
        return new Language(
                "Russian"
        );
    }
}
