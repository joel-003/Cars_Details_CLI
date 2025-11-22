package com.github.joel003.dao;

import com.github.joel003.entity.UserDetail;
import com.github.joel003.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private final Connection con = DBConnection.getConnection();

    public String searchEmail(String email) throws SQLException {

        String query = "SELECT u_email FROM userscars WHERE u_email = ?";

        try (PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString(1);
            }
        }
        return null;
    }

    public boolean registerUser(UserDetail userd) throws SQLException {

        String query = " INSERT into userscars values(?,?,?,?,?,?)";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, userd.getU_id());
            ps.setString(2, userd.getU_name());
            ps.setString(3, userd.getU_email());
            ps.setString(4, userd.getU_phoneNumber());
            ps.setString(5, userd.getU_address());
            ps.setString(6, userd.getPwd());

            int res = ps.executeUpdate();

            return res > 0;
        }
    }

    public String verifyUser(String email, String pwd) throws SQLException {

        String query = "SELECT u_name FROM userscars WHERE u_email = ? AND u_password = ?";

        try (PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, email);
            ps.setString(2, pwd);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString(1);
            }
        }
        return null;

    }

    public boolean changePassword(String email, String pwd) throws SQLException {

        String query = "UPDATE userscars SET u_password = ? WHERE u_email = ?";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, pwd);
            ps.setString(2, email);

            int res = ps.executeUpdate();

            return res > 0;
        }
    }
}
