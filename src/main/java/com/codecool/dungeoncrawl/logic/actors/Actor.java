package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.monsters.Monster;

public abstract class Actor implements Drawable {
    protected Cell cell;
    private int health;
    private int attack;
    private String name;
    private int power;

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

    public void fight(Monster monster){
        System.out.println(monster.getAttack());
       int monsterHealth = monster.getHealth();
       int monsterAttack = monster.getAttack();
       int playerAttack = this.attack;
       int playerHealth = this.health;
       int round = 1;
       while((monsterHealth >= 1) && ( playerHealth >= 1)){
            switch (round){
                case 1:
                    int actuelMonsterHealth = monsterHealth - playerAttack;
                    monsterHealth = actuelMonsterHealth;
                    monster.setHealth(actuelMonsterHealth);
                    round++;
                    break;
                case 2:
                    int actuelPlayerHealth = playerHealth - monsterAttack;
                    playerHealth = actuelPlayerHealth;
                    this.setHealth(actuelPlayerHealth);
                    round--;
                    break;
            }
       }
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
