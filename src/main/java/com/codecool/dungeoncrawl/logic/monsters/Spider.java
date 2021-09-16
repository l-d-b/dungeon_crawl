package com.codecool.dungeoncrawl.logic.monsters;

import com.codecool.dungeoncrawl.logic.Cell;

public class Spider extends Monster{
    public Spider(Cell cell) {
        super(cell, 10,15,"Spider");
    }

    @Override
    public String getTileName() {
        return null;
    }
}
