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

    public List<Person> showAllPeople() {
        return jdbcTemplate.query("Select * from person", new BeanPropertyRowMapper<>(Person.class));
    }

    public void addNewPerson(Person person) {
        jdbcTemplate.update("insert into person (fullname, year) values(?,?)",
                person.getFullName(), person.getYear());
    }

    public Person getPersonById(int id) {
        return jdbcTemplate.query("Select * from person where id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void changePerson(int id, Person person) {
        jdbcTemplate.update("update person set fullname=?, year=? where id=? ", person.getFullName(),
                person.getYear(), person.getId());
    }

    public Optional<Person> getPersonByName(String fullName) {
        return jdbcTemplate.query("Select * from person where fullname=?", new Object[]{fullName},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }
}