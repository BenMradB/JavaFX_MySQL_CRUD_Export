package com.example.tableview;

import com.example.tableview.models.User;
import com.example.tableview.services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.System.lineSeparator;
import static javafx.scene.control.Alert.AlertType.*;

public class UsersController implements Initializable {
    @FXML
    private TableColumn<String, User> departmentColumn;
    @FXML
    private TableColumn<String, User> genderColumn;
    @FXML
    private RadioButton maleGender;
    @FXML
    private RadioButton femaleGender;
    @FXML
    private  ComboBox<String> departments;
    @FXML
    private TableView<User> usersTableView;
    @FXML
    private TableColumn<Long, User> idColumn;
    @FXML
    private TableColumn<String, User> usernameColumn;

    @FXML
    private TableColumn<String, User> emailColumn;
    @FXML
    private TableColumn<String, User> passwordColumn;
    @FXML
    private TextField idField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button addBtn;
    @FXML
    private Button editBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button importBtn;
    @FXML
    private Button exportBtn;

    private final UserService userService = new UserService();

    public void clearFields() {
        idField.clear();
        usernameField.clear();
        emailField.clear();
        passwordField.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idField.setEditable(false);
        usersTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                User user = newSelection;
                idField.setText(user.getId().toString());
                usernameField.setText(user.getUsername());
                emailField.setText(user.getEmail());
                passwordField.setText(user.getPassword());
                departments.setValue(user.getDepartment());
            }
        });

        departments.setItems(FXCollections.observableArrayList(
                List.of(
                        "DSI",
                        "TI",
                        "MDW",
                        "RSI"
                )
        ));

        departments.setValue(departments.getItems().get(0));
    }

    public void addUser(ActionEvent actionEvent) {
        User newUser = new User(
                usernameField.getText(),
                emailField.getText(),
                passwordField.getText(),
                departments.getValue(),
                maleGender.isSelected() ? "M" : "F"
        );

        User addedUser = this.userService.addUser(newUser);
        usersTableView.getItems().add(addedUser);
        usersTableView.refresh();
        clearFields();
    }

    public void editUser(ActionEvent actionEvent) {
        User updatedUser = new User(
                usernameField.getText(),
                emailField.getText(),
                passwordField.getText(),
                departments.getValue(),
//                Condition ? True : False
                maleGender.isSelected() ? "M" : "F"
        );

        this.userService.updateUser(Long.parseLong(idField.getText()), updatedUser);
        usersTableView.getSelectionModel().getSelectedItem().setUsername(usernameField.getText());
        usersTableView.getSelectionModel().getSelectedItem().setEmail(emailField.getText());
        usersTableView.getSelectionModel().getSelectedItem().setPassword(passwordField.getText());

        usersTableView.refresh();
        clearFields();
    }

    public void deleteUser(ActionEvent actionEvent) {
        this.userService.deleteUser(Long.parseLong(idField.getText()));
        usersTableView.getItems().remove(
                usersTableView.getSelectionModel().getSelectedItem()
        );

        usersTableView.refresh();
        clearFields();
    }

    public void importUsers(ActionEvent event) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

        usersTableView.setItems(
                FXCollections.observableArrayList(this.userService.getUsers())
        );
    }

    public void exportUsers(ActionEvent actionEvent) {
        StringBuilder content = new StringBuilder();
        for (User user : usersTableView.getItems()) {
            content.append("User Id : ").append(user.getId()).append(lineSeparator())
                    .append("Username : ").append(user.getUsername()).append(lineSeparator())
                    .append("Email Address : ").append(user.getEmail()).append(lineSeparator())
                    .append("Password : ").append(user.getPassword()).append(lineSeparator());
        }

        try {
            File users = new File("./users.txt");
            FileWriter writer = new FileWriter(users);

            writer.write(content.toString());
            writer.close();
            Alert alert = new Alert(INFORMATION);
            alert.setTitle("Success Message");
            alert.setHeaderText(null);
            alert.setContentText("Users Exported");
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}