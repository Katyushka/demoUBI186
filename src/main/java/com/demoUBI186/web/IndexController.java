package com.demoUBI186.web;

import com.demoUBI186.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ekaterina on 11.12.2016.
 */


@Controller
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    protected static final String PATH_ROOT = "/";

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(PATH_ROOT)
    public String getHomePage(Model model) {
        LOGGER.debug("Getting home page");
        return "index";
    }
}