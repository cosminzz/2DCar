package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UiManager {
    Group layout;
    Scene scene;
    NpC player;
    NpC spawnObj1;

    public void display(Stage primaryStage) {
        int dist = 50;
        double speed = 10;
        long delay = 50;

        layout = new Group();
        scene = new Scene(layout, 500, 500);
        player = new NpC();
        spawnObj1 = new NpC();

        // Characters initial setup
        player.theNpc("sample/car.png", 100, 100, scene.getWidth() / 2 - 50, scene.getHeight() - 110);
        spawnObj1.theNpc("sample/coin.png", 50, 50, scene.getWidth() / dist + 50, -10);

        // Add characters to the layout
        layout.getChildren().add(spawnObj1.imageView);
        layout.getChildren().add(player.imageView);

        // Initialize player controller
        player.playerMovement(scene, dist);

        // Initialize spawned object movement
        spawnObj1.npcMovement(speed, delay);

        primaryStage.setTitle("The Car Game");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
