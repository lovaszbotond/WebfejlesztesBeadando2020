package hu.unideb.webdev.dao;


import hu.unideb.webdev.dao.entity.*;
import hu.unideb.webdev.dao.repository.FilmRepository;
import hu.unideb.webdev.dao.repository.LanguageRepository;
import hu.unideb.webdev.exceptions.UnknownFilmException;
import hu.unideb.webdev.exceptions.UnknownLanguageException;
import hu.unideb.webdev.model.Film;
import hu.unideb.webdev.model.Rating;
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
public class FilmDaoImpl implements FilmDao{

    private final FilmRepository filmRepository;
    private final LanguageRepository languageRepository;

    //read teljes
    @Override
    public Collection<Film> readAll() {
        log.info("Read Start:");
            return StreamSupport.stream(filmRepository.findAll().spliterator(), false).map(entity ->
                    {
                String entityRating = entity.getRating();
                Rating rating = null;

                switch(entityRating){
                    case "PG":
                        rating = Rating.PG;
                        break;
                    case "G":
                        rating = Rating.G;
                        break;
                    case "PG-13":
                        rating = Rating.PG13;
                        break;
                    case "R":
                        rating = Rating.R;
                        break;
                    case "NC-17":
                        rating = Rating.NC17;
                        break;
                    default:
                        System.out.println("nincs");
                }
                return new Film(
                      //  entity.getId(),
                        entity.getTitle(),
                        entity.getDescription(),
                        entity.getReleaseYear(),
                        entity.getLanguage().getLanguageId(),

                        entity.getRentalDuration(),
                        entity.getRentalRate(),
                        entity.getLength(),
                        entity.getReplacementCost(),
                        rating,
                        entity.getSpecialFeatures());
                    }).collect(Collectors.toList());
        }


        //create teljes
        @Override
        public void createFilm(Film film) throws UnknownLanguageException{
        FilmEntity filmEntity;

        filmEntity = FilmEntity.builder()
                .title(film.getTitle())
                .description(film.getDescription())
                .releaseYear(film.getReleaseYear())
                .language(queryLanguage(film.getLanguage()))

                .rentalDuration(film.getRentalDuration())
                .rentalRate(film.getRentalRate())
                .length(film.getLength())
                .replacementCost(film.getReplacementCost())
                .rating(film.getRating().toString())
                .specialFeatures(film.getSpecialFeatures())
                .build();

        log.info("LanguageEntity: {}", filmEntity);
        try{
            log.info("Előtte");
            filmRepository.save(filmEntity);
           // film.setId(filmEntity.getId());
            log.info("Utána");
        }catch(Exception e){
            log.error(e.getMessage());
        }
    }

    //delete teljes
    @Override
    public void deleteFilm(Film film) throws UnknownFilmException, UnknownLanguageException {
        Collection<FilmEntity> filmEntity = filmRepository.findByTitleAndDescriptionAndReleaseYearAndLanguageAndRentalDurationAndRentalRateAndLengthAndReplacementCostAndRatingAndSpecialFeatures(
                film.getTitle(),
                film.getDescription(),
                film.getReleaseYear(),
                queryLanguage(film.getLanguage()),

                film.getRentalDuration(),
                film.getRentalRate(),
                film.getLength(),
                film.getReplacementCost(),
                film.getRating().getType(),
                film.getSpecialFeatures());


        if(filmEntity.isEmpty())
        {
            throw new UnknownFilmException("Unkown Film: "+ filmEntity.toString());
        }

        log.info("FilmEntity: {}", filmEntity);
        try{
           // filmRepository.delete(filmEntity.get());
            filmEntity.forEach(filmRepository::delete);
        }catch(Exception e)
        {
            log.error(e.getMessage());
        }
    }

    //delete Id
    @Override
    public void deleteFilmById(Short id) throws UnknownFilmException{
        Optional<FilmEntity> filmEntity = filmRepository.findById(id).stream().findFirst();
        if(!filmEntity.isPresent())
        {
            throw new UnknownFilmException("Unkown Film: "+ id);
        }

        log.info("FilmEntity: {}", filmEntity);
        try{
            filmRepository.delete(filmEntity.get());
        }catch(Exception e)
        {
            log.error(e.getMessage());
        }

    }

    // update teljes
    @Override
    public void updateFirstMatch(Film film, Film updated) throws UnknownLanguageException, UnknownFilmException{
        Optional<FilmEntity> filmEntity = filmRepository.findByTitleAndDescriptionAndReleaseYearAndLanguageAndRentalDurationAndRentalRateAndLengthAndReplacementCostAndRatingAndSpecialFeatures(
                film.getTitle(),
                film.getDescription(),
                film.getReleaseYear(),
                queryLanguage(film.getLanguage()),

                film.getRentalDuration(),
                film.getRentalRate(),
                film.getLength(),
                film.getReplacementCost(),
                film.getRating().getType(),
                film.getSpecialFeatures())
                .stream().findFirst();

        if(!filmEntity.isPresent())
        {
            throw new UnknownFilmException("Unkown Film: " + filmEntity);
        }

        log.info("The Original Film is: " + filmEntity.get().toString());
        filmEntity.get().setTitle(updated.getTitle());
        filmEntity.get().setDescription(updated.getDescription());
        filmEntity.get().setReleaseYear(updated.getReleaseYear());
        filmEntity.get().setLanguage(queryLanguage(updated.getLanguage()));

        filmEntity.get().setRentalDuration(updated.getRentalDuration());
        filmEntity.get().setRentalRate(updated.getRentalRate());
        filmEntity.get().setReplacementCost(updated.getReplacementCost());
        filmEntity.get().setSpecialFeatures(updated.getSpecialFeatures());
        log.info("The New Updated Film is: " + filmEntity.get().toString());
        try{
            filmRepository.save(filmEntity.get());
        }catch(Exception e)
        {
            log.error(e.getMessage());
        }
    }

    //Laguage ID
    protected LanguageEntity queryLanguage(int languageId) throws UnknownLanguageException{
        Optional<LanguageEntity> languageEntity = languageRepository.findByLanguageId(languageId)
                .stream().filter(entity -> entity.getLanguageId()==(languageId)).findFirst();
        if(!languageEntity.isPresent()){
            throw new UnknownLanguageException("Unknown Language Id: " + languageId);
        }
        return languageEntity.get();
    }

    protected LanguageEntity queryOriginal(String name) throws UnknownLanguageException{
        Optional<LanguageEntity> languageEntity = languageRepository.findByName(name)
                .stream().filter(entity -> entity.getName().equals(name)).findFirst();
        if(!languageEntity.isPresent()){
            throw new UnknownLanguageException("Unknown Language Id: " + name);
        }
        return languageEntity.get();
    }

    public String checkLanguage(String L){
        if(L == null){
            return "Nincs Megadva";
        }
        return L;
    }

}
