package com.codecool.dungeoncrawl.logic.monsters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Monster implements Drawable {
    private String name;
    private int health;
    private Cell cell;

    public Monster(String name, int health, Cell cell) {
        this.name = name;
        this.health = health;
        this.cell = cell;
        this.cell.setMonster(this);
    }
}
