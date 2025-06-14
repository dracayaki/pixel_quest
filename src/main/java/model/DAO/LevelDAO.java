package model.DAO;

import model.Object.Level;
import utils.connectorBBDD;

import java.lang.ref.PhantomReference;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
