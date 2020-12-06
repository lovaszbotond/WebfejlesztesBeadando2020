package hu.unideb.webdev.controller;

import hu.unideb.webdev.controller.dto.FilmCategoryDto;
import hu.unideb.webdev.controller.dto.FilmCategoryUpdateDto;
import hu.unideb.webdev.exceptions.UnknownCategoryException;
import hu.unideb.webdev.exceptions.UnknownFilmException;
import hu.unideb.webdev.model.FilmCategory;
import hu.unideb.webdev.service.FilmCategoryService;
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
public class FilmCategoryController {

    private final FilmCategoryService service;

    @GetMapping("/FilmCategory")
    public Collection<FilmCategoryDto> listFilmCategories() {
        return service.getAllFilmCategories().stream().map(
                model -> FilmCategoryDto.builder()
                        .filmTitle(model.getFilmTitle())
                        .categoryName(model.getCategoryName())
                        .build()
        ).collect(Collectors.toList());
    }

    @PostMapping("/FilmCategory")
    public void recordFilmCategory(@RequestBody FilmCategoryDto filmCategoryDto) {
        try {
            service.recordFilmCategory(
                    new FilmCategory(
                            filmCategoryDto.getFilmTitle(),
                            filmCategoryDto.getCategoryName()
                    )
            );
        }catch(UnknownCategoryException | UnknownFilmException e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @DeleteMapping("/FilmCategory")
    public void deleteFilmCategory(@RequestBody FilmCategoryDto filmCategoryDto){
        try{
            service.deleteFilmCategory(
                    new FilmCategory(
                            filmCategoryDto.getFilmTitle(),
                            filmCategoryDto.getCategoryName()
                    )
            );
        }catch(UnknownCategoryException | UnknownFilmException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/FilmCategory")
    public void updateFilmCategory(@RequestBody FilmCategoryUpdateDto filmCategoryUpdateDto){
        try{
            service.updateFilmCategory(
                    new FilmCategory(
                            filmCategoryUpdateDto.getFilmTitle(),
                            filmCategoryUpdateDto.getCategoryName()
                    ),
                    new FilmCategory(
                            filmCategoryUpdateDto.getUpdatedFilmTitle(),
                            filmCategoryUpdateDto.getUpdatedCategoryName()
                    )
            );
        }catch(UnknownCategoryException | UnknownFilmException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
