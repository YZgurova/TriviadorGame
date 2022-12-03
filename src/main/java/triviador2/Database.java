package triviador2;

import com.google.gson.Gson;

import java.sql.*;

public class Database {
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/triviador?characterEncoding=utf8", "root", "root");
            statement=connection.createStatement();
//            createTablesIfNotExists();
        } catch(Exception e) {
            System.out.println("Problem with DB setup!" + e);
        }
    }

    public Game.State getLatestGameData() {
        return new Gson().fromJson(getJSONFromDB(), Game.State.class);
    }

    private String getJSONFromDB() {
        try {
            String query = "SELECT `data` from `tbl_game_statement` order by last_update ASC LIMIT 1";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                return resultSet.getString("data");
            }
        } catch (Exception e) {
            System.out.println("Problem with getJSONFromDB "+e);
        }

        return null;
    }

    public void truncateGameTable() {
        String query = "TRUNCATE tbl_game_statement";
        try {
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Problems with truncating game table: "+e);
            System.out.println(query);
        }

        executeQuery(query);
    }

    public void addFirstData(Game.State game) {
        String jo=new Gson().toJson(game);
        String query = "INSERT INTO `tbl_game_statement` (id, data, last_update) VALUES (1, ?, CURRENT_TIMESTAMP) ";
        try {
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1, jo);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Problems with adding updated data "+e);
            System.out.println(query);
        }
    }
    public void addUpdatedData(Game.State game) {
        String jo=new Gson().toJson(game);
//        String query = "INSERT INTO `tbl_game_statement` (id, data, last_update) VALUES (1, ?, CURRENT_TIMESTAMP) " +
//                "ON DUPLICATE KEY UPDATE data=?, last_update=CURRENT_TIMESTAMP";
        String query = "UPDATE `tbl_game_statement` SET id = 1 ,data = ?, last_update=CURRENT_TIMESTAMP";
        try {
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1, jo);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Problems with adding updated data "+e);
            System.out.println(query);
        }
    }

    private void executeQuery(String query) {
        try {
            statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("Problem with insert query" + e);
            System.out.println(query);
        }
    }
}
