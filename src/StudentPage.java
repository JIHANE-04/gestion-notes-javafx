package uihi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class StudentPage {
	
    private GridPane root;
    private Stage primaryStage; // Add a field to store the primaryStage

    // Constructor that accepts the primaryStage
    public StudentPage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Scene createScene() {
        // Create layout using GridPane
        root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10); // Set horizontal gap
        root.setVgap(10); // Set vertical gap
        root.setPadding(new Insets(25, 25, 25, 25));

        // Add title and label
        Label titleLabel = new Label("Student Login");
        titleLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 30));
        root.add(titleLabel, 0, 0, 2, 1); // Span across two columns

        // Create components with padding and styling
        Label emailLabel = new Label("Email Address:");
        emailLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        TextField emailTextField = new TextField();
        emailTextField.setPromptText("you@example.com");
        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter 6 characters or more");
        Button loginButton = new Button("Login");
        loginButton.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
        loginButton.setTextFill(Color.WHITE);
        loginButton.setStyle("-fx-background-color: #673AB7; -fx-min-width: 100px;");
        loginButton.setPadding(new Insets(10, 20, 10, 20));
        Label loginStatusLabel = new Label();
        // Add background image
        Image backgroundImage = new Image("file:C:\\Users\\kaout\\Downloads/background.png"); // Replace with your image path
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, false,
                        false));
        root.setBackground(new Background(background));
        // Add event handler to the button
        loginButton.setOnAction(event -> {
            String username = emailTextField.getText();
            String password = passwordField.getText();
            handleLogin(username, password, loginStatusLabel);
        });
        
        Button backButton = new Button("Back to Main");
        backButton.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
        backButton.setTextFill(Color.WHITE);
        backButton.setStyle("-fx-background-color: #35495e; -fx-min-width: 100px;");
        backButton.setPadding(new Insets(10, 20, 10, 20));

        // Add an event handler to the "Back" button
        backButton.setOnAction(event -> {
            navigateToMainPage(); // Define this method to navigate back to the main class
        });

        // Add components to grid cells
        root.add(emailLabel, 0, 1);
        root.add(emailTextField, 1, 1);
        root.add(passwordLabel, 0, 2);
        root.add(passwordField, 1, 2);
        root.add(loginButton, 1, 3);
        GridPane.setHalignment(loginButton, HPos.RIGHT);
        root.add(loginStatusLabel, 1, 4);
        root.add(backButton, 1, 5); // Add the "Back" button to the grid layout
        GridPane.setHalignment(backButton, HPos.RIGHT); // Align it to the left

        // Create the scene with desired dimensions
        return new Scene(root, 800, 500);
    }

    private void handleLogin(String email, String password, Label loginStatusLabel) {
        try (Connection conn = DatabaseConnection.connexionDB()) {
            if (conn != null) {
                try {
                    String sql = "SELECT * FROM Students WHERE email = ? AND password = ?";
                    PreparedStatement statement = conn.prepareStatement(sql);
                    statement.setString(1, email);
                    statement.setString(2, password);

                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        loginStatusLabel.setText("Login successful!");
                        // Proceed with actions after successful login
                        openAcceuilstdPage(resultSet.getInt("student_id"));
                    } else {
                        loginStatusLabel.setText("Login failed!");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    loginStatusLabel.setText("Database error occurred!");
                }
            } else {
                loginStatusLabel.setText("Database connection failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            loginStatusLabel.setText("Connection error occurred!");
        }
    }
    private void navigateToMainPage() {
        Main mainApp = new Main(); // Create an instance of your Main class
        mainApp.start(primaryStage); // Start the main class with the primary stage
    }


    private void openAcceuilstdPage(int studentId) {
        AcceuilstdPage acceuilstdPage = new AcceuilstdPage(primaryStage);
        Scene acceuilstdScene = acceuilstdPage.createScene(studentId); // Pass the student ID

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(acceuilstdScene);
        stage.show();
    }
}