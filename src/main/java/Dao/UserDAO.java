package Dao;

import Database.Connection;
import Model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public static boolean isExists(String email) throws SQLException {
        java.sql.Connection conn = Connection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select email from users");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            if (rs.getString("email").equals(email)) {
                return true;
            }
        }
        return false;
    }

    public static int saveUser(User user) throws SQLException {
        java.sql.Connection conn = Connection.getConnection();
        PreparedStatement ps = conn.prepareStatement("insert into users values(default,?,?)");
        ps.setString(1, user.getName().toLowerCase());
        ps.setString(2,user.getEmail().toLowerCase());
        return ps.executeUpdate();
    }

    public static String getName(String email) throws SQLException {
        java.sql.Connection conn = Connection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select * from users where email=?");
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getString("name");
    }
}
