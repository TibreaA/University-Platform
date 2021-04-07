package sample;

import controller.SignUpHelper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import javax.mail.*;
import javax.mail.internet.*;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

public class SignUp {
    @FXML
    public ComboBox<String> tipUser;
    @FXML
    public Label incorect;
    @FXML
    public PasswordField passwordUser;
    @FXML
    public TextField iban;
    @FXML
    public TextField address;
    @FXML
    public TextField phoneNumber;
    @FXML
    public TextField mail;
    @FXML
    public TextField cnp;
    @FXML
    public TextField lastName;
    @FXML
    public TextField firstName;
    @FXML
    public TextField departament;
    @FXML
    public TextField minHours;
    @FXML
    public TextField maxHours;
    @FXML
    public Label minHours1;
    @FXML
    public Label departament1;
    @FXML
    public Label maxHours1;
    @FXML
    private ImageView logoImage;

    public void initialize() {
        logoImage.setImage(new Image("Logo.jpg"));

        incorect.setVisible(false);

        setVisibleProfObjects(false);

        ObservableList<String> tipUserButtons = tipUser.getItems();

        tipUserButtons.add("Student");
        tipUserButtons.add("Professor");
        tipUserButtons.add("Admin");

        tipUser.getSelectionModel().selectFirst();

        SignUpHelper.open();

    }

    @FXML
    private void handleClickTipUser(){
        switch (tipUser.getValue()){
            case "Student": selectStudent();
                break;
            case "Professor": selectProfessor();
                break;
            case "Admin": selectAdmin();
                break;
        }
    }

    private void selectStudent(){
        setVisibleProfObjects(false);
    }
    private void selectProfessor(){
        setVisibleProfObjects(true);

        dialogConfirmation("Professor" , "professor");
    }
    private void selectAdmin(){
        setVisibleProfObjects(false);

        dialogConfirmation("Admin" , "admin");
    }

    private void setVisibleProfObjects(Boolean b){
        minHours.setVisible(b);
        maxHours.setVisible(b);
        departament.setVisible(b);
        minHours1.setVisible(b);
        maxHours1.setVisible(b);
        departament1.setVisible(b);
    }

    private void dialogConfirmation (String s , String p){
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Confirmation");
        dialog.setHeaderText("Prove you're a " + s + "!");
        dialog.setContentText("Enter the professors' unique password!");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            if(!result.get().equals(p)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Wrong password!");
                tipUser.setValue("Student");
            }
        }else{
            tipUser.setValue("Student");
        }
    }

    @FXML
    private void handleClickLogoImage() throws IOException {
        openNewStage("sample");
    }
    @FXML
    private void handleClickButtonSignIn () throws IOException {
        openNewStage("signIn");
    }
    @FXML
    private void handleClickButtonSignUp () throws IOException {
        openNewStage("signUp");
    }

    public void openNewStage(String s) throws IOException {
        Stage stage = (Stage) logoImage.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource(s + ".fxml"));
        stage.setTitle("Platform UTCN");
        stage.setScene(new Scene(root, 1200, 700));
        stage.setMinHeight(700);
        stage.setMinWidth(1200);
        stage.setMaxHeight(700);
        stage.setMaxWidth(1200);
        stage.show();
    }

    @FXML
    private void handleClickSignUp() throws IOException {
        if(checkInformation()){
            boolean ok ;
            switch (tipUser.getValue()){
                case "Student": ok = SignUpHelper.SignUpStudent(cnp.getText() , firstName.getText() , lastName.getText() , address.getText() , phoneNumber.getText() ,mail.getText(), iban.getText() , passwordUser.getText());
                    break;
                case "Professor":  ok = SignUpHelper.SignUpProfessor(cnp.getText() , firstName.getText() , lastName.getText() , address.getText() , phoneNumber.getText() ,mail.getText(), iban.getText() , passwordUser.getText() , Integer.parseInt(minHours.getText()) , Integer.parseInt(maxHours.getText()) , departament.getText());

                    break;
                case "Admin":  ok = SignUpHelper.SignUpAdmin(cnp.getText() , firstName.getText() , lastName.getText() , address.getText() , phoneNumber.getText() ,mail.getText(), iban.getText() , passwordUser.getText());
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + tipUser.getValue());
            }
            if(ok){
                sendMail();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sign Up");
                alert.setHeaderText(null);
                alert.setContentText("The account has been created!");

                alert.show();

                handleClickButtonSignIn();
            }else{
                incorect.setVisible(true);
            }
        }else{
            incorect.setVisible(true);
        }
    }

    private void sendMail(){
        String to;
        to = (mail.getText().length() > 3) ? mail.getText() : "mail@gmail.com";

        String from = "myusername@gmail.com";
        final String username = "popmariusionut13@gmail.com";//your Gmail username
        final String password = "mariusionutpop13";//your Gmail password

        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        props.put("smtp.socketFactory.class" , "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject("Hi, "+ lastName.getText() + " " + firstName.getText());
            message.setText("\nYour account has been successfully created\n" +
                    "\nUsername:\n"+ mail.getText() +"\n" +
                    "\nPassword:\n" + passwordUser.getText() + "\n\n" + "Welcome to the platform of the Technical University of Cluj-Napoca!");

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    private boolean checkInformation(){
        if(firstName.getText().length() < 2) return false;
        if(lastName.getText().length() < 2) return false;
        if(cnp.getText().length() != 13) return false;
        if(address.getText().length() < 3) return false;
        if(phoneNumber.getText().length() != 10) return false;
        return passwordUser.getText().length() >= 3;
    }
}
