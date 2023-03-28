package ua.chernonog.springcourse.DAO;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.chernonog.springcourse.model.Book;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getAllBooks() {
        return jdbcTemplate.query("Select * from book", new BeanPropertyRowMapper<>(Book.class));

    }

    public void addNewBook(Book book) {
        jdbcTemplate.update("insert into book (name,author,year) values (?,?,?)", book.getName(), book.getAuthor(),
                book.getYear());
    }

    public Book showBook(int id) {
        return jdbcTemplate.query("select * from book where id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void editBook(int id, Book book) {
        jdbcTemplate.update("update book set name=?,author=?,year=? where id=?", book.getName(), book.getAuthor()
                , book.getYear(), book.getId());
    }

    public Optional<Book> getBookByName(String name) {
        return jdbcTemplate.query("select * from book where name=?", new Object[]{name},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny();
    }
}
