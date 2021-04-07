package sample;

import controller.SignInHelper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.models.Anunt;
import model.models.FormatMesaj;

import java.io.IOException;
import java.util.List;

public class Student {
    @FXML
    private TextArea chatMess;
    @FXML
    private MenuButton chooseGrup;
    @FXML
    private TextField mess;
    @FXML
    private Label chat;
    @FXML
    private ListView<Anunt> anunturi;
    @FXML
    private BorderPane panelStudent;
    @FXML
    private ImageView logoImage;

    private String grup;

    public void initialize()throws IOException{
        logoImage.setImage(new Image("Logo.jpg"));

        SignInHelper.addAnunturi();
        anunturi.setItems(SignInHelper.completionAnunturi());
        anunturi.setFixedCellSize(75);

        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("informationStudent.fxml"));
        panelStudent.setCenter(root);

        addGrup();

    }

    @FXML
    public void handleClickSignOut()throws IOException {
        Stage stage = (Stage) panelStudent.getScene().getWindow();
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
    @FXML
    public void handleClickInfo()throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("informationStudent.fxml"));
        panelStudent.setCenter(root);
    }
    @FXML
    public void handleClickCursuri()throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("cursuri.fxml"));
        panelStudent.setCenter(root);
    }

    @FXML
    public void handleClickOrar()throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("orar.fxml"));
        panelStudent.setCenter(root);
    }

    @FXML
    public void handleClickGrupuri()throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("grupuriStudent.fxml"));
        panelStudent.setCenter(root);
    }

    @FXML
    public void handleClickCatalog()throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("catalog.fxml"));
        panelStudent.setCenter(root);
    }

    @FXML
    public void handleClickSend(){
        String s = mess.getText();
        SignInHelper.sendMesaj(s , chooseGrup.getText());      /// implementare sendMess
        actualizareMesaje(chooseGrup.getText());
        mess.setText("");
    }

    public void actualizareMesaje(String id){
        List<FormatMesaj> list = SignInHelper.actualizareMesaje(id);

        StringBuilder me = new StringBuilder();

        for(FormatMesaj f : list){
            me.append(f.toString());
            me.append("\n");
        }

        chatMess.setText(me.toString());
    }
    public void addGrup(){
        List<MenuItem> allgrup;
        allgrup = SignInHelper.addGrupuriMesaje();         /// implementare allGrup
        for(MenuItem m : allgrup){
            m.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    chooseGrup.setText(m.getText());
                    actualizareMesaje(m.getText());
                }
            });
        }
        chooseGrup.getItems().addAll(allgrup);
    }

}
