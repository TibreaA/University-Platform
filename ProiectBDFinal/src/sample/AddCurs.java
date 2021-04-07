package sample;

import controller.SignInHelper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class AddCurs {
    @FXML
    private TextField name;
    @FXML
    private TextField idProf;
    @FXML
    private MenuButton tip;
    @FXML
    private TextField pondere;
    @FXML
    private TextField description;
    @FXML
    private TextField nrMax;
    @FXML
    private DatePicker dateStart;
    @FXML
    private DatePicker dateEnd;

    public void initialize(){
        List<MenuItem> allgrup = new ArrayList<>();
        allgrup.add(new MenuItem("Curs"));
        allgrup.add(new MenuItem("Laborator"));
        allgrup.add(new MenuItem("Seminar"));

        for(MenuItem m : allgrup){
            m.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    tip.setText(m.getText());
                }
            });
        }
        tip.getItems().addAll(allgrup);
    }

    public void clickAdd(){
        SignInHelper.addCurs(name.getText() , idProf.getText() , tip.getText() , pondere.getText() , description.getText() , nrMax.getText() , dateStart.getValue().toString() , dateEnd.getValue().toString());
    }

}
