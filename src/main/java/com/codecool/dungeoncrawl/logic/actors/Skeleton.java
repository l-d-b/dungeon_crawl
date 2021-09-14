package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends Actor {
    public Skeleton(Cell cell, int health) {
        super(cell, health);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}
