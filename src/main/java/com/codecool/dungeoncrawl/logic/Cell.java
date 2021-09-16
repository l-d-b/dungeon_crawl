package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.monsters.Monster;

public class Cell implements Drawable {
    public Cell cell;
    private CellType type;
    private Actor actor;
    private Item item;
    private Monster monster;
    private GameMap gameMap;
    private int x, y;

    Cell(GameMap gameMap, int x, int y, CellType type) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Actor getActor() {
        return actor;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setMonster(Monster monster){
        this.monster = monster;
    }

    public Monster getMonster() {
        return monster;
    }

    public Item getItem() {
        return item;
    }

    public Cell getNeighbor(int dx, int dy) {
        return gameMap.getCell(x + dx, y + dy);
    }

    @Override
    public String getTileName() {
        return type.getTileName();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isItem() {
        return this.type.equals(CellType.SWORD) || this.type.equals(CellType.SHIELD)
                || this.type.equals(CellType.KEY) || this.type.equals(CellType.HEAL);
    }

    public boolean isMonster() {
        return this.type.equals(CellType.GOLEM) || this.type.equals(CellType.GHOST)
                || this.type.equals(CellType.SKELETON) || this.type.equals(CellType.SPIDER) || this.type.equals(CellType.BOSS);
    }


    public boolean isClosedDoor() {
        return this.type.equals(CellType.CLOSED_DOOR);
    }

    public boolean isOpenDoor() {
        return this.type.equals(CellType.OPENED_DOOR);
    }

}
