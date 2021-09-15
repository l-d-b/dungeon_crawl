package com.codecool.dungeoncrawl.logic.monsters;

import com.codecool.dungeoncrawl.logic.Cell;

public class Golem extends Monster{
    public Golem(String name, int health, Cell cell) {
        super(name, health, cell);
    }

    @Override
    public String getTileName() {
        return null;
    }
}
