package sample;

import controller.SignInHelper;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;

public class InfoAdmin {
    @FXML
    private ListView<String> informationAdmin;
    public void initialize(){
        informationAdmin.setItems(SignInHelper.informationStudent());
        informationAdmin.setFixedCellSize(50);

        informationAdmin.setCellFactory(new Callback<>() {
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
