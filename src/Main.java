package uihi;

import javafx.application.Application;	
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {

    private Stage primaryStage;


	@Override
    public void start(Stage primaryStage) {
    	this.primaryStage = primaryStage;
        // VBox as the root layout
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);

        // Set background image to VBox
        Image bgImage = new Image("file:C:\\Users\\kaout\\Downloads/background.png"); // Make sure the path is correct
        BackgroundImage backgroundImage = new BackgroundImage(bgImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        // Apply the background to the VBox
        root.setBackground(new Background(backgroundImage));

        // Title label
        Label titleLabel = new Label("Welcome Back! Select what user are you");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Buttons for Student and Teacher
        Button btnStudent = new Button("I'm a Student");
        btnStudent.setStyle("-fx-background-color: #1E90FF; -fx-text-fill: white; -fx-font-weight: bold;");
        btnStudent.setPrefWidth(200); // Set the preferred width
        btnStudent.setPrefHeight(40); // Set the preferred height
        btnStudent.setOnAction(event -> {openStudentpage();
        	
            // Handle Student selection
            System.out.println("Student selected");
        });

        Button btnTeacher = new Button("I'm a Teacher");
        btnTeacher.setStyle("-fx-background-color: grey; -fx-text-fill: white; -fx-font-weight: bold;");
        btnTeacher.setPrefWidth(200); // Set the preferred width
        btnTeacher.setPrefHeight(40); // Set the preferred height
        btnTeacher.setOnAction(event -> {openteacherpage();
            // Handle Teacher selection
            System.out.println("Teacher selected");
        });

        // Add title and buttons to the layout
        root.getChildren().addAll(titleLabel, btnStudent, btnTeacher);

        // Set the scene
        Scene scene = new Scene(root, 800, 600);
        

        // Configure and show the stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }
	private void openteacherpage() {
	    TeacherPage teacherPage = new TeacherPage(primaryStage);
	    Scene teacherScene = teacherPage.createScene();
	    
	    // Set the new scene on the primary stage
	    primaryStage.setScene(teacherScene);

	    // Show the new scene
	    primaryStage.show();
	}

	private void openStudentpage() {
	    StudentPage studentPage = new StudentPage(primaryStage);
	    Scene studentScene = studentPage.createScene();
	    
	    // Set the new scene on the primary stage
	    primaryStage.setScene(studentScene);
	    


	}

    
    
    public static void main(String[] args) {
        launch(args);
    }
}
