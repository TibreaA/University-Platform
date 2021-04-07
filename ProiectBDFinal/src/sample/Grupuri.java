package sample;

import controller.SignInHelper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.models.formatActivitateGrup;
import model.models.formatGrupuri;


public class Grupuri {
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
        creareActivitate.setVisible(false);
        inscriereActivitate.setVisible(false);
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
        creareActivitate.setVisible(false);
        inscriereActivitate.setVisible(false);
        toolBar.setVisible(false);
        ok = false;
    }

    public void clickMyActivity(){
        creareActivitate.setVisible(true);
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
        inscriereActivitate.setVisible(false);
    }

    public void clickTabelActivity(){
        if(ok && !ok1){
            inscriereActivitate.setVisible(true);
        }
    }

    public void clickInscriere(){
        boolean ok = SignInHelper.insertGrup(listaGrupuri.getSelectionModel().getSelectedIndex());
        SignInHelper.setMyGrup();
        listaGrupuri.setItems(SignInHelper.setAllGrupuri());

        System.out.println(ok);
    }

    public void clickInscriereActivity(){
        SignInHelper.inscriereActivitate(listaActivitati.getSelectionModel().getSelectedIndex());
    }
    public void clickCreateActivity(){

    }
}
