package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;

import java.util.ArrayList;

public class Player extends Actor {
    private final ArrayList<CellType> inventory = new ArrayList<>();

    public Player(Cell cell, int health, int attack) {
        super(cell, health, attack, "Player");
    }

    public String getTileName() {
        if (inventory.contains(CellType.SWORD) && inventory.contains(CellType.SHIELD)) {
            return "player-sword-shield";
        } else if (inventory.contains(CellType.SWORD)) {
            return "player-sword";
        } else {
            return "player";
        }
    }

    public ArrayList<CellType> getInventory() {
        return inventory;
    }


    public void pickUpItem(CellType item) {
        if (!(item == CellType.HEAL)) {
            this.inventory.add(item);
        }
        if (item == CellType.SWORD) {
            int attack = getAttack() + 5;
            setAttack(attack);
        } else if (item == CellType.HEAL) {
            int health;
            if (this.getHealth() + 15 > 100) {
                health = this.getHealth() + (100 - this.getHealth());
            } else {
                health = this.getHealth() + 15;
            }
            setHealth(health);

        }
        getCell().setType(CellType.FLOOR);
    }

    @Override
    public String toString() {
        return "Player{" +
                "cell=" + cell +
                ", inventory=" + inventory +
                "} " + super.toString();
    }
}
