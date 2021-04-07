package controller;

import javafx.beans.property.SimpleStringProperty;

public class formatNota {
    private SimpleStringProperty grade = new SimpleStringProperty("");
    private SimpleStringProperty professor = new SimpleStringProperty("");
    private SimpleStringProperty courses = new SimpleStringProperty("");
    private SimpleStringProperty data = new SimpleStringProperty("");
    private SimpleStringProperty pondere = new SimpleStringProperty("");

    public formatNota(String grade, String professor, String courses, String data) {
        this.grade.set(grade);
        this.professor.set(professor);
        this.courses.set(courses);
        this.data.set(data);
    }

    public formatNota(String grade, String professor, String courses, String data, String pondere) {
        this.grade.set(grade);
        this.professor.set(professor);
        this.courses.set(courses);
        this.data.set(data);
        this.pondere.set(pondere);
    }

    public String getGrade() {
        return grade.get();
    }

    public SimpleStringProperty gradeProperty() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade.set(grade);
    }

    public String getProfessor() {
        return professor.get();
    }

    public String getPondere() {
        return pondere.get();
    }

    public SimpleStringProperty pondereProperty() {
        return pondere;
    }

    public void setPondere(String pondere) {
        this.pondere.set(pondere);
    }

    public SimpleStringProperty professorProperty() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor.set(professor);
    }

    public String getCourses() {
        return courses.get();
    }

    public SimpleStringProperty coursesProperty() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses.set(courses);
    }

    public String getData() {
        return data.get();
    }

    public SimpleStringProperty dataProperty() {
        return data;
    }

    public void setData(String data) {
        this.data.set(data);
    }

    @Override
    public String toString() {
        return "formatNota{" +
                "grade=" + grade +
                ", professor=" + professor +
                ", courses=" + courses +
                ", data=" + data +
                ", pondere=" + pondere +
                '}';
    }
}
