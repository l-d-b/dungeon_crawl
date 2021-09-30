//package com.codecool.dungeoncrawl.dao;
//
//import com.codecool.dungeoncrawl.logic.actors.Player;
//import com.codecool.dungeoncrawl.model.GameState;
//import com.codecool.dungeoncrawl.model.PlayerModel;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.List;
//
//public class InventroyDaoJdbc implements GameStateDao{
//
//    private DataSource dataSource;
//    private PlayerDao playerDao;
//
//    public InventroyDaoJdbc(DataSource dataSource, PlayerDao playerDao) {
//        this.dataSource = dataSource;
//        this.playerDao = playerDao;
//    }
//
//    @Override
//    public void add(PlayerModel player) {
//        try (Connection conn = dataSource.getConnection()) {
//            String sql = "INSERT INTO inventory (player_id, inventory) VALUES (?, ?)";
//            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            statement.setInt(1, player.getId());
//            statement.setString(2, player.getInventory());
//            statement.executeUpdate();
////            ResultSet resultSet = statement.getGeneratedKeys();
////            resultSet.next();
////            state.setId(resultSet.getInt(1));
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public void update(GameState state) {
//
//    }
//
//    @Override
//    public GameState get(int id) {
//        return null;
//    }
//
//    @Override
//    public List<GameState> getAll() {
//        return null;
//    }
//}
