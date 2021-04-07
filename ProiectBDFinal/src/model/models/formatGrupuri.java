package model.models;

import javafx.beans.property.SimpleStringProperty;

public class formatGrupuri {
    private SimpleStringProperty id = new SimpleStringProperty("");
    private SimpleStringProperty nume = new SimpleStringProperty("");

    public formatGrupuri(String id, String nume) {
        this.id.set(id);
        this.nume.set(nume);
    }

    @Override
    public String toString() {
        return "formatGrupuri{" +
                "id=" + id +
                ", nume=" + nume +
                '}';
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
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
}
