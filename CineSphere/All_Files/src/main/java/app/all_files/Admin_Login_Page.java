package app.all_files;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Admin_Login_Page {

    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Admin_Login_Page.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("CineSphere");
        stage.show();
        stage.setX(220);
        stage.setY(45);
        stage.setResizable(false);
        //stage.setMaximized(true);

    }
}

