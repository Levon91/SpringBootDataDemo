package com.energizeglobal.web.controller;

import com.energizeglobal.common.model.entity.RoleEnum;
import com.energizeglobal.common.model.entity.User;
import com.energizeglobal.service.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by test on 7/26/2016.
 */
@Controller
@RequestMapping(value = RegistrationController.CONTROLLER_NAME)
public class RegistrationController {
    public static final String CONTROLLER_NAME = "/register";

    @Autowired
    UserServiceImpl userService;

    // type localhost:8001/login to go to register page

    @RequestMapping(method = RequestMethod.GET)
    public String initPage(Model model, @ModelAttribute("userForm") User user) {
//        model.addAttribute("userForm", getUser());
        return "registration";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String registerUser(Model model, @Valid @ModelAttribute("userForm") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        User dbUser = userService.authenticate(user.getEmail(), user.getPassword());

//        model.addAttribute("userForm", user);
        if (dbUser != null) {
            model.addAttribute("message", "User with this username already exists");
            return "registration";
        }
        user.setRole(RoleEnum.getById(1));
        userService.addUser(user);
        model.addAttribute("message", "You have Successful registered");
        return "redirect:login";
    }


    public User getUser() {
        return new User();
    }
}
