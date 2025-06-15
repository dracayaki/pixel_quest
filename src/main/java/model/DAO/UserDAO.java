package model.DAO;

import model.Object.User;
import utils.connectorBBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public User getUserBBDD(String username, String password) {

        String query = "select * from user where username=? and password=?";

        try (Connection con = connectorBBDD.getConnection()) {

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id_user"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("totalPoints"),
                        rs.getInt("lastLevelPlayed")

                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Trouble getting users from tha database");
        }
        return null;
    }

    public static boolean insertUser(User user) {
        String query = "insert into user(username, password) values(?,?)";

        try (Connection con = connectorBBDD.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate")) {
                System.out.println("The username already exists");
            } else {
                e.printStackTrace();
            }
            return false;
        }
    }

    public boolean usernameExists(String username) {

        String query = "select * from user where username=?";

        try (Connection con = connectorBBDD.getConnection()){
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public void updateLastLevelPlayed(int userId, int newLevel) {
        String query = "update user set lastLevelPlayed=? where id_user=?";

        try (Connection con = connectorBBDD.getConnection()){

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, newLevel);
            ps.setInt(2, userId);

            ps.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("We couldn't update the last level played by user");
        }
    }
}