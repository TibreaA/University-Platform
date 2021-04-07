package sample;

import controller.SignInHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.models.FormatUser;

import java.io.IOException;

public class UsersAdmin {
    @FXML
    private Button delete;
    @FXML
    private Button admin;
    @FXML
    private TextField prenume;
    @FXML
    private TextField name;
    @FXML
    private TableView<FormatUser> listaUsers;

    private int tip = 4;

    public void initialize(){
        listaUsers.setItems(SignInHelper.addUsers());
        delete.setVisible(false);

        if(SignInHelper.isSuperAdim()){
            admin.setVisible(true);
        }else{
            admin.setVisible(false);
        }
    }

    public void handleClickUser(ActionEvent event) {

        listaUsers.setItems(SignInHelper.addUsers());
        delete.setVisible(false);
        tip = 4;
    }

    public void handleClickStudent(ActionEvent event) {
        listaUsers.setItems(SignInHelper.addUsersStudents());
        delete.setVisible(true);
        tip = 1;
    }

    public void handleClickProfessors(ActionEvent event) {
        listaUsers.setItems(SignInHelper.addUsersProfesor());
        delete.setVisible(true);
        tip = 2;
    }

    public void handleClickAdmins(ActionEvent event) {
        listaUsers.setItems(SignInHelper.addUsersAdmin());
        delete.setVisible(true);
        tip = 3;
    }


    public void handleClickFind(){
        listaUsers.setItems(SignInHelper.findUsers(name.getText() ,prenume.getText() , tip));
    }

    public void handleClickDelete(ActionEvent event) {
        SignInHelper.deleteUser(listaUsers.getSelectionModel().getSelectedIndex() , tip);

        switch (tip){
            case 1:
                listaUsers.setItems(SignInHelper.addUsersStudents());
                break;

            case 2:
                listaUsers.setItems(SignInHelper.addUsersProfesor());
                break;

            case 3:
                listaUsers.setItems(SignInHelper.addUsersAdmin());
                break;
        }
    }

    public void handleClickAdd(ActionEvent event) throws IOException {
        Stage stage = (Stage) delete.getScene().getWindow();
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
}
