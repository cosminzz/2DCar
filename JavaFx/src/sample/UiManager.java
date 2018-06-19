package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class UiManager {
    Group layout;
    Scene scene;
    NpC player;
    NpC spawnObj1;

    //Scene sizes
    int sceneWidth = 500;
    int sceneHeight = 500;

    //Character movement
    int dist = 50;
    double speed = 5;
    long delay = 50;

    Random random = new Random();

    public void display(Stage primaryStage) {
        layout = new Group();
        scene = new Scene(layout, sceneWidth, sceneHeight);
        player = new NpC();
        spawnObj1 = new NpC();

        // Set the npc spawn position X
        int randomPosX = random.nextInt(30 - 10 + 1) + 10;

        // Characters initial object setup
        player.theNpc("sample/car.png", 100, 100, scene.getWidth() / 2 - 50, scene.getHeight() - 110);
        spawnObj1.theNpc("sample/coin.png", 50, 50, npcSpawnY(), -randomPosX);

        // Add characters to the layout
        layout.getChildren().add(spawnObj1.imageView);
        layout.getChildren().add(player.imageView);

        // Initialize player controller
        player.playerMovement(scene, dist);

        // Initialize spawned object movement
        spawnObj1.npcMovement(speed, delay, scene);

        //Set the main stage where the scene>layouts>characters are added
        primaryStage.setTitle("The Car Game");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
int test = npcSpawnY();
    public int npcSpawnY() {
        //Sets the npc spawn positions Y
        int additive = 30;
        int increment = 0;
        int maxYListLocations = (int) (scene.getWidth() / dist);
        ArrayList<Integer> npcXPositionHolder = new ArrayList<>(maxYListLocations);

        for (int i = 0; i < maxYListLocations; i++) {
            increment = increment + maxYListLocations + additive;
            npcXPositionHolder.add(increment);
        }
        int randomPosY = npcXPositionHolder.get(random.nextInt(npcXPositionHolder.size()));
        return randomPosY;
    }
}
