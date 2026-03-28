package uihi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AcceuilltecPage {
	private Stage primaryStage;

	public AcceuilltecPage(Stage primaryStage) {
	    this.primaryStage = primaryStage;
	}

    @SuppressWarnings("unchecked")
    public Scene createScene(int teacherId) {
    	    // Existing code...

    	    Button signOutButton = new Button("Sign Out");

    	    // Add an event handler to the "Sign Out" button
    	    signOutButton.setOnAction(event -> {
    	        // Navigate back to the main page (assuming you have a Main class)
    	        Main main = new Main();
    	        main.start(primaryStage);
    	    });

    	    // Existing code...

    	   
    	

        BorderPane borderPane = new BorderPane();

        // Sidebar
        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(10));
        sidebar.setStyle("-fx-background-color: #34495E; -fx-padding: 20;");

        Label welcomeLabel = new Label("Welcome, admin");
        welcomeLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold; -fx-padding: 10;");

        

        for (Button button : new Button[]{signOutButton}) {
            button.setPrefWidth(150);
            button.setStyle("-fx-background-color: #2C3E50; -fx-text-fill: white;");
        }

        sidebar.getChildren().addAll(welcomeLabel, signOutButton);

        // Form
        GridPane form = new GridPane();
        form.setVgap(10);
        form.setHgap(10);
        form.setPadding(new Insets(10));
        form.setStyle("-fx-background-color: #ECF0F1;");

        Label idLabel = new Label("id :");
        TextField idTextField = new TextField();
        Label studentLabel = new Label("StudentName :");
        TextField studentTextField = new TextField();
        Label sectorLabel = new Label("sector :");
        TextField sectorTextField = new TextField();
        Label gradeLabel = new Label("grade :");
        TextField gradeTextField = new TextField();

        Label yearLabel = new Label("year :");
        ComboBox<String> yearComboBox = new ComboBox<>();
        ObservableList<String> yearOptions = FXCollections.observableArrayList("1st Year", "2nd Year");
        yearComboBox.setItems(yearOptions);

        Label courseLabel = new Label("course :");
        TextField courseTextField = new TextField();

        Label semLabel = new Label("semester :");
        TextField semTextField = new TextField();

        Button addButton = new Button("Add");
        Button clearButton = new Button("Clear");
        Button updateButton = new Button("Update");
        Button selectButton = new Button("Select");
        Button loadDataButton = new Button("Load Data");
        // Add the "Delete" button
        Button deleteButton = new Button("Delete");
        deleteButton.setDisable(true);
        // Add the "Select" button
       
        clearButton.setStyle("-fx-background-color: #3498DB; -fx-text-fill: white;");
        loadDataButton.setStyle("-fx-background-color: #3498DB; -fx-text-fill: white;");
        updateButton.setStyle("-fx-background-color: #3498DB; -fx-text-fill: white;");
        addButton.setStyle("-fx-background-color: #3498DB; -fx-text-fill: white;");
        loadDataButton.setStyle("-fx-background-color: #3498DB; -fx-text-fill: white;");
        selectButton.setStyle("-fx-background-color: #3498DB; -fx-text-fill: white;");
        deleteButton.setStyle("-fx-background-color: #E74C3C; -fx-text-fill: white;");
        form.add(idLabel, 0, 0);
        form.add(idTextField, 1, 0);
        form.add(studentLabel, 0, 1);
        form.add(studentTextField, 1, 1);
        form.add(yearLabel, 0, 2);
        form.add(yearComboBox, 1, 2);
        form.add(courseLabel, 0, 3);
        form.add(courseTextField, 1, 3);
        form.add(semLabel, 0, 4);
        form.add(semTextField, 1, 4);
        form.add(sectorLabel, 0, 5);
        form.add(sectorTextField, 1, 5);
        form.add(gradeLabel, 0, 6);
        form.add(gradeTextField, 1, 6);
        form.add(updateButton, 0, 7);
        form.add(addButton, 1, 7);
        form.add(clearButton, 2, 7);
        form.add(loadDataButton, 3, 7);
        form.add(selectButton, 4, 7);
        form.add(deleteButton, 5, 7);

        TableView<StudentData> table = new TableView<>();

        TableColumn<StudentData, Integer> idColumn = new TableColumn<>("student_id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));

        TableColumn<StudentData, String> studentNameColumn = new TableColumn<>("student_name");
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));

        TableColumn<StudentData, String> yearColumn = new TableColumn<>("year");
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

        TableColumn<StudentData, String> courseNameColumn = new TableColumn<>("course_name");
        courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));

        TableColumn<StudentData, String> semColumn = new TableColumn<>("semester");
        semColumn.setCellValueFactory(new PropertyValueFactory<>("semester"));

        TableColumn<StudentData, String> sectorColumn = new TableColumn<>("sector");
        sectorColumn.setCellValueFactory(new PropertyValueFactory<>("sector"));

        TableColumn<StudentData, String> gradeColumn = new TableColumn<>("grade");
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));

        table.getColumns().addAll(idColumn, studentNameColumn, yearColumn, courseNameColumn, semColumn, sectorColumn,
                gradeColumn);
        table.setStyle("-fx-background-color: #ECF0F1;");

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String studentName = studentTextField.getText();
                String year = yearComboBox.getValue();
                String course = courseTextField.getText();
                String semester = semTextField.getText();
                String sector = sectorTextField.getText();
                String grade = gradeTextField.getText();

                insertDataIntoDatabase(teacherId, studentName, year, course, semester, sector, grade);

                loadDataIntoTable(table,teacherId);
            }
        });
        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                idTextField.setText("");
                studentTextField.setText("");
                yearComboBox.setValue(null);
                courseTextField.setText("");
                semTextField.setText("");
                sectorTextField.setText("");
                gradeTextField.setText("");
            }
        });
        // Add an event handler for the "Select" button
        selectButton.setOnAction(event -> selectStudent(table, studentTextField, idTextField, yearComboBox,
        		courseTextField, semTextField, sectorTextField, gradeTextField));
        // Add a selection listener to enable/disable the "Delete" button based on selection
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                deleteButton.setDisable(false); // Enable the button when a row is selected
            } else {
                deleteButton.setDisable(true); // Disable the button when no row is selected
            }
        });
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                StudentData selectedStudent = table.getSelectionModel().getSelectedItem();
                if (selectedStudent != null) {
                    deleteStudentData(selectedStudent.getStudentId());
                    loadDataIntoTable(table, teacherId); // Refresh the table
                }
            }
        });
        updateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int studentId = Integer.parseInt(idTextField.getText());
                String studentName = studentTextField.getText();
                String year = yearComboBox.getValue();
                String course = courseTextField.getText();
                String semester = semTextField.getText();
                String sector = sectorTextField.getText();
                String grade = gradeTextField.getText();

                updateStudentData(studentId, studentName, year, course, semester, sector, grade);
                loadDataIntoTable(table, teacherId); // Refresh the table
            }
        });

        loadDataButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadDataIntoTable(table,teacherId);
            }
        });
        

        loadDataIntoTable(table ,teacherId);

        borderPane.setLeft(sidebar);
        VBox rightSide = new VBox(10, form, table);
        rightSide.setPadding(new Insets(10));
        borderPane.setCenter(rightSide);

        Scene scene = new Scene(borderPane, 800, 600);
        return scene;
    }


    private void loadDataIntoTable(TableView<StudentData> table,int teacherId) {
        Connection connection = DatabaseConnection.connexionDB();
        ObservableList<StudentData> data = FXCollections.observableArrayList();

        try {
        	String query = "SELECT s.student_id, s.email AS student_name, g.year, g.courseName, g.semester, g.sector, g.grade "
        			  + "FROM Students s "
        			  + "JOIN Grades g ON s.student_id = g.student_id "
        			  + "WHERE s.teacher_id = ?";

        			PreparedStatement preparedStatement = connection.prepareStatement(query);
        			 preparedStatement.setInt(1, teacherId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String id = resultSet.getString("student_id");
                String studentName = resultSet.getString("student_name");
                String year = resultSet.getString("year");
                String course = resultSet.getString("courseName");
                String semester = resultSet.getString("semester");
                String sector = resultSet.getString("sector");
                String grade = resultSet.getString("grade");

                data.add(new StudentData(id, studentName, year, course, semester, sector, grade));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        table.setItems(data);
    }

    private void insertDataIntoDatabase(int teacherId, String studentName, String year, String courseName,
            String semester, String sector, String grade) {
try (Connection connection = DatabaseConnection.connexionDB()) {
// Disable student name uniqueness constraint temporarily


// Insert student data
String studentQuery = "INSERT INTO Students (email, password, teacher_id) VALUES (?, ?, ?)";
PreparedStatement studentStmt = connection.prepareStatement(studentQuery, Statement.RETURN_GENERATED_KEYS);
studentStmt.setString(1, studentName);
studentStmt.setString(2, "123"); // Replace with actual password hash
studentStmt.setInt(3, teacherId);
studentStmt.executeUpdate();

int studentId = -1;
ResultSet studentKeys = studentStmt.getGeneratedKeys();
if (studentKeys.next()) {
studentId = studentKeys.getInt(1);
}

// Insert grade data
String gradeQuery = "INSERT INTO Grades (student_id, studentName, year, courseName, semester, sector, grade) VALUES (?, ?, ?, ?, ?, ?, ?)";
PreparedStatement gradeStmt = connection.prepareStatement(gradeQuery);
gradeStmt.setInt(1, studentId);
gradeStmt.setString(2, studentName); // Add studentName
gradeStmt.setString(3, year);
gradeStmt.setString(4, courseName);
gradeStmt.setString(5, semester);
gradeStmt.setString(6, sector);
gradeStmt.setString(7, grade);
gradeStmt.executeUpdate();

} catch (SQLException e) {
e.printStackTrace();
// Handle SQL errors appropriately, informing the user if necessary
}
}
    private void selectStudent(TableView<StudentData> table, TextField studentTextField, TextField idTextField, ComboBox<String> yearComboBox,
    		 TextField courseTextField   , TextField SemTextField, TextField sectorTextField, TextField gradeTextField) {
StudentData selectedStudent = table.getSelectionModel().getSelectedItem();

if (selectedStudent != null) {
studentTextField.setText(selectedStudent.getStudentName());
idTextField.setText(selectedStudent.getStudentId());
yearComboBox.setValue(selectedStudent.getYear());
courseTextField.setText(selectedStudent.getCourseName());
SemTextField.setText(selectedStudent.getSemester());
sectorTextField.setText(selectedStudent.getSector());
gradeTextField.setText(selectedStudent.getGrade());
}
}
    private void updateStudentData(int studentId, String studentName, String year, String courseName, String semester, String sector, String grade) {
        try (Connection connection = DatabaseConnection.connexionDB()) {
            // Update student name in Students table
            String updateStudentQuery = "UPDATE Students SET email = ? WHERE student_id = ?";
            try (PreparedStatement studentStmt = connection.prepareStatement(updateStudentQuery)) {
                studentStmt.setString(1, studentName);
                studentStmt.setInt(2, studentId);
                studentStmt.executeUpdate();
            }

            // Update grades in Grades table
            String updateGradesQuery = "UPDATE Grades SET year = ?, courseName = ?, semester = ?, sector = ?, grade = ? WHERE student_id = ?";
            try (PreparedStatement gradesStmt = connection.prepareStatement(updateGradesQuery)) {
                gradesStmt.setString(1, year);
                gradesStmt.setString(2, courseName);
                gradesStmt.setInt(3, Integer.parseInt(semester));
                gradesStmt.setString(4, sector);
                gradesStmt.setString(5, grade);
                gradesStmt.setInt(6, studentId);
                gradesStmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL errors appropriately, informing the user if necessary
        }
    }
    private void deleteStudentData(String studentId) {
        try (Connection connection = DatabaseConnection.connexionDB()) {
            // Define the SQL query to delete associated grade records from Grades table
            String deleteGradesQuery = "DELETE FROM Grades WHERE student_id = ?";

            // Create a prepared statement for deleting from Grades table
            try (PreparedStatement gradesStmt = connection.prepareStatement(deleteGradesQuery)) {
                gradesStmt.setString(1, studentId);

                // Execute the deletion query for Grades table
                gradesStmt.executeUpdate();
            }

            // Now that associated grades have been deleted, you can delete the student record
            String deleteStudentQuery = "DELETE FROM Students WHERE student_id = ?";

            // Create a prepared statement for deleting from Students table
            try (PreparedStatement studentStmt = connection.prepareStatement(deleteStudentQuery)) {
                studentStmt.setString(1, studentId);

                // Execute the deletion query for Students table
                studentStmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL errors appropriately, informing the user if necessary
        }
    }
}