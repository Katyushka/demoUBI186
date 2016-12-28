package com.demoUBI186.domain;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by ekaterina on 13.12.2016.
 */
public class SiteCreateForm {

    @NotEmpty
    private String name = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
