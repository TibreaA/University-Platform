package model.Activitate;

import java.sql.Date;

public class Materie {
    private String nume;
    private int idProf;
    private int tip;
    private int pondere;
    private int id;
    private Date date;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getIdProf() {
        return idProf;
    }

    public void setIdProf(int idProf) {
        this.idProf = idProf;
    }

    public int getTip() {
        return tip;
    }

    public void setTip(int tip) {
        this.tip = tip;
    }

    public int getPondere() {
        return pondere;
    }

    public void setPondere(int pondere) {
        this.pondere = pondere;
    }

    @Override
    public String toString() {
        return "Materie{" +
                "nume='" + nume + '\'' +
                ", idProf=" + idProf +
                ", tip=" + tip +
                ", pondere=" + pondere +
                '}';
    }
}
