package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Sword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    GameMap gameMap;
    Player player;

    @BeforeEach
    public void setUpGame() {
        gameMap = new GameMap(3, 3, CellType.FLOOR);
        player = new Player(gameMap.getCell(1, 1));
    }

    @Test
    void isItemShouldBeTrueForItem () {
        Cell cell = gameMap.getCell(1,1);
        cell.setType(CellType.SWORD);
        assertTrue(cell.isItem());
        assertFalse(gameMap.getCell(0,0).isItem());
    }

    @Test
    void getNeighbor() {
        Cell cell = gameMap.getCell(1, 1);
        Cell neighbor = cell.getNeighbor(-1, 0);
        assertEquals(0, neighbor.getX());
        assertEquals(1, neighbor.getY());
    }

    @Test
    void cellOnEdgeHasNoNeighbor() {
        Cell cell = gameMap.getCell(1, 0);
        assertEquals(null, cell.getNeighbor(0, -1));

        cell = gameMap.getCell(1, 2);
        assertEquals(null, cell.getNeighbor(0, 1));
    }
}