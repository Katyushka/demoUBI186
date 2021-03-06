package com.demoUBI186.domain;

import org.springframework.security.core.authority.AuthorityUtils;

/**
 * Created by ekaterina on 13.12.2016.
 */
public class CurrentUser extends org.springframework.security.core.userdetails.User {
    private User user;

    public CurrentUser(User user) {
        super(user.getLogin(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
        this.user = user;
    }

    public User getUser(){
        return user;
    }

    public Long getId(){
        return user.getId();
    }

    public Role getRole(){
        return user.getRole();
    }
}
