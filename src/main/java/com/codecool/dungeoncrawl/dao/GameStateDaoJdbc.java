package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.model.GameState;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class GameStateDaoJdbc implements GameStateDao {

    private DataSource dataSource;
//    private PlayerDao playerDao;

    public GameStateDaoJdbc(DataSource dataSource, PlayerDao playerDao) {
        this.dataSource = dataSource;
//        this.playerDao = playerDao;
    }

    @Override
    public void add(GameState state) {
        System.out.println(state.getPlayer().getId());

        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO game_state (current_map, saved_at, player_id) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, state.getCurrentMap().toString());
            statement.setDate(2, state.getSavedAt());
            statement.setInt(3, state.getPlayer().getId());


            statement.executeUpdate();
//            ResultSet resultSet = statement.getGeneratedKeys();
//            resultSet.next();
//            state.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(GameState state) {

    }

    @Override
    public String get(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT current_map, saved_at FROM game_state WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            //            Object gameMap = (Object) map;
//            GameMap map = new GameMap(rs.getString(2));
//            author.setId(id);
            return rs.getString(2);
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading author with id: " + id, e);
        }
    }

    @Override
    public List<GameState> getAll() {
        return null;
    }
}
