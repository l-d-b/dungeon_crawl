package com.codecool.dungeoncrawl.logic.monsters;

import com.codecool.dungeoncrawl.logic.Cell;

public class Ghost extends Monster{
    public Ghost(String name, int health, Cell cell) {
        super(name, health, cell);
    }

    @Override
    public String getTileName() {
        return "spider";
    }
}
