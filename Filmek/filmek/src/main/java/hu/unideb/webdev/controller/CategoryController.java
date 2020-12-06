package hu.unideb.webdev.controller;


import hu.unideb.webdev.controller.dto.CategoryDto;
import hu.unideb.webdev.controller.dto.CategoryUpdateDto;
import hu.unideb.webdev.exceptions.UnknownCategoryException;
import hu.unideb.webdev.model.Category;
import hu.unideb.webdev.service.CategoryService;
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
public class CategoryController {
    private final CategoryService service;

    @GetMapping("/Category")
    public Collection<CategoryDto> listCategories(){
        return service.getAllCategories().stream().map(
                model -> CategoryDto.builder()
                .categoryId(model.getCategoryId())
                .name(model.getName())
                .build()
        ).collect(Collectors.toList());
    }

    @PostMapping("/Category")
    public void recordCategory(@RequestBody CategoryDto categoryDto){
        try{
            service.recordCategory(
                    new Category(
                            categoryDto.getCategoryId(),
                            categoryDto.getName()
                    )
            );
        }catch (UnknownCategoryException e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @DeleteMapping("/Category")
    public void deleteCategory(@RequestBody CategoryDto categoryDto) {
        try {
            service.deleteCategory(
                    new Category(
                            categoryDto.getCategoryId(),
                            categoryDto.getName()
                    )
            );
        }catch (UnknownCategoryException e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/Category")
    public void updateCategory(@RequestBody CategoryUpdateDto categoryUpdateDto){
        try{
            service.updateCategory(
                    new Category(
                            categoryUpdateDto.getCategoryId(),
                            categoryUpdateDto.getName()
                    ),
                    new Category(
                            categoryUpdateDto.getUpdatedCategoryId(),
                            categoryUpdateDto.getUpdatedName()
                    )
            );
        }catch(UnknownCategoryException e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}


