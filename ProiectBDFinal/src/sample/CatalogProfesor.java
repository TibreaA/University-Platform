package sample;

import controller.SignInHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.text.TextAlignment;
import javafx.stage.DirectoryChooser;
import javafx.util.Callback;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

public class CatalogProfesor {
    @FXML
    private ListView<String> activitatiProfesor;
    @FXML
    private ListView<String>  studentiPeActivitate;
    @FXML
    private ListView<String>  note;

    private StringBuilder grades;

    public void initialize(){
        activitatiProfesor.setItems(SignInHelper.activitatiprof());

        activitatiProfesor.setFixedCellSize(50);

        activitatiProfesor.setCellFactory(new Callback<>() {
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

        studentiPeActivitate.setFixedCellSize(50);

        studentiPeActivitate.setCellFactory(new Callback<>() {
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

        note.setFixedCellSize(50);

        note.setCellFactory(new Callback<>() {
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

    public void handleClickActivitate(){
       int activitate =  activitatiProfesor.getSelectionModel().getSelectedIndex();

       studentiPeActivitate.setItems(SignInHelper.studentiPeActivitate(activitate));

    }

    public void handleClickStudent(){
        int student = studentiPeActivitate.getSelectionModel().getSelectedIndex();
        int activitate =  activitatiProfesor.getSelectionModel().getSelectedIndex();

        note.setItems(SignInHelper.noteStudenti(student , activitate));
    }

    public void handleClickAdd(){
        int student = studentiPeActivitate.getSelectionModel().getSelectedIndex();
        int activitate =  activitatiProfesor.getSelectionModel().getSelectedIndex();

        if(!SignInHelper.checkHaveGrade(student , activitate)) {
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("Add Grade");
            dialog.setHeaderText("Course: " + activitatiProfesor.getSelectionModel().getSelectedItem() + "\nStudent: " + studentiPeActivitate.getSelectionModel().getSelectedItem());
            dialog.setContentText("Enter grade: ");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                SignInHelper.addNota(student, activitate, Integer.parseInt(result.get()));
                note.setItems(SignInHelper.noteStudenti(student, activitate));
            }
        }
    }

    public void handleClickUp(){
        int student = studentiPeActivitate.getSelectionModel().getSelectedIndex();
        int activitate =  activitatiProfesor.getSelectionModel().getSelectedIndex();

        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Upgrade Grade");
        dialog.setHeaderText("Course: " + activitatiProfesor.getSelectionModel().getSelectedItem() + "\nStudent: " + studentiPeActivitate.getSelectionModel().getSelectedItem());
        dialog.setContentText("Enter grade: ");

        Optional<String> result = dialog.showAndWait();
        if(result.isPresent()) {
            SignInHelper.upNota(student, activitate , Integer.parseInt(result.get()));
            note.setItems(SignInHelper.noteStudenti(student, activitate));
        }
    }

    public void handleClickViewGrades(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Grades");
        alert.setHeaderText(null);
        alert.setContentText(SignInHelper.noteProf());

        alert.showAndWait();

    }

    public void handleClickDownloadGrades(ActionEvent event) {

        DirectoryChooser dirChooser = new DirectoryChooser();
        File chosenDir = dirChooser.showDialog(activitatiProfesor.getScene().getWindow());
        try {
            File myObj = new File(chosenDir.getPath() + "Grades.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter(chosenDir.getPath() + "/Grades.txt");
            myWriter.write(SignInHelper.noteProf());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
