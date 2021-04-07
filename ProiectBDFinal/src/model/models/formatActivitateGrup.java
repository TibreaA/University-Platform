package model.models;

import javafx.beans.property.SimpleStringProperty;

public class formatActivitateGrup {
    private SimpleStringProperty nume = new SimpleStringProperty("");
    private SimpleStringProperty minPart = new SimpleStringProperty("");
    private SimpleStringProperty limDate = new SimpleStringProperty("");
    private SimpleStringProperty data = new SimpleStringProperty("");
    private SimpleStringProperty profesor = new SimpleStringProperty("");

    public formatActivitateGrup(String nume, String minPart, String limDate, String data, String profesor) {
        this.nume.set(nume);
        this.profesor.set(profesor);
        this.data.set(data);
        this.limDate.set(limDate);
        this.minPart.set(minPart);
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

    public String getMinPart() {
        return minPart.get();
    }

    public SimpleStringProperty minPartProperty() {
        return minPart;
    }

    public void setMinPart(String minPart) {
        this.minPart.set(minPart);
    }

    public String getLimDate() {
        return limDate.get();
    }

    public SimpleStringProperty limDateProperty() {
        return limDate;
    }

    public void setLimDate(String limDate) {
        this.limDate.set(limDate);
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

    public String getProfesor() {
        return profesor.get();
    }

    public SimpleStringProperty profesorProperty() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor.set(profesor);
    }

    @Override
    public String toString() {
        return "formatActivitateGrup{" +
                "nume=" + nume +
                ", minPart=" + minPart +
                ", limDate=" + limDate +
                ", data=" + data +
                ", profesor=" + profesor +
                '}';
    }
}
