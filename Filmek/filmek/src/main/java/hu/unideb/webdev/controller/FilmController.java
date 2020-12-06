package hu.unideb.webdev.controller;


import hu.unideb.webdev.controller.dto.FilmDto;
import hu.unideb.webdev.controller.dto.FilmUpdateDto;
import hu.unideb.webdev.exceptions.UnknownFilmException;
import hu.unideb.webdev.exceptions.UnknownLanguageException;
import hu.unideb.webdev.model.Film;
import hu.unideb.webdev.service.FilmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FilmController {

    private final FilmService service;

    @GetMapping("/Film")
    public Collection<FilmDto> listFilms(){
        return service.getAllFilms().stream()
                .map( model -> FilmDto.builder()
              // .id( model.getId())
                .title(model.getTitle())
                .description(model.getDescription())
                .releaseYear(model.getReleaseYear())
                .language(model.getLanguage())

                .rentalDuration(model.getRentalDuration())
                .rentalRate(model.getRentalRate())
                .length(model.getLength())
                .replacementCost(model.getReplacementCost())
                .rating(model.getRating())
                .specialFeatures(model.getSpecialFeatures())
                .build()).collect(Collectors.toList());
    }

    @PostMapping("/Film")
    public void record(@RequestBody FilmDto filmDto) {
        String[] str = {"Trailers", "Behind The Scenes", "Deleted Scenes", "Commentaries"};
        String[] special = filmDto.getSpecialFeatures().split(",");

        for(String s : special)
        {
            Boolean test = false;
            for(String s2 : str)
            {
                if(s.equalsIgnoreCase(s2))
                {
                    test = true;
                }
            }
            if(!test){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Rossz Special");
            }
        }

        try{
            service.recordFilm(new Film(
                  //  filmDto.getId(),
                    filmDto.getTitle(),
                    filmDto.getDescription(),
                    filmDto.getReleaseYear(),
                    filmDto.getLanguage(),

                    filmDto.getRentalDuration(),
                    filmDto.getRentalRate(),
                    filmDto.getLength(),
                    filmDto.getReplacementCost(),
                    filmDto.getRating(),
                    filmDto.getSpecialFeatures()
            ));
        }catch (UnknownLanguageException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/Film")
    public void deleteFilm(@RequestBody FilmDto filmDto){
        try{
            service.deleteFilm( new Film(
                //    filmDto.getId(),
                    filmDto.getTitle(),
                    filmDto.getDescription(),
                    filmDto.getReleaseYear(),
                    filmDto.getLanguage(),

                    filmDto.getRentalDuration(),
                    filmDto.getRentalRate(),
                    filmDto.getLength(),
                    filmDto.getReplacementCost(),
                    filmDto.getRating(),
                    filmDto.getSpecialFeatures()
            ));
        } catch( UnknownFilmException | UnknownLanguageException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

  /*  @DeleteMapping("/Film")
    public void deleteFilmId(FilmDto filmDto){
        try{
            service.deleteFilmId(filmDto.getId());
        } catch (UnknownFilmException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    } */



    @PutMapping("/Film")
    public void updateFirstMatch(@RequestBody FilmUpdateDto filmUpdateDto){

        try{
            service.updateFirstMatch( new Film(
                  //  filmUpdateDto.getId(),
                    filmUpdateDto.getTitle(),
                    filmUpdateDto.getDescription(),
                    filmUpdateDto.getReleaseYear(),
                    filmUpdateDto.getLanguage(),

                    filmUpdateDto.getRentalDuration(),
                    filmUpdateDto.getRentalRate(),
                    filmUpdateDto.getLength(),
                    filmUpdateDto.getReplacementCost(),
                    filmUpdateDto.getRating(),
                    filmUpdateDto.getSpecialFeatures()),
                    new Film(
                      //      filmUpdateDto.getUpdatedId(),
                            filmUpdateDto.getUpdatedTitle(),
                            filmUpdateDto.getUpdatedDescription(),
                            filmUpdateDto.getUpdatedReleaseYear(),
                            filmUpdateDto.getUpdatedLanguage(),

                            filmUpdateDto.getUpdatedRentalDuration(),
                            filmUpdateDto.getUpdatedRentalRate(),
                            filmUpdateDto.getUpdatedLength(),
                            filmUpdateDto.getUpdatedReplacementCost(),
                            filmUpdateDto.getUpdatedRating(),
                            filmUpdateDto.getUpdatedSpecialFeatures())
            );
        }catch(UnknownLanguageException | UnknownFilmException e){
            throw new ResponseStatusException((HttpStatus.BAD_REQUEST),e.getMessage());
        }
    }

}
