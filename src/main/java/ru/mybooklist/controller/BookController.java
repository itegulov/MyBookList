package ru.mybooklist.controller;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mybooklist.model.Book;
import ru.mybooklist.repositories.BookRepository;

/**
 * @author Daniyar Itegulov
 */
@Controller
@RequestMapping("books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFoundException() {
        return "books/not_found";
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String allBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "books/list";
    }

    @RequestMapping(params = "id", method = RequestMethod.GET)
    public String bookWithId(@RequestParam("id") int id, Model model) {
        Book book = bookRepository.findOne(id);
        if (book == null) {
            throw new ResourceNotFoundException("There is no book with id = " + id);
        }
        model.addAttribute("book", book);
        return "books/book";
    }
}
