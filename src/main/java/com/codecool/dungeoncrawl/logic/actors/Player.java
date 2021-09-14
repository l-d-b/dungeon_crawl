package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Sword;

import java.util.ArrayList;

public class Player extends Actor {
    private CellType cellType;
    private ArrayList<Item> inventory = new ArrayList<>();

    public Player(Cell cell, int health) {
        super(cell, health);
    }

    public String getTileName() {
        return "player";
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }


    public void pickUpItem(Item item){
        this.inventory.add(item);
        if(item instanceof Sword) {
            int health = getHealth();
            health += 3;
            setHealth(health);
        }
        System.out.println(item);
    }
}
