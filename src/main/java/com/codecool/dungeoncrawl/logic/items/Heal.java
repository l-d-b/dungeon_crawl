package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Heal extends Item{
    public Heal(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "red-potion";
    }
}
