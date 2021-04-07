package model.Note;



import java.util.Date;

public class Nota {
    private int grade;
    private int idActivity;
    private String student;
    private String profesor;
    private Date date;
    private String materie;

    public Nota() {
    }

    public int getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(int idActivity) {
        this.idActivity = idActivity;
    }

    public String getMaterie() {
        return materie;
    }

    public void setMaterie(String materie) {
        this.materie = materie;
    }

    public Nota(int grade, String student, String profesor, Date date) {
        this.grade = grade;
        this.student = student;
        this.profesor = profesor;
        this.date = date;
    }


    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return  "Grade: " + grade + ", " + materie +
                ", profesor " + profesor +
                ", " + date +
                "\n";
    }
}
