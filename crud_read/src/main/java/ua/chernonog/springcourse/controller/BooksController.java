package ua.chernonog.springcourse.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.chernonog.springcourse.DAO.BookDAO;
import ua.chernonog.springcourse.model.Book;
import ua.chernonog.springcourse.util.BookValidator;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;

    private final BookValidator bookValidator;

    public BooksController(BookDAO bookDAO, BookValidator bookValidator) {
        this.bookDAO = bookDAO;
        this.bookValidator = bookValidator;
    }

    @GetMapping()
    public String showAllBooksPage(Model model) {
        model.addAttribute("books", bookDAO.getAllBooks());
        return "/books/showAllBooksPage";
    }

    @GetMapping("/new")
    public String addNewBookPage(Model model) {
        model.addAttribute("book", new Book());
        return "/books/addNewBookPage";
    }

    @PostMapping()
    public String addNewBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        bookValidator.validate(book,bindingResult);
        if (bindingResult.hasErrors())
            return "/books/addNewBookPage";
        bookDAO.addNewBook(book);
        return "redirect:/books";
    }

    @GetMapping("{id}")
    public String editBookPage(@PathVariable("id") int id,Model model) {
       model.addAttribute("book", bookDAO.showBook(id));
        return "/books/editBookPage";
    }
    @PatchMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id,@ModelAttribute("book")
    @Valid Book book,BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "/books/editBookPage";
        bookDAO.editBook(id,book);
        return "redirect:/books";
    }
}
