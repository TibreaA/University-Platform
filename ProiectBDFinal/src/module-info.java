module JavaFX {
    requires  javafx.fxml;
    requires javafx.controls;
    requires java.sql;
    requires java.desktop;
    requires java.mail;
    requires java.naming;
    requires activation;

    opens sample;
    opens controller;
    opens model.models;
}