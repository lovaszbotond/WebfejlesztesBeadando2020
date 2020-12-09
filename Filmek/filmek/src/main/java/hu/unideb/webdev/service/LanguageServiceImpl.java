package hu.unideb.webdev.service;

import hu.unideb.webdev.dao.LanguageDao;
import hu.unideb.webdev.exceptions.UnknownLanguageException;
import hu.unideb.webdev.model.Language;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    private final LanguageDao languageDao;

    @Override
    public Collection<Language> getAllLanguages(){
        return languageDao.readAll();
    }

    @Override
    public void recordLanguage(Language language){
        languageDao.createLanguage(language);
    }

    @Override
    public void deleteLanguage(Language language) throws UnknownLanguageException{
        languageDao.deleteLanguage(language);
    }

    @Override
    public void updateLanguage(Language language, Language updatedLanguage) throws UnknownLanguageException{
        languageDao.updateLanguage(language,updatedLanguage);
    }
}
