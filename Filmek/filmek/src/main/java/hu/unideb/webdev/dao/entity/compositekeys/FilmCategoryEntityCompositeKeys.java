package hu.unideb.webdev.dao.entity.compositekeys;

import hu.unideb.webdev.dao.entity.CategoryEntitiy;
import hu.unideb.webdev.dao.entity.FilmEntity;

import java.io.Serializable;

public class FilmCategoryEntityCompositeKeys implements Serializable {

    private FilmEntity filmId;
    private CategoryEntitiy categoryId;
}
