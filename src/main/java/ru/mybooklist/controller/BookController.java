package ru.mybooklist.controller;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mybooklist.dao.BookDAO;
import ru.mybooklist.dao.UserDAO;
import ru.mybooklist.model.Book;
import ru.mybooklist.model.User;

/**
 * @author Daniyar Itegulov
 */
@Controller
@RequestMapping("books")
public class BookController {
    @Autowired
    private BookDAO bookDAO;

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFoundException() {
        return "books/not_found";
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String allBooks(Model model) {
        model.addAttribute("books", bookDAO.allBooks());
        return "books/list";
    }

    @RequestMapping(params = "id", method = RequestMethod.GET)
    public String bookWithId(@RequestParam("id") int id, Model model) {
        Book book = bookDAO.getBookById(id);
        if (book == null) {
            throw new ResourceNotFoundException("There is no book with id = " + id);
        }
        model.addAttribute("book", book);
        return "books/book";
    }
}
