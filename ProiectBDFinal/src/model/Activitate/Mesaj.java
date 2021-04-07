package model.Activitate;

public class Mesaj {
    private String mess;
    private int idStudent;


    public Mesaj() {
    }

    public Mesaj(String mess, int idStudent) {
        this.mess = mess;
        this.idStudent = idStudent;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    @Override
    public String toString() {
        return "Mesaj{" +
                "mess='" + mess + '\'' +
                ", idStudent=" + idStudent +
                '}';
    }
}

