package hu.unideb.webdev.controller;

import hu.unideb.webdev.controller.dto.LanguageDto;
import hu.unideb.webdev.controller.dto.LanguageUpdateDto;
import hu.unideb.webdev.exceptions.UnknownLanguageException;
import hu.unideb.webdev.model.Language;
import hu.unideb.webdev.service.LanguageService;
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
public class LanguageController {

    private final LanguageService service;

    @GetMapping("/Language")
    public Collection<LanguageDto> listLanguages() {
        return service.getAllLanguages().stream().map(
                model -> LanguageDto.builder()
                        .name(model.getName())
                        .build()).collect(Collectors.toList());
    }

    @PostMapping("/Language")
    public void record(@RequestBody LanguageDto languageDto) {

        try {
            service.recordLanguage(
                    new Language(
                            languageDto.getName()
                    ));
        } catch (UnknownLanguageException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/Language")
    public void deleteLanguage(@RequestBody LanguageDto languageDto){
        try{
            service.deleteLanguage(
                    new Language(
                            languageDto.getName()
                    )
            );
        }catch(UnknownLanguageException e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @PutMapping("/Language")
    public void updateLanguage(@RequestBody LanguageUpdateDto languageUpdateDto){
        try{
            service.updateLanguage(
                    new Language(
                            languageUpdateDto.getName()
                    ),
                    new Language(
                            languageUpdateDto.getUpdatedName()
                    )
            );
        }catch(UnknownLanguageException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
}
