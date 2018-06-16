package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UiManager {
    Group layout;
    Scene scene;
    NpC player;


    public void display(Stage primaryStage) throws Exception {
        layout = new Group();
        scene = new Scene(layout, 500, 500);
        player = new NpC();

        player.theNpc("sample/car.png",100,100,scene.getWidth()/2-50,scene.getHeight()-110);

        layout.getChildren().add(player.imageView);
        player.playerMovement(scene,50);
        player.npcMovement();

        primaryStage.setTitle("The Car Game");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
