package Persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Helper.User;

public class UserDAO {

    public User getUserByUsername(String username) {
        User user = null;
        String sql = "SELECT id, username, password FROM User WHERE username = ?";

        try (Connection con = DBConfig.getCon();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String uname = rs.getString("username");
                String pwd = rs.getString("password");
                user = new User(id, uname, pwd);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
}
