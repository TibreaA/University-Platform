package sample;

import controller.SignInHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.models.Curs;

public class CursuriProfesor {
    @FXML
    public TextField pondere;
    @FXML
    private TableView<Curs> listaCursuri;

    public void initialize(){
        listaCursuri.setItems(SignInHelper.cursuriProfesor());
    }

    public void schimbaPondere(ActionEvent event) {
        SignInHelper.schimbaPondere(listaCursuri.getSelectionModel().getFocusedIndex() , Integer.parseInt(pondere.getText()));
        listaCursuri.setItems(SignInHelper.cursuriProfesor());
    }
}
