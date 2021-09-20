package com.codecool.dungeoncrawl.logic.monsters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;

public abstract class Monster extends Actor {

    public Monster(Cell cell, int health, int attack, String name) {
        super(cell, health, attack, name);
        this.cell.setMonster(this);
    }
}
