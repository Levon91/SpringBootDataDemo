package com.energizeglobal.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by test on 7/26/2016.
 */
@Api(tags = "Admin accounts management")
@Controller
@RequestMapping
public class AdminController {

    private static final String SESSION_ATTRIBUTE_BOOK_LIST = "BookList";

    @Autowired
    BookPagination pagination;

    @ApiOperation("Retrieve a list of all users")
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String initHome(HttpServletRequest request, Model model, HttpSession session) {
//        User user = (User) session.getAttribute("loggedUser");
//        user.getRole().getId();

        pagination.pagination(0, model);
        return "admin/admin";
    }

    @RequestMapping(value = "/admin/books", method = RequestMethod.GET)
    public String booksRedirect() {
        return "redirect:admin/books/page/1";
    }

    @RequestMapping(value = "/admin/books/page/{pageNumber}", method = RequestMethod.GET)
    public String pagedBooksPage(Model model, @PathVariable Integer pageNumber) {
        pagination.pagination(pageNumber, model);
        return "admin/admin";
    }

}
