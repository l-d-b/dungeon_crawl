package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.GameState;

import java.util.List;

public interface InventroyDao {
    void add(Player player);
    void update(Player player);
    GameState get(int id);
    List<GameState> getAll();
}
