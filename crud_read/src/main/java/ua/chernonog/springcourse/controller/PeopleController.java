package ua.chernonog.springcourse.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.chernonog.springcourse.DAO.PersonDAO;
import ua.chernonog.springcourse.model.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String getPeople(Model model) {
        model.addAttribute("people", personDAO.displayPeople());

        return "people/home";
    }

    @GetMapping("/new")
    public String addPersonPage(Model model) {
        model.addAttribute("person", new Person());
        return "people/add";

    }

    @PostMapping()
    public String createPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "people/add";
        personDAO.savePerson(person);
        return "redirect:people";
    }

}
