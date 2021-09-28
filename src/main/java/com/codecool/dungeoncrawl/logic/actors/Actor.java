package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health = 10;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
import com.codecool.dungeoncrawl.logic.monsters.Monster;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public abstract class Actor implements Drawable {
    protected Cell cell;
    private int health;
    private int attack;
    private final String name;

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

    public void fight(Monster monster) {

        int monsterHealth = monster.getHealth();
        int monsterAttack = monster.getAttack();
        String monsterName = monster.getName();
        int playerAttack = this.attack;
        int playerHealth = this.health;

        int actualMonsterHealth = monsterHealth - playerAttack;
        monster.setHealth(actualMonsterHealth);

        if (monsterName.equals("Boss") && monsterHealth <= 0) {
            endGame(this);
        }

        int actualPlayerHealth = playerHealth - monsterAttack;
        this.setHealth(actualPlayerHealth);

        if (actualPlayerHealth <= 0) {
            endGame(monster);
        }
    }

    public Cell cellCheck(int dx, int dy) {
        return cell.getNeighbor(dx, dy);
    }

    public void endGame(Actor actor) {
        JFrame parent = new JFrame();
        if (actor.cell.isMonster()) {
            JOptionPane.showMessageDialog(parent, "Game over! You are dead!");
        } else {
            JOptionPane.showMessageDialog(parent, "You have finished it HERO!");
        }
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
