package hu.unideb.webdev.dao;

import hu.unideb.webdev.exceptions.UnknownLanguageException;
import hu.unideb.webdev.model.Language;
import java.util.Collection;

public interface LanguageDao {
    Collection<Language> readAll();

   void createLanguage(Language language);

   void deleteLanguage(Language language) throws UnknownLanguageException;

   void updateLanguage(Language language, Language updatedLanuage) throws UnknownLanguageException;



}
