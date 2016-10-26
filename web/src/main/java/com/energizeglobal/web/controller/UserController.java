package com.energizeglobal.web.controller;

import com.energizeglobal.service.service.UserService;
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
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserPagination userPagination;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String initHome(Model model, HttpSession session, HttpServletRequest request) {
        userPagination.pagination(request, 0, model);
        return "admin/users";
    }

    @RequestMapping(value = "admin/users")

    public String usersRedirect(HttpServletRequest request) {


        return "redirect:admin/users/page/1";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "admin/users/page/{pageNumber}", method = RequestMethod.GET)
    public String pagedUsersPage(HttpServletRequest request, @PathVariable Integer pageNumber, Model uiModel) {
        userPagination.pagination(request, pageNumber, uiModel);
        return "/admin/users";
    }

}
