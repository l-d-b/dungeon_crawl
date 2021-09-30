package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.monsters.Skeleton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    GameMap gameMap;
    Player player;

    @BeforeEach
    public void setUpGame() {
        gameMap = new GameMap(3, 3, CellType.FLOOR);
        player = new Player(gameMap.getCell(1, 1));
    }

    @Test
    public void pickUpItemShouldPutItemToInventory() {

        ArrayList<CellType> inventory = player.getInventory();

        player.pickUpItem(CellType.SWORD);
        player.pickUpItem(CellType.KEY);
        player.pickUpItem(CellType.SHIELD);
        ArrayList<CellType> expected = new ArrayList<>(List.of(CellType.SWORD, CellType.KEY, CellType.SHIELD));

        assertEquals(expected, inventory);
    }

}
