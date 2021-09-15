package com.codecool.dungeoncrawl.logic.monsters;

import com.codecool.dungeoncrawl.logic.Cell;

public class Golem extends Monster{
    public Golem(Cell cell) {
        super(cell, 10,5,"Golem");
    }

    @Override
    public String getTileName() {
        return "golem";
    }
}
