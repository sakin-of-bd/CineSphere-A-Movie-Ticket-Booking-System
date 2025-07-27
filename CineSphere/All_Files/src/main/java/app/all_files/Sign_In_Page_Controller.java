package app.all_files;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;


public class Sign_In_Page_Controller {

    @FXML
    private Button Sign_In;

    @FXML
    private Button Sign_Up;

    @FXML
    private Button admin_login;

    @FXML
    private Hyperlink forget_password;

    @FXML
    private PasswordField passowrd;

    @FXML
    private TextField username;

    @FXML
    void admin_log_in(MouseEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin_Login_Page.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void forgetting_password(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Forgot_Password_Verification_Page.fxml"));
            Parent root = loader.load();

            // Get the current stage from any node in the scene
            Stage stage = (Stage) forget_password.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Password Reset Verification");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void signing_in(MouseEvent event) {

    }

    @FXML
    void signing_up(MouseEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Sign_Up_Page.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}







