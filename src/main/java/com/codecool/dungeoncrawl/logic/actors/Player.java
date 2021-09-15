package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;

import java.util.ArrayList;

public class Player extends Actor {
    private CellType cellType;
    private ArrayList<CellType> inventory = new ArrayList<CellType>();

    public Player(Cell cell) {
        super(cell, 10,5,"Player");
    }

    public String getTileName() {
        return "player";
    }

    public ArrayList<CellType> getInventory() {
        return inventory;
    }


    public void goThroughDoor() {

    }

    public void pickUpItem(CellType item, GameMap map){
        System.out.println(item);
        this.inventory.add(item);
        if(item == CellType.SWORD) {
            int health = this.getHealth();
            health += 3;
            setHealth(health);
        }
        map.getCell(map.getPlayer().getX(), map.getPlayer().getY()).setType(CellType.FLOOR);
        System.out.println(inventory);

    }
}
