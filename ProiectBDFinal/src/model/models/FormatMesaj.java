package model.models;

public class FormatMesaj {
    private String mesaj;
    private String student;

    public FormatMesaj() {
    }

    public FormatMesaj(String mesaj, String student) {
        this.mesaj = mesaj;
        this.student = student;
    }

    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return student + ":\n\t" + mesaj ;
    }
}
