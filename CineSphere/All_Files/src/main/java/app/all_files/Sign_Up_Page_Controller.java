package app.all_files;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Sign_Up_Page_Controller {

    @FXML
    private Button Sign_In;

    @FXML
    private Button Sign_Up;

    @FXML
    private Button admin_login;

    @FXML
    private DatePicker date_of_birth;

    @FXML
    private TextField first_name;

    @FXML
    private ToggleGroup genderToggleGroup;

    @FXML
    private RadioButton gender_female;

    @FXML
    private RadioButton gender_male;

    @FXML
    private PasswordField last_name;

    @FXML
    private TextField phone_number;

    @FXML
    private PasswordField user_password;

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
    void sign_in(MouseEvent event) {
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
    void sign_up(MouseEvent event) {

    }

}
