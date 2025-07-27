package app.all_files;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Forgot_Password_Verification_Page_Controller {

    @FXML
    private Button back;

    @FXML
    private Button check;

    @FXML
    private DatePicker date_of_birth;

    @FXML
    private ToggleGroup gender_check;

    @FXML
    private RadioButton gender_female;

    @FXML
    private RadioButton gender_male;

    @FXML
    private TextField phone_number;

    @FXML
    void back_to_user_sign_in(MouseEvent event) {
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
    void checking(MouseEvent event) {

    }

}
