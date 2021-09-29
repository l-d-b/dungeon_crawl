package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;

public class InventoryModel extends BaseModel{
    private int sword;
    private int shield;

//    public InventoryModel(Player player) {
//        this.sword = for(CellType item: player.getInventory()){
//
//        };
//        this.shield = shield;
//    }

    public void setSword(int sword) {
        this.sword = sword;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }

    public int getSword() {
        return sword;
    }

    public int getShield() {
        return shield;
    }
}
