package com.demoUBI186.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ekaterina on 13.12.2016.
 */

@Entity
@Table(name = "sites")
public class Site implements Serializable{
    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
