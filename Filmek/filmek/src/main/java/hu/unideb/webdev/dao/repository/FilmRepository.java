package hu.unideb.webdev.dao.repository;

import hu.unideb.webdev.dao.entity.FilmEntity;
import hu.unideb.webdev.dao.entity.LanguageEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;


public interface FilmRepository extends CrudRepository<FilmEntity, Integer> {
    Collection<FilmEntity> findByTitleAndDescriptionAndReleaseYearAndLanguageAndRentalDurationAndRentalRateAndLengthAndReplacementCostAndRatingAndSpecialFeatures
            (
                    String title, String description,
                    int releaseYear, LanguageEntity languageEntity,

                    int rentalDuration, Double rentalRate, Short length,
                    Double replacementCost, String rating, String specialFeatures
            );
    Collection<FilmEntity> findById(Short id);

    Collection<FilmEntity> findByTitle (String title);


}
