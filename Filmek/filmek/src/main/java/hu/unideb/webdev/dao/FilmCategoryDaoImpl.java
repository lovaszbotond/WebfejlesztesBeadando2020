package hu.unideb.webdev.dao;

import hu.unideb.webdev.dao.entity.CategoryEntitiy;
import hu.unideb.webdev.dao.entity.FilmCategoryEntity;
import hu.unideb.webdev.dao.entity.FilmEntity;
import hu.unideb.webdev.dao.repository.CategoryRepository;
import hu.unideb.webdev.dao.repository.FilmCategoryRepository;
import hu.unideb.webdev.dao.repository.FilmRepository;
import hu.unideb.webdev.exceptions.UnknownCategoryException;
import hu.unideb.webdev.exceptions.UnknownFilmException;
import hu.unideb.webdev.model.FilmCategory;
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
public class FilmCategoryDaoImpl implements FilmCategoryDao{

    private final FilmCategoryRepository filmCategoryRepository;
    private final FilmRepository filmRepository;
    private final CategoryRepository categoryRepository;

    //read
    @Override
    public Collection<FilmCategory> readAll() {
        log.info("Read Start:");
        return StreamSupport.stream(filmCategoryRepository.findAll().spliterator(), false).map(entity -> new FilmCategory(
                entity.getKey().getFilmId().getTitle(),
                entity.getKey().getCategoryId().getName()
        )).collect((Collectors.toList()));
    }

    //create
    @Override
    public void createFilmCategory(FilmCategory filmCategory) throws UnknownCategoryException,UnknownFilmException{
        FilmCategoryEntity filmCategoryEntity;

        filmCategoryEntity = FilmCategoryEntity.builder()
                .key( new FilmCategoryEntity.Key(queryFilmTitle(filmCategory.getFilmTitle()), queryCategoryName(filmCategory.getCategoryName())))
                .build();

        log.info("FilmCategoryEntity: {}", filmCategoryEntity);
        try{
           filmCategoryRepository.save(filmCategoryEntity);
        }catch(Exception e)
        {
            log.error(e.getMessage());
        }
    }

    @Override
    public void deleteFilmCategory(FilmCategory filmCategory) throws  UnknownCategoryException, UnknownFilmException{
        Optional<FilmCategoryEntity> filmCategoryEntity = filmCategoryRepository.findByKey_FilmId_TitleAndKey_CategoryId_Name(
                filmCategory.getFilmTitle(), filmCategory.getCategoryName()
        ).stream().filter(entity -> entity.getKey().getFilmId().getTitle().equals(filmCategory.getFilmTitle()) &&
                entity.getKey().getCategoryId().getName().equals(filmCategory.getCategoryName())).findFirst();

        if(!filmCategoryEntity.isPresent())
        {
            throw new UnknownCategoryException("Unkown FilmCategory: " + filmCategory.toString());
        }
        try {
            filmCategoryRepository.delete(filmCategoryEntity.get());
        } catch(Exception e){
            log.error(e.getMessage());
        }
    }

    @Override
    public void updateFilmCategory(FilmCategory filmCategory, FilmCategory updatedFilmCategory) throws UnknownCategoryException, UnknownFilmException {
        Optional<FilmCategoryEntity> filmCategoryEntity = filmCategoryRepository.findByKey_FilmId_TitleAndKey_CategoryId_Name(
               filmCategory.getFilmTitle(),filmCategory.getCategoryName()
        ).stream().filter(entity -> entity.getKey().getFilmId().getTitle().equals(filmCategory.getFilmTitle()) &&
                entity.getKey().getCategoryId().getName().equals(filmCategory.getCategoryName())).findFirst();

        if(!filmCategoryEntity.isPresent())
        {
            throw new UnknownCategoryException("Unknown FilmCategory: " + filmCategory.toString());
        }
        log.info("The original Filmcategory: " + filmCategoryEntity);
        filmCategoryEntity.get().getKey().setFilmId(queryFilmTitle(updatedFilmCategory.getFilmTitle()));
        filmCategoryEntity.get().getKey().setCategoryId(queryCategoryName(updatedFilmCategory.getCategoryName()));
        log.info("The new Filmcategory: "+ filmCategoryEntity.get().getKey());

        try{
            filmCategoryRepository.save(filmCategoryEntity.get());
        }catch(Exception e){
            log.error(e.getMessage());
        }
    }


    protected FilmEntity queryFilmTitle(String title) throws UnknownFilmException{
        Optional<FilmEntity> filmEntity = filmRepository.findByTitle(title).stream()
                .filter(entity -> entity.getTitle().equals(title)).findFirst();

        if(!filmEntity.isPresent())
        {
            throw new UnknownFilmException("Unknown Film Title: " + title);
        }
        return filmEntity.get();
    }

    protected CategoryEntitiy queryCategoryName(String name) throws UnknownCategoryException{
        Optional<CategoryEntitiy> categoryEntitiy = categoryRepository.findByName(name).stream()
                .filter(entity -> entity.getName().equals(name)).findFirst();
        if(!categoryEntitiy.isPresent())
        {
            throw new UnknownCategoryException("Unknown Category Name: " + name);
        }
        return categoryEntitiy.get();
    }
}
