package com.example.tableview.services;

import com.example.tableview.config.Database;
import com.example.tableview.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final Connection connection;
    private PreparedStatement statement;
    private ResultSet result;

    public UserService() {
        this.connection = Database.getConnection();
    }

    public List<User> getUsers() {
        String query = "SELECT * FROM user";
        List<User> users = new ArrayList<>();

        try {
            this.statement = connection.prepareStatement(query);
            this.result = statement.executeQuery();

            while (result.next()) {
                users.add(new User(
                        result.getLong(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getString(6)
                ));
            }

            return users;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public User getUserById(Long id) {
        String query = "SELECT * FROM user WHERE id = ?";

        try {
            this.statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            this.result = statement.executeQuery();
            User user = null;
            if (result.next()) {
                user = new User(
                        result.getLong(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getString(6)
                );
            }

            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public User getUserByEmail(String email) {
        String query = "SELECT * FROM user WHERE email = ?";

        try {
            this.statement = connection.prepareStatement(query);
            statement.setString(1, email);
            this.result = statement.executeQuery();
            User user = null;
            if (result.next()) {
                user = new User(
                        result.getLong(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getString(6)
                );
            }

            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public User addUser(User user) {
        String query = "INSERT INTO user(username, email, password, department, gender) VALUES(?, ?, ?, ?, ?)";

        try {
            this.statement = connection.prepareStatement(query);
            this.statement.setString(1, user.getUsername());
            this.statement.setString(2, user.getEmail());
            this.statement.setString(3, user.getPassword());
            this.statement.setString(4, user.getDepartment());
            this.statement.setString(5, user.getGender());
            this.statement.executeUpdate();
            return this.getUserByEmail(user.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateUser(Long id, User user) {
        String query = "UPDATE user SET username = ?, email = ?, password = ?, department = ?, gender = ? WHERE id = ?";
        try {
            this.statement = connection.prepareStatement(query);
            this.statement.setString(1, user.getUsername());
            this.statement.setString(2, user.getEmail());
            this.statement.setString(3, user.getPassword());
            this.statement.setString(4, user.getDepartment());
            this.statement.setString(5, user.getGender());
            this.statement.setLong(6, id);
            this.statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(Long id) {
        String query = "DELETE FROM user WHERE id  = ?";
        try {
            this.statement = connection.prepareStatement(query);
            this.statement.setLong(1, id);
            this.statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
