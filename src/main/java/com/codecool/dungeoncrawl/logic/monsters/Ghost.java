package com.codecool.dungeoncrawl.logic.monsters;

import com.codecool.dungeoncrawl.logic.Cell;

public class Ghost extends Monster{
    public Ghost(Cell cell) {
        super(cell, 10,5,"Player");
    }

    @Override
    public String getTileName() {
        return "ghost";
    }
}
