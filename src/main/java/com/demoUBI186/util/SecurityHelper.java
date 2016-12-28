package com.demoUBI186.util;

import com.demoUBI186.domain.CurrentUser;
import com.demoUBI186.domain.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by ekaterina on 18.12.2016.
 */
public class SecurityHelper {
    public static User getCurrentUser() {
        if (SecurityContextHolder.getContext().getAuthentication() == null){
            return null;
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            CurrentUser currentUser = (CurrentUser) principal;
            return currentUser.getUser();
        }
        return null;
    }
}
