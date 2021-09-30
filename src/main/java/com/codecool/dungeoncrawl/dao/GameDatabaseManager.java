package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class GameDatabaseManager {
    private PlayerDao playerDao;
    private GameStateDao gameStateDao;
//    private PlayerModel playerModel;

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
        gameStateDao = new GameStateDaoJdbc(dataSource, playerDao);
    }

    public PlayerModel savePlayer(PlayerModel player) {
//        PlayerModel model = new PlayerModel(player);
        playerDao.add(player);
        return player;
    }

    public void saveGameStatus(GameMap state, PlayerModel playerModel) {
        GameState gameState = new GameState(state, playerModel);
        gameStateDao.add(gameState);
    }

    public String getGameStatus(PlayerModel playerModel){
//        GameState gameState = new GameState(playerModel);
        return gameStateDao.get(playerModel.getId());
    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String dbName = System.getenv("databaseName");
        String user = System.getenv("user");
        String password = System.getenv("password");

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }
}
