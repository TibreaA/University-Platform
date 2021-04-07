package sample;

import controller.SignInHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import model.models.Curs;
import model.models.FormatUser;

public class CursuriAdmin {
    @FXML
    private TableView<FormatUser> listaUsers;
    @FXML
    private TableView<Curs> listaActivitati;

    public void initialize() {
        listaActivitati.setItems(SignInHelper.addMateri());
    }

    public void clickActivitate(){
        listaUsers.setItems(SignInHelper.addStudentiPerMaterie(listaActivitati.getSelectionModel().getSelectedIndex()));
    }

    public void clickAddCurs(ActionEvent event) {

    }
}
