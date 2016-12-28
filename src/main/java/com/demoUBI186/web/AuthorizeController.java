package com.demoUBI186.web;

import com.demoUBI186.domain.User;
import com.demoUBI186.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ekaterina on 18.12.2016.
 */


@Controller
public class AuthorizeController {
    protected static final String PATH_ROOT = "/authorize";
    private final UserService userService;

    public AuthorizeController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = PATH_ROOT, method = RequestMethod.GET)
    public String getHomePage(Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("user", user);
        return "authorize";
    }
}
