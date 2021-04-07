package sample;

import controller.SignInHelper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class SignIn {
    @FXML
    private Label incorect;
    @FXML
    private PasswordField passwordUser;
    @FXML
    private TextField nameUser;
    @FXML
    private ImageView logoImage;

    public void initialize(){
        logoImage.setImage(new Image("Logo.jpg"));
        incorect.setVisible(false);
    }
    @FXML
    private void handleClickLogoImage() throws  IOException{
        openNewStage("sample");
    }
    @FXML
    private void handleClickButtonSignIn () throws IOException {
        openNewStage("signIn");
    }
    @FXML
    private void handleClickButtonSignUp () throws IOException {
        openNewStage("signUp");
    }

    @FXML
    private void handleClickSignIn() throws IOException {
        int restrictieUtilizator;
        String name = nameUser.getText();
        String password = passwordUser.getText();
        restrictieUtilizator = SignInHelper.signIn(name, password);

        switch (restrictieUtilizator){
            case 1: openNewStage("student");
                break;
            case 2: openNewStage("profesor");
                break;
            case 3: openNewStage("administrator");
                break;
            default: incorect.setVisible(true);
                break;
        }
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
