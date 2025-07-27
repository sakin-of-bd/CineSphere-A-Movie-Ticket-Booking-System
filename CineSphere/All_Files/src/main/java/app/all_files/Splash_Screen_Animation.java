package app.all_files;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Splash_Screen_Animation extends Application {
    @Override
    public void start(Stage stage) {
        StackPane splashPane = new StackPane();

        ImageView splashImage = new ImageView(new Image(getClass().getResource("/Images/splash_Image.jpg").toExternalForm()));
        splashImage.setFitWidth(500);
        splashImage.setFitHeight(390);
        splashImage.setPreserveRatio(true);

        Text splashText = new Text("CineSphere");
        splashText.setId("splashText");

        ProgressIndicator progressIndicator = new ProgressIndicator(0);
        Text loadingText = new Text("Loading... 0%");
        loadingText.setId("loadingText");

        splashPane.getChildren().addAll(splashImage, splashText, progressIndicator, loadingText);

        splashText.setTranslateY(-10);
        progressIndicator.setTranslateY(70);
        loadingText.setTranslateY(100);

        Scene splashScene = new Scene(splashPane, 350, 320);
        splashScene.getStylesheets().add(getClass().getResource("/Splash_Screen.css").toExternalForm());



        ScaleTransition zoomInTransition = new ScaleTransition(Duration.seconds(1.0), splashText);
        zoomInTransition.setFromX(0.5);
        zoomInTransition.setFromY(0.5);
        zoomInTransition.setToX(1);
        zoomInTransition.setToY(1);
        zoomInTransition.setCycleCount(1);
        zoomInTransition.setAutoReverse(false);
        zoomInTransition.play();

        LoadingScreen loadingScreen = new LoadingScreen(progressIndicator, loadingText, stage);
        Thread loadingThread = new Thread(loadingScreen);
        loadingThread.setDaemon(true);
        loadingThread.start();

        stage.setScene(splashScene);
        //stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("CineSphere");
        stage.setX(500);
        stage.setY(200);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}

class LoadingScreen implements Runnable {

    private ProgressIndicator progressIndicator;
    private Text loadingText;
    private Stage stage;

    public LoadingScreen(ProgressIndicator progressIndicator, Text loadingText, Stage stage) {
        this.progressIndicator = progressIndicator;
        this.loadingText = loadingText;
        this.stage = stage;
    }

    @Override
    public void run() {
        double progress = 0;
        while (progress <= 1.0) {

//            if (progress == 0.2) {
//                progress += 0.4;
//            }

            final double currentProgress = progress;
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    progressIndicator.setProgress(currentProgress);
                    loadingText.setText("Loading... " + (int) (currentProgress * 100) + "%");
                }
            });

            //progress += 0.01;
            progress += 0.5;

            try {
                Thread.sleep(70);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                progressIndicator.setProgress(1.0);
                loadingText.setText("Loading... 100%");
                showLoginPage();
            }
        });
    }

    private void showLoginPage() {
        try {
            Sign_In_Page signInPage=new Sign_In_Page();
            signInPage.start(stage);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load the Sign-In Page", e);
        }
    }

}

