package com.codecool.dungeoncrawl.logic.monsters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;

public class Skeleton extends Monster {
    public Skeleton(Cell cell) {
        super(cell, 5,5,"Skeleton");
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}
