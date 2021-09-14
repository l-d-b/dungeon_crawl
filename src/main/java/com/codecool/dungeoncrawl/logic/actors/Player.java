package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

import java.util.ArrayList;

public class Player extends Actor {

    private ArrayList<Item> inventory = new ArrayList<>();

    public Player(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "player";
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void pickUpItem(Item item){
        this.inventory.add(item);
        if(item instanceof Sword){
            power += ((Sword) item).addPower();
        } else if(item instanceof redPotion){
            health += ((redPotion) item).addHealth();
        }
        item.getCell().cell.setType(CellType.FLOOR);
    }
}
