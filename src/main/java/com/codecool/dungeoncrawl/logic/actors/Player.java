package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;

import java.util.ArrayList;

public class Player extends Actor {
    private CellType cellType;
    private ArrayList<CellType> inventory = new ArrayList<CellType>();

    public Player(Cell cell) {
        super(cell, 100, 15, "Player");
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


    public void goThroughDoor() {

    }

    public void pickUpItem(CellType item, GameMap map, int maxhealth) {
        if (!(item == CellType.HEAL)) {
            this.inventory.add(item);
        }
        if (item == CellType.SWORD) {
            int attack = getAttack() + 5;
            setAttack(attack);
        } else if (item == CellType.HEAL) {
            if (this.getHealth() + 15 > 100) {
                int health = this.getHealth() + (100 - this.getHealth());
                setHealth(health);
            } else {
                int health = this.getHealth() + 15;
                setHealth(health);
            }

        }
        map.getCell(map.getPlayer().getX(), map.getPlayer().getY()).setType(CellType.FLOOR);
    }
}
