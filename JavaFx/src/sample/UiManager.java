package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class UiManager {
    Group layout;
    Scene scene;

    //Character objects
    NpC player;
    NpC spawnObj1;
    NpC spawnObj2;
    NpC spawnObj3;
    NpC spawnObj4;

    //Scene sizes
    int sceneWidth = 500;
    int sceneHeight = 500;

    //Character movement
    int dist = 50;
    double speed = 10;
    long delay = 50;

    Random random = new Random();
    ArrayList<Integer> npcYPositionHolder;

    public void display(Stage primaryStage) {
        layout = new Group();
        scene = new Scene(layout, sceneWidth, sceneHeight);
        player = new NpC();
        spawnObj1 = new NpC();
        spawnObj2 = new NpC();
        spawnObj3 = new NpC();
        spawnObj4 = new NpC();

        // Characters initial object setup
        player.theNpc("sample/car.png", 100, 100, scene.getWidth() / 2 - dist, scene.getHeight() - 110);
        spawnObj1.theNpc("sample/coin.png", 50, 50, npcSpawnPos(), 200);
        spawnObj2.theNpc("sample/coin.png", 50, 50, npcSpawnPos(), 200);
        spawnObj3.theNpc("sample/coin.png", 50, 50, npcSpawnPos(), 200);
        spawnObj4.theNpc("sample/coin.png", 50, 50, npcSpawnPos(), 200);//-npcYPositionHolder.get(random.nextInt(npcYPositionHolder.size()))

        // Add characters to the layout
        layout.getChildren().add(spawnObj1.imageView);
        layout.getChildren().add(spawnObj2.imageView);
        layout.getChildren().add(spawnObj3.imageView);
        layout.getChildren().add(spawnObj4.imageView);
        layout.getChildren().add(player.imageView);

        // Initialize player controller
        player.playerMovement(scene, dist);

        // Initialize spawned object movement
        spawnObj1.npcMovement(speed, delay, scene, npcYPositionHolder);
        spawnObj2.npcMovement(speed, delay, scene, npcYPositionHolder);
        spawnObj3.npcMovement(speed, delay, scene, npcYPositionHolder);
        spawnObj4.npcMovement(speed, delay, scene, npcYPositionHolder);

        //Set the main stage where the scene>layouts>characters are added
        primaryStage.setTitle("The Car Game");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public int npcSpawnPos() {
        //Sets the npc spawn positions Y
        int additive = dist;
        int increment = 0;
        //Sets the length of the list containing coordinates
        int maxListLocations = ((int) (scene.getWidth() / dist)-2);
        ArrayList<Integer> npcPositionHolder = new ArrayList<>(maxListLocations);

        for (int i = 0; i < maxListLocations; i++) {
            // Creates the list values based on the size of the list
            increment = increment + additive;
            npcPositionHolder.add(increment);
        }
        int randomPosY = npcPositionHolder.get(random.nextInt(npcPositionHolder.size()));
        // Saves the reference of the values that are sent to the NPC
        this.npcYPositionHolder = npcPositionHolder;
        return randomPosY;
    }
}
