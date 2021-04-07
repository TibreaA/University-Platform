package sample;

import controller.SignInHelper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.models.formatActivitateGrup;
import model.models.formatGrupuri;

import java.sql.Date;
import java.util.List;


public class GrupuriStudent {
    @FXML
    private Button viewStudent;
    @FXML
    private TableView<formatGrupuri> listaGrupuriCreate;
    @FXML
    private TextField name;
    @FXML
    private TextField nrMin;
    @FXML
    private DatePicker lim;
    @FXML
    private DatePicker data;
    @FXML
    private ToolBar toolBar;
    @FXML
    private Button inscriereActivitate;
    @FXML
    private Button creareActivitate;
    @FXML
    private Button inscriere;
    @FXML
    private TableView<formatActivitateGrup> listaActivitati;
    @FXML
    private TableView<formatGrupuri> listaGrupuri;

    public boolean ok = true;
    public boolean ok1 = true;

    public void initialize(){
        listaGrupuri.setItems(SignInHelper.setMyGrup());
        inscriere.setVisible(false);
        inscriereActivitate.setVisible(false);
        listaGrupuriCreate.setItems(SignInHelper.setMyGrup());
    }

    public void clickMyGrup(){
        listaGrupuri.setItems(SignInHelper.setMyGrup());
        inscriere.setVisible(false);
        listaActivitati.setVisible(true);
        toolBar.setVisible(true);
        ok = true;
    }
    public void clickAllGrup(){
        listaGrupuri.setItems(SignInHelper.setAllGrupuri());
        listaActivitati.setVisible(false);
        inscriereActivitate.setVisible(false);
        toolBar.setVisible(false);
        ok = false;
    }

    public void clickMyActivity(){
        inscriereActivitate.setVisible(false);
        inscriere.setVisible(false);
        ok1 = true;
    }
    public void clickErrolActivity(){
        creareActivitate.setVisible(false);
        inscriereActivitate.setVisible(true);
        inscriere.setVisible(false);
        ok1 = false;
    }

    public void clickTabel(){
        if(ok) {
            if(ok1) {
                listaActivitati.setItems(SignInHelper.setAllActivitatiGrupParticip(listaGrupuri.getSelectionModel().getSelectedIndex()));
            }else{
                listaActivitati.setItems(SignInHelper.setAllActivitatiGrupNuParticip(listaGrupuri.getSelectionModel().getSelectedIndex()));
            }
            creareActivitate.setVisible(true);
        }else {
            inscriere.setVisible(true);
        }
    }

    public void clickInscriere(){
        SignInHelper.insertGrup(listaGrupuri.getSelectionModel().getSelectedIndex());
        SignInHelper.setMyGrup();
        listaGrupuri.setItems(SignInHelper.setAllGrupuri());
        listaGrupuriCreate.setItems(SignInHelper.setMyGrup());
    }

    public void clickInscriereActivity(){
        SignInHelper.inscriereActivitate(listaActivitati.getSelectionModel().getSelectedIndex());
    }
    public void clickCreateActivity(){
        SignInHelper.adaugareActivitate(listaGrupuriCreate.getSelectionModel().getSelectedIndex(), name.getText() , Integer.parseInt(nrMin.getText()) ,Date.valueOf(lim.getValue()), Date.valueOf(data.getValue()));
    }
    public void clickViewStudent(){
        List<String> studenti = SignInHelper.membriGrup(listaGrupuriCreate.getSelectionModel().getSelectedIndex());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Members of grup " + listaGrupuriCreate.getSelectionModel().getSelectedItem().getNume());

        StringBuilder stringBuilder = new StringBuilder();
        for(String s : studenti){
            stringBuilder.append(s);
            stringBuilder.append("\n");
        }

        alert.setHeaderText(stringBuilder.toString());

        alert.show();
    }
}

