package com.goda5.smartjcr.model;

import com.goda5.smartjcr.JcrProperty;

public abstract class Animal {
    private final String name;

    public Animal(String name) {
        this.name = name;
    }

    @JcrProperty
    public String getName() {
        return name;
    }
}
