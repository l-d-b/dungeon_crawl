package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;

import java.util.ArrayList;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health;
    private int attack;
    private String name;

    public Actor(Cell cell, int health, int attack, String name) {
        this.name = name;
        this.attack = attack;
        this.health = health;
        this.cell = cell;
        this.cell.setActor(this);

    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;

    }


    public Cell cellCheck(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        return nextCell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGyozo(String name){
        this.name = name;
    }


    public int getHealth() {
        return health;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getY() {
        return cell.getY();
    }
}
