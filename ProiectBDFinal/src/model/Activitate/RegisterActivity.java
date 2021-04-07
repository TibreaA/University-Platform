package model.Activitate;

public class RegisterActivity {
    private int idActivitate;
    private int nrStudent;

    public RegisterActivity() {
    }

    public RegisterActivity(int idActivitate, int nrStudent) {
        this.idActivitate = idActivitate;
        this.nrStudent = nrStudent;
    }

    public int getIdActivitate() {
        return idActivitate;
    }

    public void setIdActivitate(int idActivitate) {
        this.idActivitate = idActivitate;
    }

    public int getNrStudent() {
        return nrStudent;
    }

    public void setNrStudent(int nrStudent) {
        this.nrStudent = nrStudent;
    }

}
