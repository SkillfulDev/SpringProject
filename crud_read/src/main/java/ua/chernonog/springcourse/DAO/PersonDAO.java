package ua.chernonog.springcourse.DAO;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.chernonog.springcourse.model.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> displayPeople() {
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));

    }

    public void savePerson(Person person) {
        jdbcTemplate.update("INSERT INTO person (fullname,year) VALUES (?,?) "
                , person.getFullName(), person.getYear());
    }

    public Optional<Person> show (String fullname) {
       return jdbcTemplate.query("SELECT * FROM person WHERE fullname=?",new Object[]{fullname},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public Person show(int id) {
       return jdbcTemplate.query("SELECT * FROM person where id=?",new Object[]{id},
               new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void changePerson(int id,Person person) {
        jdbcTemplate.update("UPDATE person set fullname =?,year=? where id=?",
                person.getFullName(),person.getYear(),person.getId());
    }
}
