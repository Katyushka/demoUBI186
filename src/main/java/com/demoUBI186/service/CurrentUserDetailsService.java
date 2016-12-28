package com.demoUBI186.service;

import com.demoUBI186.domain.CurrentUser;
import com.demoUBI186.domain.User;
import com.demoUBI186.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by ekaterina on 13.12.2016.
 */
@Service
public class CurrentUserDetailsService implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentUserDetailsService.class);

    private final UserRepository userRepository;

    @Autowired
    public CurrentUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.debug("Authenticating user with name={}", username);
        User user = userRepository.findByLogin(username);
        return new CurrentUser(user);
    }
}
