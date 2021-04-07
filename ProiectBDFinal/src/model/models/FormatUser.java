package model.models;

import javafx.beans.property.SimpleStringProperty;

public class FormatUser {
    private int id;
    private SimpleStringProperty nume = new SimpleStringProperty("");
    private SimpleStringProperty cnp = new SimpleStringProperty("");
    private SimpleStringProperty phone = new SimpleStringProperty("");
    private SimpleStringProperty mail = new SimpleStringProperty("");

    public FormatUser(int id,String nume, String cnp, String phone, String mail) {
        this.id = id;
        this.nume.set(nume);
        this.cnp.set(cnp);
        this.phone.set(phone);
        this.mail.set(mail);
    }

    @Override
    public String toString() {
        return "FormatUser{" +
                "nume=" + nume +
                ", cnp=" + cnp +
                ", phone=" + phone +
                ", mail=" + mail +
                '}';
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

    public String getCnp() {
        return cnp.get();
    }

    public SimpleStringProperty cnpProperty() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp.set(cnp);
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getMail() {
        return mail.get();
    }

    public SimpleStringProperty mailProperty() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail.set(mail);
    }
}
