package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;

import com.codecool.dungeoncrawl.logic.items.Heal;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.items.Shield;
import com.codecool.dungeoncrawl.logic.items.Sword;
import com.codecool.dungeoncrawl.logic.monsters.*;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {

    public static GameMap loadMap(String currentMap) {
        InputStream is = MapLoader.class.getResourceAsStream(currentMap);
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.SKELETON);
                            new Skeleton(cell);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case 'ß':
                            cell.setType(CellType.SWORD);
                            new Sword(cell);
                            break;
                        case '$':
                            cell.setType(CellType.SHIELD);
                            new Shield(cell);
                            break;
                        case '*':
                            cell.setType(CellType.KEY);
                            new Key(cell);
                            break;
                        case '|':
                            cell.setType(CellType.OPENED_DOOR);
                            break;
                        case '_':
                            cell.setType(CellType.CLOSED_DOOR);
                            break;
                        case 'p':
                            cell.setType(CellType.SPIDER);
                            new Spider(cell);
                            break;
                        case 'g':
                            cell.setType(CellType.GOLEM);
                            new Golem(cell);
                            break;
                        case '¤':
                            cell.setType(CellType.GHOST);
                            new Ghost(cell);
                            break;
                        case 'h':
                            cell.setType(CellType.HEAL);
                            new Heal(cell);
                            break;
                        case 't':
                            cell.setType(CellType.TREE);
                            break;
                        case 'b':
                            cell.setType(CellType.BOSS);
                            new Boss(cell);
                            break;
                        case 'o':
                            cell.setType(CellType.OVER);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
