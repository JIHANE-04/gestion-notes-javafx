package uihi;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.stage.Stage;


public class AcceuilstdPage {
	private Stage primaryStage;

	public AcceuilstdPage(Stage primaryStage) {
	    this.primaryStage = primaryStage;
	}
    @SuppressWarnings("unchecked")
    public Scene createScene(int studentId) {
        BorderPane borderPane = new BorderPane();

        // Sidebar
        VBox sidebar = new VBox(20);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #008000;"); // Green background
        sidebar.setPrefWidth(200);

        Text appName = new Text("IIUI Student Grade System");
        appName.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        appName.setFill(Color.WHITE);

        Button logoutButton = new Button("Log out");
        logoutButton.setMaxWidth(Double.MAX_VALUE);
	    logoutButton.setOnAction(event -> {
	        // Navigate back to the main page (assuming you have a Main class)
	        Main main = new Main();
	        main.start(primaryStage);
	    });
        sidebar.getChildren().addAll(appName, logoutButton);

        // Student Information Section
        VBox studentInfoBox = new VBox(10);
        studentInfoBox.setPadding(new Insets(15));
        studentInfoBox.setStyle("-fx-background-color: #006400;"); // Darker Green background

        Label nameLabel = new Label("Name:");
        nameLabel.setTextFill(Color.WHITE);
        Label regLabel = new Label("Reg No.:");
        regLabel.setTextFill(Color.WHITE);
        Label departmentLabel = new Label("Department:");
        departmentLabel.setTextFill(Color.WHITE);

        studentInfoBox.getChildren().addAll(nameLabel, regLabel, departmentLabel);

        // Grades Table
        TableView<Grade> gradesTable = new TableView<>();
        TableColumn<Grade, String> classNameColumn = new TableColumn<>("Class Name");
        TableColumn<Grade, String> gradeValueColumn = new TableColumn<>("Grade");
        classNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        gradeValueColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));
        gradesTable.getColumns().addAll(classNameColumn, gradeValueColumn);

        coursesTable(studentId, gradesTable);

        // Header for the courses section
        HBox coursesHeader = new HBox();
        coursesHeader.setPadding(new Insets(10));
        coursesHeader.setStyle("-fx-background-color: #228B22;"); // Forest green background
        Text coursesHeaderText = new Text("Joined Courses");
        coursesHeaderText.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        coursesHeaderText.setFill(Color.WHITE);
        coursesHeader.getChildren().add(coursesHeaderText);

        // Main content layout
        VBox mainContent = new VBox(5);
        mainContent.setPadding(new Insets(10));
        mainContent.getChildren().addAll(coursesHeader, gradesTable);

        // Assemble the main BorderPane
        borderPane.setLeft(sidebar);
        borderPane.setCenter(mainContent);

        // Scene and Stage setup
        Scene scene = new Scene(borderPane, 800, 600);
        return scene;
    }

    public static class Course {
        private final StringProperty studentId;
        private final IntegerProperty studentName;
        private final StringProperty courseName;
        private final StringProperty grade;

        public Course(String studentId, int studentName, String courseName, String grade) {
            this.studentId = new SimpleStringProperty(studentId);
            this.studentName = new SimpleIntegerProperty(studentName);
            this.courseName = new SimpleStringProperty(courseName);
            this.grade = new SimpleStringProperty(grade);
        }

        // Getters
        public String getCode() {
            return studentId.get();
        }

        public int getCredit() {
            return studentName.get();
        }

        public String getCourseName() {
            return courseName.get();
        }

        public String getGrade() {
            return grade.get();
        }

        // Property getters
        public StringProperty codeProperty() {
            return studentId;
        }

        public IntegerProperty creditProperty() {
            return studentName;
        }

        public StringProperty courseNameProperty() {
            return courseName;
        }

        public StringProperty gradeProperty() {
            return grade;
        }

        // Setters (if needed)
        public void setCode(String studentId) {
            this.studentId.set(studentId);
        }

        public void setCredit(int studentName) {
            this.studentName.set(studentName);
        }

        public void setCourseName(String courseName) {
            this.courseName.set(courseName);
        }

        public void setGrade(String grade) {
            this.grade.set(grade);
        }
    }

    private void coursesTable(int studentId, TableView<Grade> gradesTable) {
        ObservableList<Grade> gradesList = FXCollections.observableArrayList();

        try (Connection connection = DatabaseConnection.connexionDB()) {
            String query = "SELECT courseName, grade FROM Grades WHERE student_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String courseName = resultSet.getString("courseName");
                String grade = resultSet.getString("grade");

                // Assuming "courseName" represents the class name
                gradesList.add(new Grade(courseName, grade));
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace(); // Consider logging this error
        }

        gradesTable.setItems(gradesList);
        gradesTable.refresh();  // Refresh the table to show new data
    }

   

    public class Grade {
        private final StringProperty courseName;
        private final StringProperty grade;

        public Grade(String courseName, String grade) {
            this.courseName = new SimpleStringProperty(courseName);
            this.grade = new SimpleStringProperty(grade);
        }

        public String getCourseName() {
            return courseName.get();
        }

        public void setCourseName(String courseName) {
            this.courseName.set(courseName);
        }

        public StringProperty courseNameProperty() {
            return courseName;
        }

        public String getGrade() {
            return grade.get();
        }

        public void setGrade(String grade) {
            this.grade.set(grade);
        }

        public StringProperty gradeProperty() {
            return grade;
        }
    }

}
