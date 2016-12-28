package com.demoUBI186.repository;

import com.demoUBI186.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ekaterina on 13.12.2016.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByLogin(String login);
}
