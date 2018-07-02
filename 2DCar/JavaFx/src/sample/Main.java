package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    UiManager mainLayout = new UiManager();
    Stage stage = new Stage();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) {
        mainLayout.display(stage);
    }

}
