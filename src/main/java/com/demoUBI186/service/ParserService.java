package com.demoUBI186.service;

import com.demoUBI186.domain.Site;
import com.demoUBI186.repository.SiteRepository;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ekaterina on 13.12.2016.
 */


@Service
@Transactional
public class ParserService {

    private final SiteRepository siteRepository;


    public ParserService(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }
    Document doc;

    public List<Site> getSites(String url){
        Boolean flag = connect(url);

        List<Site> sites;
        List<Site> currentSites = new ArrayList<>();
        if (flag){
            Site site = new Site();
            site.setName("0");
            site.setId((long) 1000);
            currentSites.add(site);
            return currentSites;
        }
        sites = siteRepository.findAll();
        currentSites.addAll(sites.stream().filter(site -> doc.html().contains(site.getName())).collect(Collectors.toList()));
        return currentSites;
    }


    private Boolean connect(String url) {

        try{

            try {
                Connection.Response response1 = Jsoup.connect(url).timeout(0).execute();
            } catch (java.net.UnknownHostException e){
                Boolean flag = true;
                return true;
            }

            doc = Jsoup.connect(url).get();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean isCorrectUrl(String url){

        return true;
    }

    public String getCorrectUrl(String url){
        if (url.contains("http://") || url.contains("https://")) return url;
        return  "http://"+url;
    }



}
