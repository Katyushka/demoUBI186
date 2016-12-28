package com.demoUBI186.web;

import com.demoUBI186.domain.*;
import com.demoUBI186.domain.validator.SiteCreateFormValidator;
import com.demoUBI186.repository.SiteRepository;
import com.demoUBI186.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

/**
 * Created by ekaterina on 13.12.2016.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class SiteController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SiteController.class);

    protected static final String PATH_ROOT = "/sites";
    protected static final String PATH_CREATE = "/site/create";
    protected static final String PATH_SAVE = "/sites/save";
    protected static final String PATH_GET = "/sites/get/{siteId}";
    protected static final String PATH_DELETE = "/sites/delete/{siteId}";

    @Autowired
    private SiteService siteService;

    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private SiteCreateFormValidator siteCreateFormValidator;

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(siteCreateFormValidator);
    }


    @RequestMapping(value = PATH_CREATE, method = RequestMethod.GET)
    public String getSiteCreatePage(Model model) {
        LOGGER.debug("Getting site create form");
        model.addAttribute("form", new SiteCreateForm());
        return "siteCreate";
    }


    @RequestMapping(value = PATH_CREATE, method = RequestMethod.POST)
    public String handleSiteCreateForm(@Valid @ModelAttribute("form") SiteCreateForm form, BindingResult bindingResult) {

        LOGGER.debug("Processing word create form={}, bindingResult={}", form, bindingResult);
        if (bindingResult.hasErrors()) {
            return "siteCreate";
        }
        try {
            Site site = siteService.create(form);
            CurrentSite currentSite = new CurrentSite(site);

        } catch (DataIntegrityViolationException e) {
            LOGGER.warn("Exception occurred when trying to save the user, assuming duplicate email", e);
            bindingResult.reject("email.exists", "Email already exists");
            return "siteCreate";
        }
        return "redirect:/";
    }

    @RequestMapping(PATH_ROOT)
    public String getSites(Model model) {
        LOGGER.debug("Getting Sites list");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            model.addAttribute("site", new Site());
            model.addAttribute("sites", siteService.getAllSites());


        return "sites";
    }

    @RequestMapping(PATH_GET)
    @Secured("ROLE_ADMIN")
    public String getUser(@PathVariable("siteId") Long siteId, Model model) {
        LOGGER.debug("Getting get Site action" + siteId);
        Site site = siteService.getSitesById(siteId);
        model.addAttribute("site", site);
        model.addAttribute("password", "invisible");
        return "siteForm";
    }



    @RequestMapping(value = PATH_SAVE, method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String saveWord(@Valid @ModelAttribute("site") Site site, BindingResult bindingResult) {
        LOGGER.debug("Getting save word action");
        if (bindingResult.hasErrors()) {
            return "siteForm";
        }
        siteService.save(site);
        return "redirect:/sites";
    }

    @RequestMapping(value = PATH_DELETE, method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String deleteWord(@PathVariable("siteId") Long siteId) {
        LOGGER.debug("Delete Site by id action");
        Site site = siteService.getSitesById(siteId);
        siteService.delete(site);
        return "redirect:/sites";
    }



}
