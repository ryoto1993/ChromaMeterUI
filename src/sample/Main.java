package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    private Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ui.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("色彩照度計の照度・色温度");
        primaryStage.setOnCloseRequest(event -> Platform.exit());

        controller = fxmlLoader.getController();
        controller.stage = primaryStage;
        controller.setFullscreenListener();

        Timeline timer = new Timeline(new KeyFrame(Duration.millis(300), event -> controller.update()));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
