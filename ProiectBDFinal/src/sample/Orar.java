package sample;

import controller.SignInHelper;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import model.Activitate.Activity;
import model.models.formatActivitati;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Orar {

    @FXML
    private TableView<formatActivitati> particip;
    @FXML
    private GridPane days;

    private ImageView[] buttonsImage = new ImageView[14];

    public void  initialize(){
       setGridPane(-1);
    }

    public void setGridPane(int k){
        days.getChildren().clear();

        LocalDate lD = LocalDate.now();
        int day = lD.getDayOfMonth();
        int nrDay = lD.lengthOfMonth();

        for(int i = 0 ; i < 7 ; i++){
            days.add(new Label(lD.getDayOfWeek().toString()) , i , 0);
            lD = lD.plusDays(1);
        }

        for(int i = 0 ; i < 14 ; i++){
            buttonsImage[i] = new ImageView(day +".jpg");
            if(k == i) {
                buttonsImage[i].setFitHeight(105);
                buttonsImage[i].setFitWidth(105);
                buttonsImage[i].setEffect(new DropShadow(40, Color.RED));
            }else{
                buttonsImage[i].setFitHeight(85);
                buttonsImage[i].setFitWidth(85);
            }
            day = (day >= nrDay) ? 1 : day + 1;

            if(i < 7) days.add(buttonsImage[i] , i , 1);
            else days.add(buttonsImage[i] , i - 7 , 2);

        }

        days.setHgap(10); //horizontal gap in pixels => that's what you are asking for
        days.setVgap(10); //vertical gap in pixels
        days.setPadding(new Insets(10, 10, 10, 10));
        days.setAlignment(Pos.CENTER);
        days.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(2, 2, 2, 2))));
    }

    public void selectDay (MouseEvent e){
        if (e.getButton().equals(MouseButton.PRIMARY)) {
            int y = (int) ((e.getX() - 60) / 100);
            int x = (int) ((e.getY() + 60) / 100);

            setGridPane((x-1)*7 + y);

            LocalDate localDate = LocalDate.now();
            localDate = localDate.plusDays((x-1)*7 + y);

            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formatDateTime = localDate.format(format);

            particip.setItems(SignInHelper.setAllActivitatiParticip(formatDateTime));
        }
    }
}
