package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.models.Anunt;

import java.io.IOException;

public class Profesor {
    @FXML
    private ListView<Anunt> anunturi;
    @FXML
    private Label chat;
    @FXML
    private MenuButton chooseGrup;
    @FXML
    private TextArea chatMess;
    @FXML
    private TextField mess;
    @FXML
    private BorderPane panelProfesor;
    @FXML
    private ImageView logoImage;

    public void initialize()throws IOException{
        logoImage.setImage(new Image("Logo.jpg"));

        Parent root;
        root = FXMLLoader.load(getClass().getResource("informationProfesor.fxml"));
        panelProfesor.setCenter(root);

    }

    @FXML
    public void handleClickSignOut()throws IOException {
        Stage stage = (Stage) logoImage.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        stage.setTitle("Platform UTCN");
        stage.setScene(new Scene(root, 1200, 700));
        stage.setMinHeight(700);
        stage.setMinWidth(1200);
        stage.setMaxHeight(700);
        stage.setMaxWidth(1200);
        stage.show();
    }
    @FXML
    public void handleClickInfo()throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("informationProfesor.fxml"));
        panelProfesor.setCenter(root);
    }
    @FXML
    public void handleClickCursuri()throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("cursuriProfesor.fxml"));
        panelProfesor.setCenter(root);
    }

    @FXML
    public void handleClickOrar()throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("orarProfesor.fxml"));
        panelProfesor.setCenter(root);
    }

    @FXML
    public void handleClickCatalog()throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("catalogProfesor.fxml"));
        panelProfesor.setCenter(root);
    }

    public void handleClickAddCourse(ActionEvent event) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("addCursProf.fxml"));
        panelProfesor.setCenter(root);
    }
}
