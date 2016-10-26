package com.energizeglobal.web.controller;


import com.energizeglobal.common.model.entity.RoleEnum;
import com.energizeglobal.common.model.entity.User;
import com.energizeglobal.service.service.impl.UserServiceImpl;
import com.energizeglobal.web.model.UserLoginCmd;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
@RequestMapping
public class LoginController {
    private static final Logger log = LogManager.getLogger(LoginController.class);
    public static final String LOGIN = "/login";

    @Autowired
    UserServiceImpl userService;

    // type localhost:8001/login to go to login page

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm(Model model, @ModelAttribute("userForm") UserLoginCmd user) {
        return "/login";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String processLogin(Model model, HttpSession session, @Valid @ModelAttribute("userForm") UserLoginCmd user, BindingResult result
    ) {

        if (result.hasErrors()) {
            return "login";
        }
        User authenticatedUser = userService.getUserByCretentials(user.getUsername(), user.getPassword());

        if (authenticatedUser == null) {
            model.addAttribute("msg_incorrect", "Incorrect Login or Password");
            model.addAttribute("loggedUser", user);
            log.debug("Incorrect Login or Password");
            return "login";
        }

        model.addAttribute("loggedUser", authenticatedUser);
        session.setAttribute("loggedUser", authenticatedUser);

        if (authenticatedUser.getRole() == RoleEnum.ADMIN) {
            return "redirect:/" + "admin";
        }
        return "redirect:/" + "books";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String showLoginForm(HttpSession session) {

        session.removeAttribute("loggedUser");

        return "redirect:/login";
    }
}
