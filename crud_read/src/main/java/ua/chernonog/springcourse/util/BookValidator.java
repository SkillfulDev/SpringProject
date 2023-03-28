package ua.chernonog.springcourse.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.chernonog.springcourse.DAO.BookDAO;
import ua.chernonog.springcourse.model.Book;
@Component
public class BookValidator implements Validator {
    private final BookDAO bookDAO;

    public BookValidator(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book)target;

        if (bookDAO.getBookByName(book.getName()).isPresent())
            errors.rejectValue("name","","Такая книга уже есть в базе");

    }
}
