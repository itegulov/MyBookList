package ru.mybooklist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.mybooklist.dao.BookDAO;
import ru.mybooklist.dao.UserDAO;
import ru.mybooklist.model.User;

/**
 * @author Daniyar Itegulov
 */
@Controller
@RequestMapping("books")
public class BookController {
    @Autowired
    private BookDAO bookDAO;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String newUser(Model model) {
        model.addAttribute("books", bookDAO.allBooks());
        return "books/list";
    }
}
