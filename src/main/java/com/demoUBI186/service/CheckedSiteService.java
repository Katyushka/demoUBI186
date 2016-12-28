package com.demoUBI186.service;

import com.demoUBI186.domain.CheckedSite;
import com.demoUBI186.repository.CheckedSiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ekaterina on 27.12.2016.
 */

@Service
@Transactional
public class CheckedSiteService {

    @Autowired
    private CheckedSiteRepository checkedSiteRepository;

    public List<CheckedSite> getAllSites() {
        List<CheckedSite> sites = new ArrayList<CheckedSite>();
        for (CheckedSite site : checkedSiteRepository.findAll()) {
            sites.add(site);
        }
        return sites;
    }

    @Transactional
    public CheckedSite save(CheckedSite site) {
        return checkedSiteRepository.save(site);
    }

    public void delete(CheckedSite site) {
        checkedSiteRepository.delete(site);
    }


    public CheckedSite getSitesById(Long id) {
        return checkedSiteRepository.findOne(id);
    }

}
