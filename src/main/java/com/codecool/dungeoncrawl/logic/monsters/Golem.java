package com.codecool.dungeoncrawl.logic.monsters;

import com.codecool.dungeoncrawl.logic.Cell;

public class Golem extends Monster{
    public Golem(Cell cell) {
        super(cell, 25,10,"Golem");
    }

    @Override
    public String getTileName() {
        return "golem";
    }
}
