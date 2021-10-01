package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;

import com.codecool.dungeoncrawl.logic.GameMap;

import java.io.Serializable;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class GameState extends BaseModel implements Serializable {
    private String savedAt;
    private GameMap currentMap;
    private List<String> discoveredMaps = new ArrayList<>();
    private PlayerModel player;

    public GameState(PlayerModel playerModel) {
        this.player = playerModel;
    }

    public GameState(GameMap currentMap) {
        this.currentMap = currentMap;
    }

    public GameState(GameMap gameState, PlayerModel playerModel) {
        this.savedAt = gameState.getCurrentDate();
        this.currentMap = gameState;
        this.player = playerModel;
    }


    public String getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(String savedAt) {
        this.savedAt = savedAt;
    }


    public List<String> getDiscoveredMaps() {
        return discoveredMaps;
    }

    public void addDiscoveredMap(String map) {
        this.discoveredMaps.add(map);
    }

    public PlayerModel getPlayer() {
        return player;
    }

    public void setPlayer(PlayerModel player) {
        this.player = player;
    }

    public GameMap getCurrentMap() {
        return currentMap;
    }
}
