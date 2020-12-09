package hu.unideb.webdev.dao;

import hu.unideb.webdev.dao.repository.CategoryRepository;
import hu.unideb.webdev.exceptions.UnknownCategoryException;
import hu.unideb.webdev.model.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class CategoryDaoImplTest {
    @Spy
    @InjectMocks
    private CategoryDaoImpl dao;
    @Mock
    private CategoryRepository categoryRepository;

    @Test
    public void createCategoryTest() throws UnknownCategoryException {
        dao.createCategory(getCategory());
        verify(categoryRepository,times(1)).save(any());
    }

    private Category getCategory() {
        return new Category(
                19,
                "Drama1"
        );
    }
}
