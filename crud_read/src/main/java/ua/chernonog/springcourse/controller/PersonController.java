package ua.chernonog.springcourse.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.chernonog.springcourse.DAO.PersonDAO;
import ua.chernonog.springcourse.model.Person;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonDAO personDAO;

    @Autowired
    public PersonController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String showPeoplePage(Model model) {
        model.addAttribute("people", personDAO.showAllPeople());
        return "/people/showPeoplePage";
    }

    @GetMapping("/new")
    public String newPersonPage(Model model) {
        model.addAttribute("person", new Person());
        return "people/newPersonPage";
    }

    @PostMapping()
    public String addNewPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "/people/newPersonPage";
        personDAO.addNewPerson(person);
        return "redirect:/people";

    }

    @GetMapping("/{id}")
    public String personPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.getPersonById(id));
        return "/people/personPage";

    }

    @PatchMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id, @ModelAttribute("person")
    @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "/people/personPage";
        personDAO.changePerson(id,person);
        return "redirect:/people";

    }
}
