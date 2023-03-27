package ua.chernonog.springcourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
        model.addAttribute("people",personDAO.showAllPeople());
        return "/people/showPeoplePage";
    }
    @GetMapping("/new")
    public String newPersonPage(Model model){
        model.addAttribute("person",new Person());
        return "people/newPersonPage";
    }
    @PostMapping()
    public String addNewPerson(@ModelAttribute("person") Person person){
        personDAO.addNewPerson(person);
        return "redirect:people";

    }


}
