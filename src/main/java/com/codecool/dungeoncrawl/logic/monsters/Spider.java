package com.codecool.dungeoncrawl.logic.monsters;

import com.codecool.dungeoncrawl.logic.Cell;

public class Spider extends Monster{
    public Spider(String name, int health, Cell cell) {
        super(name, health, cell);
    }

    @Override
    public String getTileName() {
        return null;
    }
}
