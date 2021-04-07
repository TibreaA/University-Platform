package model.Activitate;

public class Activity {
    private int id;
    private String nume;
    private int tip;
    private String profesor;
    private int pondere;
    private String descriere;
    private int nrMaxStudenti;

    public Activity() {
    }

    public Activity(int id, String nume, int tip, String profesor, int pondere, String descriere, int nrMaxStudenti) {
        this.id = id;
        this.nume = nume;
        this.tip = tip;
        this.profesor = profesor;
        this.pondere = pondere;
        this.descriere = descriere;
        this.nrMaxStudenti = nrMaxStudenti;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getTip() {
        return tip;
    }

    public void setTip(int tip) {
        this.tip = tip;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public int getPondere() {
        return pondere;
    }

    public void setPondere(int pondere) {
        this.pondere = pondere;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public int getNrMaxStudenti() {
        return nrMaxStudenti;
    }

    public void setNrMaxStudenti(int nrMaxStudenti) {
        this.nrMaxStudenti = nrMaxStudenti;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", tip=" + tip +
                ", profesor='" + profesor + '\'' +
                ", pondere=" + pondere +
                ", descriere='" + descriere + '\'' +
                ", nrMaxStudenti=" + nrMaxStudenti +
                '}';
    }
}
