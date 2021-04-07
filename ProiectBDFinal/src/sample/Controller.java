package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    private ImageView logoImage;
    @FXML
    private Label welcomeLabel;

    public void initialize(){
        logoImage.setImage(new Image("Logo.jpg"));

        welcomeLabel.setText("Welcome to the platform\nof the Technical University of Cluj Napoca");
    }
    @FXML
    private void handleClickButtonSignIn () throws IOException{
        openNewStage("signIn");
    }
    @FXML
    private void handleClickButtonSignUp () throws IOException {
        openNewStage("signUp");
    }

    public void openNewStage(String s) throws IOException {
        Stage stage = (Stage) logoImage.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource(s + ".fxml"));
        stage.setTitle("Platform UTCN");
        stage.setScene(new Scene(root, 1200, 700));
        stage.setMinHeight(700);
        stage.setMinWidth(1200);
        stage.setMaxHeight(700);
        stage.setMaxWidth(1200);
        stage.show();

    }

}
