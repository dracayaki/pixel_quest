package model.DAO;

import model.Object.Level;
import model.Object.RecordEntry;
import utils.connectorBBDD;

import java.lang.ref.PhantomReference;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LevelDAO {

    public Level getLevelByNumber (int numLevel){
        String query = "select * from level where num_level = ?";

        try(Connection con = connectorBBDD.getConnection()){
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, numLevel);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                return new Level(
                        rs.getInt("num_level"),
                        rs.getInt("width"),
                        rs.getInt("height"),
                        rs.getInt("numberCoins"),
                        rs.getInt("numberWalls"),
                        rs.getString("map"),
                        rs.getInt("time_limit")
                );
            }
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Error getting the level info");
        }

        return null;
    }

    public static void insertRecord(int userId, int levelId, int points, int timeUsed) {
        String sql = "INSERT INTO record (user_id, level_id, point_record, time_used) VALUES (?, ?, ?, ?)";

        try (Connection con = connectorBBDD.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.setInt(2, levelId);
            pstmt.setInt(3, points);
            pstmt.setInt(4, timeUsed);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<RecordEntry> getTopRecords(int levelId) {
        List<RecordEntry> records = new ArrayList<>();

        String sql = "SELECT u.username, r.point_record, r.time_used " +
                "FROM record r JOIN user u ON r.user_id = u.id_user " +
                "WHERE r.level_id = ? " +
                "ORDER BY r.point_record DESC, r.time_used ASC";

        try (Connection con = connectorBBDD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, levelId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String username = rs.getString("username");
                int points = rs.getInt("point_record");
                int time = rs.getInt("time_used");

                records.add(new RecordEntry(username, points, time));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return records;
    }

}
