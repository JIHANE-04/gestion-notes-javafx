package uihi;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StudentData {

    private final StringProperty studentId;
    private final StringProperty studentName;
    private final StringProperty year;
    private final StringProperty courseName;
    private final StringProperty semester;
    private final StringProperty sector;
    private final StringProperty grade;

    public StudentData(String studentId, String studentName, String year, String courseName, String semester, String sector, String grade) {
        this.studentId = new SimpleStringProperty(studentId);
        this.studentName = new SimpleStringProperty(studentName);
        this.year = new SimpleStringProperty(year);
        this.courseName = new SimpleStringProperty(courseName);
        this.semester = new SimpleStringProperty(semester);
        this.sector = new SimpleStringProperty(sector);
        this.grade = new SimpleStringProperty(grade);
    }

    public String getStudentId() {
        return studentId.get();
    }

    public String getStudentName() {
        return studentName.get();
    }

    public String getYear() {
        return year.get();
    }

    public String getCourseName() {
        return courseName.get();
    }

    public String getSemester() {
        return semester.get();
    }

    public String getSector() {
        return sector.get();
    }

    public String getGrade() {
        return grade.get();
    }

    public StringProperty studentIdProperty() {
        return studentId;
    }

    public StringProperty studentNameProperty() {
        return studentName;
    }

    public StringProperty yearProperty() {
        return year;
    }

    public StringProperty courseNameProperty() {
        return courseName;
    }

    public StringProperty semesterProperty() {
        return semester;
    }

    public StringProperty sectorProperty() {
        return sector;
    }

    public StringProperty gradeProperty() {
        return grade;
    }
}
