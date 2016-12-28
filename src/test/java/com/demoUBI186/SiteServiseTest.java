package com.demoUBI186;

import com.demoUBI186.domain.Role;
import com.demoUBI186.domain.Site;
import com.demoUBI186.domain.SiteCreateForm;

import com.demoUBI186.repository.SiteRepository;
import com.demoUBI186.service.SiteService;
import com.demoUBI186.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by ekaterina on 22.12.2016.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoUBI186App.class)
public class SiteServiseTest {

    @Autowired
    private SiteService siteService;

    @Autowired
    private SiteRepository siteRepository;

    @Test
    public void testCreate() throws Exception {
        SiteCreateForm siteCreateForm = new SiteCreateForm();
        siteCreateForm.setName("osu.ru");
        Site testSite = siteService.create(siteCreateForm);
        assertThat(testSite.getName(), is("osu.ru"));
    }

    @Test
    public void testSave() throws Exception {
        Site site = new Site();
        site.setName("mail.ru");
        siteService.save(site);
        Site testSite = siteService.getSitesById(siteRepository.count());
        assertThat(testSite.getName(), is("mail.ru"));
    }


}
