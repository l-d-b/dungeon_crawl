package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.ArrayList;

public class InventoryModel extends BaseModel{
    private ArrayList<CellType> inventory;

    public InventoryModel(Player player){
        this.inventory = player.getInventory();
    }

    public void setInventory(ArrayList<CellType> inventory) {
        this.inventory = inventory;
    }

    public ArrayList<CellType> getInventory() {
        return inventory;
    }
}
