package com.demoUBI186.repository;

import com.demoUBI186.domain.CheckedSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ekaterina on 27.12.2016.
 */

@Repository
public interface CheckedSiteRepository extends JpaRepository<CheckedSite, Long>{
}
