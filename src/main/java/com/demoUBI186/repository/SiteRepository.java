package com.demoUBI186.repository;

import com.demoUBI186.domain.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ekaterina on 13.12.2016.
 */

@Repository
public interface SiteRepository extends JpaRepository<Site, Long> {

}
