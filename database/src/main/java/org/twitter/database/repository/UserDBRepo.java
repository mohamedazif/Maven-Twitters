//package org.twitter.database.repository;
//
//import org.twitter.database.config.DBConnect;
//
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.Collection;
//
//public final class UserDBRepo {
//
//    private UserDBRepo() { }
//
//    public static boolean addRegisteredUser(final User user) {
//        final Collection<String> id = new ArrayList<>();
//        final Collection<String> email = new ArrayList<>();
//        final String checkId = "SELECT id FROM users";
//        final String checkEmail = "SELECT email FROM users";
//
//        try (Connection con = DBConnect.getConnection();
//             Statement statement = con.createStatement();
//             ResultSet rs = statement.executeQuery(checkId);
//             ResultSet rs1 = statement.executeQuery(checkEmail)) {
//
//            while (rs.next()) {
//                id.add(rs.getString("id"));
//            }
//
//            while (rs1.next()) {
//                email.add(rs1.getString("email"));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        // Check if userId is already in use
//        if (id.contains(user.getId())) {
//            System.err.println("User ID already exists!");
//            return false;
//        }
//
//        // Check if email is already in use
//        if (email.contains(user.getEmail())) {
//            System.err.println("Email is already registered!");
//            return false;
//        }
//
//        final String register = """
//                                INSERT INTO
//                                users (id, email, name, password, age, bio)
//                                values (?, ?, ?, ?, ?, ?)""";
//
//        try (Connection con = DBConnect.getConnection();
//             PreparedStatement pstmt = con.prepareStatement(register)) {
//
//            pstmt.setString(1, user.getId());
//            pstmt.setString(2, user.getEmail());
//            pstmt.setString(3, user.getUserName());
//            pstmt.setString(4, user.getPassword());
//            pstmt.setInt(5, user.getAge());
//            pstmt.setString(6, user.getBio());
//            pstmt.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return true;
//    }
//
//    public static User getSpecificUser(final String id) {
//        final String getUser = "SELECT * FROM users WHERE id = ?";
//
//        try (Connection con = DBConnect.getConnection();
//             PreparedStatement ps = con.prepareStatement(getUser)) {
//
//            ps.setString(1, id);
//            ResultSet rs = ps.executeQuery();
//
//            if (rs.next()) {
//                return new User(
//                        rs.getString("id"),
//                        rs.getString("email"),
//                        rs.getString("name"),
//                        rs.getString("password"),
//                        rs.getInt("age"),
//                        rs.getString("bio")
//                );
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    public static Collection<User> getAllUsers() {
//        final Collection<User> usersList = new ArrayList<>();
//        final String allUsers = "SELECT * FROM users";
//
//        try (Connection con = DBConnect.getConnection();
//             Statement stmt = con.createStatement();
//             ResultSet res = stmt.executeQuery(allUsers)){
//
//            while (res.next()) {
//                usersList.add(new User(
//                        res.getString("id"),
//                        res.getString("email"),
//                        res.getString("name"),
//                        res.getString("password"),
//                        res.getInt("age"),
//                        res.getString("bio")
//                ));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return usersList;
//    }
//}
