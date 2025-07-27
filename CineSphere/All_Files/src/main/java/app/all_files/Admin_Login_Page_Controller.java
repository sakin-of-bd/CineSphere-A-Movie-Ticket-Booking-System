package app.all_files;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Admin_Login_Page_Controller {

    @FXML
    private Button Sign_In;

    @FXML
    private Button admin_login;

    @FXML
    private Button admin_login1;

    @FXML
    private PasswordField passowrd;

    @FXML
    private Hyperlink reset_username_password;

    @FXML
    private TextField username;

    @FXML
    void admin_reset_password(MouseEvent event) {

    }


    @FXML
    void admin_signing_in(MouseEvent event) {
        String inputUsername = username.getText();
        String inputPassword = passowrd.getText();

        if (inputUsername.equals("admin") && inputPassword.equals("1234")) {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin_Dashboard.fxml")); // Replace with your actual dashboard FXML
                Parent root = loader.load();

                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Admin Dashboard");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Login failed - Show error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password. Please try again.");
            alert.showAndWait();
        }
    }


    @FXML
    void user_sign_in(MouseEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Sign_In_Page.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void user_sign_up(MouseEvent event) {
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
