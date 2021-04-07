package model.user;

import java.sql.*;

public class Profesor extends Utilizator {
    private int nrMinOre;
    private int nrMaxOre;
    private String departament;



    public Profesor() {
    }

    public Profesor(String CNP, String nume, String prenume, String adresa, String nr_telefon, String email, String IBAN, int nrContract, int nrMinOre, int nrMaxOre, String departament) {
        super(CNP, nume, prenume, adresa, nr_telefon, email, IBAN, nrContract);
        this.nrMinOre = nrMinOre;
        this.nrMaxOre = nrMaxOre;
        this.departament = departament;
    }

    public int getNrMinOre() {
        return nrMinOre;
    }

    public int getNrMaxOre() {
        return nrMaxOre;
    }

    public String getDepartament() {
        return departament;
    }

    public void setNrMinOre(int nrMinOre) {
        this.nrMinOre = nrMinOre;
    }

    public void setNrMaxOre(int nrMaxOre) {
        this.nrMaxOre = nrMaxOre;
    }

    public void setDepartament(String departament) {
        this.departament = departament;
    }


    @Override
    public String toString() {
        return "Profesor{" +
                "nrMinOre=" + nrMinOre +
                ", nrMaxOre=" + nrMaxOre +
                ", departament='" + departament + '\'' +
                  super.toString() +"} ";
    }
}
