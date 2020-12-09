package hu.unideb.webdev.controller;

import hu.unideb.webdev.controller.dto.FilmActorDto;
import hu.unideb.webdev.controller.dto.FilmActorUpdateDto;
import hu.unideb.webdev.exceptions.UnknownActorException;
import hu.unideb.webdev.exceptions.UnknownFilmException;
import hu.unideb.webdev.model.FilmActor;
import hu.unideb.webdev.service.FilmActorService;
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
public class FilmActorController {

    private final FilmActorService service;

    @GetMapping("/FilmActor")
    public Collection<FilmActorDto> listFilmActors(){
        return service.getAllFilmActors().stream().map(
                model -> FilmActorDto.builder()
                .filmTitle(model.getFilmTitle())
                .actorName(model.getActorName())
                .build()).collect(Collectors.toList());
    }


    @PostMapping("/FilmActor")
    public void createFilmActor(@RequestBody FilmActorDto filmActorDto){
        try{
            service.recordFilmActor(
                    new FilmActor(
                            filmActorDto.getFilmTitle(),
                            filmActorDto.getActorName()
                    )
            );
        }catch(UnknownActorException | UnknownFilmException e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @DeleteMapping("/FilmActor")
    public void deleteFilmActor(@RequestBody FilmActorDto filmActorDto){
        try{
            service.deleteFilmActor(
                    new FilmActor(
                            filmActorDto.getFilmTitle(),
                            filmActorDto.getActorName()
                    )
            );
        }catch(UnknownActorException | UnknownFilmException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @PutMapping("/FilmActor")
    public void updateFilmActor(@RequestBody FilmActorUpdateDto filmActorUpdateDto){
        try{
            service.updateFilmActor(
                    new FilmActor(
                            filmActorUpdateDto.getFilmTitle(),
                            filmActorUpdateDto.getActorName()
                    ),
                    new FilmActor(
                            filmActorUpdateDto.getUpdatedFilmTitle(),
                            filmActorUpdateDto.getUpdatedActorName()
                    )
            );
        }catch(UnknownActorException | UnknownFilmException e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
}
