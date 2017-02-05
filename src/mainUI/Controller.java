package mainUI;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.*;

public class Controller {
    @FXML public Label label_illuminance;
    @FXML public Label label_temp;
    @FXML public Button full_screen_button;
    @FXML public Label ill_title;
    @FXML public Label tmp_title;
    @FXML public Label main_title_label;

    public static Stage stage;

    public void update() {
        String ill = "", tmp = "";

        try{
            File ill_file = new File("illuminance.txt");
            File tmp_file = new File("temperature.txt");
            BufferedReader ill_br = new BufferedReader(new FileReader(ill_file));
            BufferedReader tmp_br = new BufferedReader(new FileReader(tmp_file));

            ill = ill_br.readLine();
            tmp = tmp_br.readLine();

            if(ill.equals(null) || tmp.equals(null)) {
                return;
            }

            ill_br.close();
            tmp_br.close();

        }catch(FileNotFoundException e){
            System.out.println(e);
        }catch(IOException e){
            System.out.println(e);
        }

        label_illuminance.setText(ill + " lx");
        label_temp.setText(tmp + " K");
    }

    public void switchFullScreen() {
        if(stage.isFullScreen()) {
            stage.setFullScreen(false);
        } else {
            stage.setFullScreen(true);
        }
    }

    public void setFullscreenListener() {
        ReadOnlyBooleanProperty fullScreenProperty = stage.fullScreenProperty();
        fullScreenProperty.addListener((observable, oldValue, isFullScreen) -> {
            if(isFullScreen) {
                ill_title.setStyle("-fx-font-size: 90px");
                tmp_title.setStyle("-fx-font-size: 90px");
                label_illuminance.setStyle("-fx-font-size: 200px");
                label_temp.setStyle("-fx-font-size: 200px");
                main_title_label.setStyle("-fx-font-size: 90px");
            } else {
                ill_title.setStyle("-fx-font-size: 50px");
                tmp_title.setStyle("-fx-font-size: 50px");
                label_illuminance.setStyle("-fx-font-size: 120px");
                label_temp.setStyle("-fx-font-size: 120px");
                main_title_label.setStyle("-fx-font-size: 50px");
            }
        });
    }
}
