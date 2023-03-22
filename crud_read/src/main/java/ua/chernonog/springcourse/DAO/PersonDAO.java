package ua.chernonog.springcourse.DAO;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.chernonog.springcourse.model.Person;

import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> displayPeople() {
       return jdbcTemplate.query("SELECT * FROM person",new BeanPropertyRowMapper<>(Person.class));

    }

    public void savePerson(Person person) {
        jdbcTemplate.update("INSERT INTO person (fullname,age) VALUES (?,?) "
                ,person.getFullName(),person.getAge());
    }
}
