package sample;

import controller.SignInHelper;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;

public class InformationProfesor {

    @FXML
    private ListView<String> informationProfessor;

    public void initialize(){
        informationProfessor.setItems(SignInHelper.informationStudent());
        informationProfessor.setFixedCellSize(50);

        informationProfessor.setCellFactory(new Callback<>() {
            @Override
            public ListCell<String> call(ListView<String> informationList) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(String info, boolean b) {
                        super.updateItem(info, b);
                        if (b) {
                            setText(null);
                        } else {
                            setText(info);
                            setTextAlignment(TextAlignment.CENTER);
                        }
                    }
                };
            }
        });
    }
}
