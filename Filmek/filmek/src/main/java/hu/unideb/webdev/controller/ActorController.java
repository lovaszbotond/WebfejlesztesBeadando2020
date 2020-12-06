package hu.unideb.webdev.controller;

import hu.unideb.webdev.controller.dto.ActorDto;
import hu.unideb.webdev.controller.dto.ActorUpdateDto;
import hu.unideb.webdev.exceptions.UnknownActorException;
import hu.unideb.webdev.model.Actor;
import hu.unideb.webdev.service.ActorService;
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
public class ActorController {

    private final ActorService service;

    @GetMapping("/Actor")
    public Collection<ActorDto> listActors() {
        return service.getAllActors().stream().map(
                model -> ActorDto.builder()
                        .firstName(model.getFirstName())
                        .lastName(model.getLastName())
                        .build()).collect(Collectors.toList());
    }

    @PostMapping("/Actor")
    public void record(@RequestBody ActorDto actorDto) {
        try {
            service.recordActor(
                    new Actor(
                            actorDto.getFirstName(),
                            actorDto.getLastName()
                    )
            );
        } catch (UnknownActorException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/Actor")
    public void deleteActor(@RequestBody ActorDto actorDto) {
        try {
            service.deleteActor(
                    new Actor(
                            actorDto.getFirstName(),
                            actorDto.getLastName()
                    )
            );
        } catch (UnknownActorException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/Actor")
    public void updateActor(@RequestBody ActorUpdateDto actorUpdateDto) {
        try {
            service.updateActor(new Actor(
                    actorUpdateDto.getFirstName(),
                    actorUpdateDto.getLastName()
            ), new Actor(
                    actorUpdateDto.getUpdatedFirstName(),
                    actorUpdateDto.getUpdatedLastName()
            ));
        } catch (UnknownActorException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}