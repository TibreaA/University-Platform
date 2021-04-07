package model.Note;

public class Materie {

    public String nume;
    public int tip;

    public Materie(String nume, int tip) {
        this.nume = nume;
        this.tip = tip;
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

    @Override
    public String toString() {
        return "Materie{" +
                "nume='" + nume + '\'' +
                ", tip=" + tip +
                '}';
    }
}
