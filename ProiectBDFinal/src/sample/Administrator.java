package sample;

import controller.SignInHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Administrator {

    @FXML
    private BorderPane panelStudent;
    @FXML
    private ImageView logoImage;
    public void initialize()throws IOException{
        logoImage.setImage(new Image("Logo.jpg"));
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("infoAdmin.fxml"));
        panelStudent.setCenter(root);
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
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("infoAdmin.fxml"));
        panelStudent.setCenter(root);
    }
    @FXML
    public void handleClickUsers()throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("usersAdmin.fxml"));
        panelStudent.setCenter(root);
    }

    public void handleClickCourses(ActionEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("cursuriAdmin.fxml"));
        panelStudent.setCenter(root);
    }

    public void handleClickAddCourses(ActionEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("addCurs.fxml"));
        panelStudent.setCenter(root);
    }
}
