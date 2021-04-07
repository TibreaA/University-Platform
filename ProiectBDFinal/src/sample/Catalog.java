package sample;

import controller.SignInHelper;
import controller.formatNota;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Catalog {
    @FXML
    private TableView<formatNota> listaNote;

    public void initialize(){
        listaNote.setItems(SignInHelper.catalogStudent());
    }

    public void handleClickDownloadGrades(ActionEvent event) {
        DirectoryChooser dirChooser = new DirectoryChooser();
        File chosenDir = dirChooser.showDialog(listaNote.getScene().getWindow());
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

        StringBuilder s = new StringBuilder();

        for(formatNota f : SignInHelper.catalogStudent()){
            s.append(f.getGrade()).append("\t").append(f.getProfessor()).append("\t").append(f.getCourses()).append("\t").append(f.getPondere());
            s.append("\n");
        }

        try {
            FileWriter myWriter = new FileWriter(chosenDir.getPath() + "/Grades.txt");
            myWriter.write(s.toString());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
