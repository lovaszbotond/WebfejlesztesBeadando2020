package hu.unideb.webdev.service;

import hu.unideb.webdev.exceptions.UnknownLanguageException;
import hu.unideb.webdev.model.Language;

import java.util.Collection;

public interface LanguageService {
    Collection<Language> getAllLanguages();
    void recordLanguage(Language language) throws UnknownLanguageException;
    void deleteLanguage(Language language) throws UnknownLanguageException;
    void updateLanguage(Language language, Language updatedLanguage) throws UnknownLanguageException;
}
