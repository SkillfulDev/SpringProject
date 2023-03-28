package ua.chernonog.springcourse.util;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.chernonog.springcourse.DAO.PersonDAO;
import ua.chernonog.springcourse.model.Person;

@Component
public class PersonValidator implements Validator {
    private final PersonDAO personDAO;

    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {

        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (personDAO.getPersonByName(person.getFullName()).isPresent()) {
            errors.rejectValue("fullName", "", "Такое ФИО уже существует");
        }

    }
}
