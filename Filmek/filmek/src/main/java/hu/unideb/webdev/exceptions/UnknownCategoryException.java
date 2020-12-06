package hu.unideb.webdev.exceptions;


import hu.unideb.webdev.model.Category;
import lombok.Data;

@Data
public class UnknownCategoryException extends Exception {

    private Category category;

    public UnknownCategoryException(Category category){
        this.category=category;
    }


    public UnknownCategoryException(String message, Category category){
        super(message);
        this.category=category;
    }

    public UnknownCategoryException(String message){
        super(message);
    }
}

