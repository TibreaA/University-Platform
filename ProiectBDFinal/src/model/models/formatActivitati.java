package model.models;

import javafx.beans.property.SimpleStringProperty;

public class formatActivitati {
    private SimpleStringProperty tip = new SimpleStringProperty("");
    private SimpleStringProperty nume = new SimpleStringProperty("");
    private SimpleStringProperty profesor = new SimpleStringProperty("");
    private SimpleStringProperty data = new SimpleStringProperty("");

    public formatActivitati(String tip, String nume, String profesor, String data) {
        this.tip.set(tip);
        this.nume.set(nume);
        this.profesor.set(profesor);
        this.data.set(data);
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

    public String getNume() {
        return nume.get();
    }

    public SimpleStringProperty numeProperty() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume.set(nume);
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
        return "formatActivitati{" +
                "tip=" + tip +
                ", nume=" + nume +
                ", profesor=" + profesor +
                ", data=" + data +
                '}';
    }
}
