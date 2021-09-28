package com.codecool.dungeoncrawl.logic.monsters;

import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends Monster {
    public Skeleton(Cell cell) {
        super(cell, 10,5,"Skeleton");
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}
