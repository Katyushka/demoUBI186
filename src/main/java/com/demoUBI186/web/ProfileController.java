package com.demoUBI186.web;


import com.demoUBI186.domain.CheckedSite;
import com.demoUBI186.domain.Site;
import com.demoUBI186.repository.CheckedSiteRepository;
import com.demoUBI186.service.CheckedSiteService;
import com.demoUBI186.service.ParserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by ekaterina on 13.12.2016.
 */

@Controller
public class ProfileController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final ParserService parserService;
    private final CheckedSiteService checkedSiteService;
    private final CheckedSiteRepository checkedSiteRepository;
    boolean checkIsPress = false;

    public ProfileController(ParserService parserService, CheckedSiteService checkedSiteService, CheckedSiteRepository checkedSiteRepository) {
        this.parserService = parserService;
        this.checkedSiteService = checkedSiteService;
        this.checkedSiteRepository = checkedSiteRepository;
    }

    private List<CheckedSite> checkedSites;

    @RequestMapping("/profile")
    public String getProfilePage(Model model) {

        checkedSites = checkedSiteService.getAllSites();
        model.addAttribute("checkedSites", checkedSites);
        return "profile";
    }


    @RequestMapping(value = "/profile", method = RequestMethod.POST, params = {"check"})
    public String saveMatch(@ModelAttribute("checkedSiteName") String checkedSiteName,@ModelAttribute("url") String url, Model model) {

        if (!url.equals("")){
            checkedSiteName = url;
        }

        String correctUrl = parserService.getCorrectUrl(checkedSiteName);
        boolean isEmpty = false;
        List<Site> sites = parserService.getSites(correctUrl);
        if (sites.size()!= 0 && sites.get(0).getName().equals("0")){
            model.addAttribute("unknownHost", true);
            return getProfilePage(model);
        }
        else {
            checkIsPress = true;
            if (sites.isEmpty()) isEmpty = true;
            model.addAttribute("sites", sites);
            model.addAttribute("isEmpty", isEmpty);
            model.addAttribute("correctUrl", correctUrl);
            model.addAttribute("checkIsPress", checkIsPress);
            model.addAttribute("checkedSiteName", checkedSiteName);
            return getProfilePage(model);
        }
    }


}
