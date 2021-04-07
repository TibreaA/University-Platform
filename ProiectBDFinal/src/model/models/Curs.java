package model.models;

import javafx.beans.property.SimpleStringProperty;

public class Curs {
    private SimpleStringProperty nume = new SimpleStringProperty("");
    private SimpleStringProperty tip = new SimpleStringProperty("");
    private SimpleStringProperty profesor = new SimpleStringProperty("");
    private SimpleStringProperty pondere = new SimpleStringProperty("");
    private SimpleStringProperty data = new SimpleStringProperty("");
    ///tot ce ii la curs

    public Curs(String nume, String tip) {
        this.nume.set(nume);
        this.tip.set(tip);
    }

    public Curs(String nume, String tip, String profesor, String pondere) {
        this.nume.set(nume);
        this.tip.set(tip);
        this.profesor.set(profesor);
        this.pondere.set(pondere);
    }

    public Curs(String nume, String tip, String data, String profesor, String pondere) {
        this.nume.set(nume);
        this.tip.set(tip);
        this.data.set(data);
        this.profesor.set(profesor);
        this.pondere.set(pondere);
    }

    public Curs(String nume, String profesor, String data) {
        this.nume.set(nume);
        this.tip.set(profesor);
        this.data.set(data);
    }

    public String getProfesor() {
        return profesor.get();
    }

    public SimpleStringProperty profesorProperty() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor.set(profesor);
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

    public String getNume() {
        return nume.get();
    }

    public SimpleStringProperty numeProperty() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume.set(nume);
    }

    public String getTip() {
        return tip.get();
    }

    public SimpleStringProperty tipProperty() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip.set(tip);
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
        return  nume + " "  + tip ;
    }
}
