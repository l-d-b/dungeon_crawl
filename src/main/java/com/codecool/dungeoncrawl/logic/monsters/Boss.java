package com.codecool.dungeoncrawl.logic.monsters;

import com.codecool.dungeoncrawl.logic.Cell;

public class Boss extends Monster{
    public Boss(Cell cell) {
        super(cell, 40, 20, "Boss");
    }

    @Override
    public String getTileName() {
        return null;
    }
}
