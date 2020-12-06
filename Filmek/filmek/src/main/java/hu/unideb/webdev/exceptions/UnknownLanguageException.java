package hu.unideb.webdev.exceptions;

import hu.unideb.webdev.model.Language;
import lombok.Data;

@Data
public class UnknownLanguageException extends Exception {

    private Language language;

    public UnknownLanguageException(Language language){
        this.language = language;
    }


    public UnknownLanguageException(String message, Language language){
        super(message);
        this.language = language;
    }

    public UnknownLanguageException(String message){
        super(message);
    }
}
