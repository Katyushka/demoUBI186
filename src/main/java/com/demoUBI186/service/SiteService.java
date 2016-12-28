package com.demoUBI186.service;

import com.demoUBI186.domain.Site;
import com.demoUBI186.domain.SiteCreateForm;
import com.demoUBI186.repository.SiteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ekaterina on 13.12.2016.
 */

@Service
@Transactional
public class SiteService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private SiteRepository siteRepository;


    public List<Site> getAllSites() {
        log.debug("Getting all sites");
        List<Site> sites = new ArrayList<Site>();
        for (Site site : siteRepository.findAll()) {
            sites.add(site);
        }
        return sites;
    }


    public Site create(SiteCreateForm form) {
        log.debug("Creating new site");
        Site site = new Site();
        site.setName(form.getName());
        return siteRepository.save(site);
    }

    @Transactional
    public Site save(Site site) {
        log.debug("Saving site");
        return siteRepository.save(site);
    }

    public void delete(Site site) {
        log.debug("Deleting site");
        siteRepository.delete(site);
    }


    public Site getSitesById(Long id) {
        log.debug("Getting site={}", id);
        return siteRepository.findOne(id);
    }

}
