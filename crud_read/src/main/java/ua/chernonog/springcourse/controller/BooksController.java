package ua.chernonog.springcourse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.chernonog.springcourse.DAO.BooksDAO;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BooksDAO bookDAO;

    public BooksController(BooksDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String showAllBooks(Model model){
        model.addAttribute("books", bookDAO.showAllBooks());
        return "books/show";


    }
}
