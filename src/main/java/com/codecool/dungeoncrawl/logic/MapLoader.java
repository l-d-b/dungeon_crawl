package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;

import com.codecool.dungeoncrawl.logic.monsters.Ghost;
import com.codecool.dungeoncrawl.logic.monsters.Golem;
import com.codecool.dungeoncrawl.logic.monsters.Skeleton;
import com.codecool.dungeoncrawl.logic.monsters.Spider;

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
                            break;
                        case '$':
                            cell.setType(CellType.SHIELD);
                            break;
                        case '*':
                            cell.setType(CellType.KEY);
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
                            break;
                        case 't':
                            cell.setType(CellType.TREE);
                            break;
                        case 'B':
                            cell.setType(CellType.BOSS);
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
