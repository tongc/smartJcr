package com.goda5.smartjcr.model;

import com.goda5.smartjcr.JcrNode;

import java.util.List;

public class Zoo {
    private final List<Animal> animals;

    public Zoo(List<Animal> animals) {
        this.animals = animals;
    }

    @JcrNode
    public List<Animal> getAnimals() {
        return animals;
    }
}
