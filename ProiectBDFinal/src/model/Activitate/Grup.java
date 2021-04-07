package model.Activitate;

public class Grup {

    private int id;
    private String Materie;
    private int idMaterie;

    public int getIdMaterie() {
        return idMaterie;
    }

    public void setIdMaterie(int idMaterie) {
        this.idMaterie = idMaterie;
    }
    public Grup() {
    }

    public Grup(int id, int idMaterie) {
        this.id = id;
        this.idMaterie = idMaterie;
    }

    public Grup(int id, String materie) {
        this.id = id;
        Materie = materie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaterie() {
        return Materie;
    }

    public void setMaterie(String materie) {
        Materie = materie;
    }

    @Override
    public String toString() {
        return "Grup{" +
                "id=" + id +
                ", Materie='" + Materie + '\'' +
                '}';
    }
}
