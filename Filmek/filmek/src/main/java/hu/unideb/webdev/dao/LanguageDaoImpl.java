package hu.unideb.webdev.dao;

import hu.unideb.webdev.dao.entity.LanguageEntity;
import hu.unideb.webdev.dao.repository.LanguageRepository;
import hu.unideb.webdev.exceptions.UnknownLanguageException;
import hu.unideb.webdev.model.Language;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class LanguageDaoImpl implements LanguageDao {

    private final LanguageRepository languageRepository;

    //read
    @Override
    public Collection<Language> readAll() {
        log.info("Read Start:");
        return StreamSupport.stream(languageRepository.findAll().spliterator(), false).map(entity -> new Language(
                entity.getName()
        )).collect((Collectors.toList()));
    }

    //create
    @Override
    public void createLanguage(Language language){
        LanguageEntity languageEntity;

        languageEntity = LanguageEntity.builder()
                .name(language.getName())
                .build();

        log.info("LanguageEntity: {}", languageEntity);
        try{
            languageRepository.save(languageEntity);
        }catch(Exception e){
            log.error(e.getMessage());
        }
    }

    //delete
    @Override
    public void deleteLanguage(Language language) throws UnknownLanguageException{
        Optional<LanguageEntity> languageEntity = languageRepository.findByName(language.getName())
                .stream().filter(entity -> entity.getName().equals(language.getName())).findFirst();

        if(!languageEntity.isPresent())
        {
            throw new UnknownLanguageException("Unkown Language: " + language.getName());
        }

        log.info("LangueEntity : {}", languageEntity);
        try{
            languageRepository.delete(languageEntity.get());
        }catch(Exception e){
            log.error(e.getMessage());
        }
    }


    //update
    @Override
    public void updateLanguage(Language language, Language updatedLanguage) throws UnknownLanguageException{
        Optional<LanguageEntity> languageEntity = languageRepository.findByName(language.getName())
                .stream().filter(entity -> entity.getName().equals(language.getName())).findFirst();
    if(!languageEntity.isPresent())
    {
        throw new UnknownLanguageException("Unkonwn Language: " + language.getName());
    }

        log.info("The original Language: " + languageEntity);
        languageEntity.get().setName(updatedLanguage.getName());
        log.info("The new Language is: " + languageEntity);

        try{
            languageRepository.save(languageEntity.get());
        }catch(Exception e){
            log.error(e.getMessage());
        }
    }

}
