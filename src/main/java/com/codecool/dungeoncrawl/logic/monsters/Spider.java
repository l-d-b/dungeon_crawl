package com.codecool.dungeoncrawl.logic.monsters;

import com.codecool.dungeoncrawl.logic.Cell;

public class Spider extends Monster{
    public Spider(Cell cell) {
        super("Spider", 15, cell);
    }

    @Override
    public String getTileName() {
        return null;
    }
}
