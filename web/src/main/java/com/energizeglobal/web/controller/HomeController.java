package com.energizeglobal.web.controller;

import com.energizeglobal.service.service.BookService;
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
@Controller
@RequestMapping
public class HomeController {

	private static final String SESSION_ATTRIBUTE_BOOK_LIST = "BookList";

	@Autowired
	BookService bookService;
	@Autowired
	BookPagination pagination;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String initHome(Model model, HttpSession session, HttpServletRequest request) {
//        User user = (User) session.getAttribute("loggedUser");
//        user.getRole().getId();

		pagination.pagination(0, model);
		return "home";
	}

	@RequestMapping(value = "/books")
	public String booksRedirect(HttpServletRequest request, Model model) {
//        request.getSession().setAttribute(SESSION_ATTRIBUTE_BOOK_LIST, null);
		return "redirect:/books/page/1";
	}

	@RequestMapping(value = "/books/page/{pageNumber}", method = RequestMethod.GET)
	public String pagedBooksPage(Model model, @PathVariable Integer pageNumber) {
		pagination.pagination(pageNumber, model);
		return "home";
	}

}
