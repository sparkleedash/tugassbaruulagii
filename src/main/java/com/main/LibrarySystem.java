package com.main;

import data.Admin;
import data.Student;
import exception.custom.IllegalAdminAccess;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class LibrarySystem extends Application {

    public static String NIM;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Saff Library");

        // Label
        Label sceneTitle = new Label("Saff Library");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        sceneTitle.setStyle("-fx-text-fill: #FFC0CB;"); // Light pink color

        // Button
        Button adminLoginButton = new Button("Login as Admin");
        Button studentLoginButton = new Button("Login as Student");

        // Button color style
        adminLoginButton.setStyle("-fx-background-color: #FFC0CB;"); // Yellow color
        studentLoginButton.setStyle("-fx-background-color: #FFC0CB;"); // Yellow color

        // Grid Layout for selection
        GridPane selectionGrid = new GridPane();
        selectionGrid.setAlignment(Pos.CENTER);
        selectionGrid.setVgap(10);
        selectionGrid.setHgap(5);

        selectionGrid.add(sceneTitle, 0, 0, 2, 1);
        selectionGrid.add(adminLoginButton, 0, 1);
        selectionGrid.add(studentLoginButton, 1, 1);

        // Create selection window
        Scene selectionScene = new Scene(selectionGrid, 1360, 720);
        primaryStage.setScene(selectionScene);
        primaryStage.show();

        // Event handler for admin login
        adminLoginButton.setOnAction(event -> showLoginForm(primaryStage, true, selectionScene));

        // Event handler for student login
        studentLoginButton.setOnAction(event -> showLoginForm(primaryStage, false, selectionScene));
    }

    private void showLoginForm(Stage primaryStage, boolean isAdmin, Scene previousScene) {
        Admin adminObj = new Admin();
        Student studentObj = new Student();

        // Label
        Label sceneTitle = new Label(isAdmin ? "Admin Login" : "Student Login");
        Label usernameLabel = new Label(isAdmin ? "Username" : "NIM");
        Label passwordLabel = new Label("Password");

        // Notification label
        Label errorLoginMessage = new Label("Pengguna tidak ditemukan");

        // Field
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();

        // Font Style
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        usernameLabel.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 15));
        passwordLabel.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 15));
        errorLoginMessage.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 12));

        // Font Color
        sceneTitle.setStyle("-fx-text-fill: #FFC0CB;"); // Light pink color
        errorLoginMessage.setStyle("-fx-text-fill: #FF1E1E;");

        // Font visible Settings
        errorLoginMessage.setVisible(false);

        // Button
        Button loginButton = new Button("Login");
        Button backButton = new Button("Back");

        // Button color style
        loginButton.setStyle("-fx-background-color: #FFC0CB;"); // Yellow color
        backButton.setStyle("-fx-background-color: #FFC0CB;"); // Yellow color

        // Grid Layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        grid.add(sceneTitle, 0, 0);

        grid.add(usernameLabel, 0, 1);
        if (isAdmin) {
            grid.add(passwordLabel, 0, 2);
            grid.add(passwordField, 1, 2);
        }
        grid.add(errorLoginMessage, 0, isAdmin ? 3 : 2);

        grid.add(usernameField, 1, 1);

        grid.add(loginButton, 1, isAdmin ? 3 : 2);
        grid.add(backButton, 0, isAdmin ? 4 : 3);

        grid.setVgap(10);
        grid.setHgap(5);

        // Create Window
        Scene scene = new Scene(grid, 1360, 720);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Action Button
        loginButton.setOnAction(event -> {
            if (isAdmin) {
                if (usernameField.getText().equals(Admin.adminusername) && passwordField.getText().equals(Admin.adminpassword)) {
                    adminObj.menu();
                    primaryStage.close();
                } else {
                    errorLoginMessage.setVisible(true);
                }
            } else {
                if (usernameField.getText().length() == 15) {
                    try {
                        if (studentObj.isStudents(usernameField)) {
                            errorLoginMessage.setVisible(false);
                            showStudentDetails(primaryStage, studentObj, previousScene);
                        } else {
                            errorLoginMessage.setVisible(true);
                        }
                    } catch (IllegalAdminAccess pesanError) {
                        errorLoginMessage.setText(pesanError.getMessage());
                        errorLoginMessage.setVisible(true);
                    }
                } else {
                    errorLoginMessage.setVisible(true);
                }
            }
        });

        // Back Button
        backButton.setOnAction(event -> primaryStage.setScene(previousScene));
    }

    private void showStudentDetails(Stage primaryStage, Student studentObj, Scene previousScene) {
        // Create a new layout for displaying student details
        VBox studentDetailsLayout = new VBox(10);
        studentDetailsLayout.setAlignment(Pos.CENTER);

        // Add student details (For demonstration, using a simple label)
        Label studentDetailsLabel = new Label("Student Details");
        studentDetailsLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));

        // Add more details about the student here
        Label studentInfo = new Label(studentObj.toString());  // Assuming Student class has a meaningful toString implementation
        studentInfo.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 15));

        // Back button
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #FFC0CB;"); // Yellow color
        backButton.setOnAction(event -> primaryStage.setScene(previousScene));

        // Add elements to the layout
        studentDetailsLayout.getChildren().addAll(studentDetailsLabel, studentInfo, backButton);

        // Create a new scene and set it on the stage
        Scene studentDetailsScene = new Scene(studentDetailsLayout, 1360, 720);
        primaryStage.setScene(studentDetailsScene);
        primaryStage.show();
    }
}
