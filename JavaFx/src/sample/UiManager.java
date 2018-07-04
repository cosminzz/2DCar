package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class UiManager {
    Group layout;
    Scene scene;
    //Character objects
    NpC player;
    NpC spawnObj1, spawnObj2, spawnObj3, spawnObj4;
    //Scene sizes
    int sceneWidth = 500;
    int sceneHeight = 500;
    //Text Displays
    static Text scoreTxt;
    static Text playerMaxHpTxt;
    Stats stats = new Stats();
    int scoreDef = 0;
    int playerMaxHpDef = 10;
    //Character movement
    int dist = 50;
    double speed = 10;
    long delay = 40;

    Random random = new Random();
    ArrayList<Integer> npcYPositionHolder;
    String[] randomObjGfxPaths = {"sample/coin.png", "sample/mine.png"};

    public void display(Stage primaryStage) {
        layout = new Group();
        scene = new Scene(layout, sceneWidth, sceneHeight);
        scoreTxt = new Text("0");
        playerMaxHpTxt = new Text("0");

        player = new NpC();
        spawnObj1 = new NpC();
        spawnObj2 = new NpC();
        spawnObj3 = new NpC();
        spawnObj4 = new NpC();
        String defaultStringImage = rndGfx();

        // Characters initial object setup
        player.theNpc("sample/car.png", 100, 100, scene.getWidth() / 2 - dist, scene.getHeight() - 110);
        spawnObj1.theNpc(defaultStringImage, 50, 50, npcSpawnPos(), -npcYPositionHolder.get(random.nextInt(npcYPositionHolder.size())));
        spawnObj2.theNpc(defaultStringImage, 50, 50, npcSpawnPos(), -npcYPositionHolder.get(random.nextInt(npcYPositionHolder.size())));
        spawnObj3.theNpc(defaultStringImage, 50, 50, npcSpawnPos(), -npcYPositionHolder.get(random.nextInt(npcYPositionHolder.size())));
        spawnObj4.theNpc(defaultStringImage, 50, 50, npcSpawnPos(), -npcYPositionHolder.get(random.nextInt(npcYPositionHolder.size())));

        // Add objects to the layout
        layout.getChildren().add(spawnObj1.imageView);
        layout.getChildren().add(spawnObj2.imageView);
        layout.getChildren().add(spawnObj3.imageView);
        layout.getChildren().add(spawnObj4.imageView);
        layout.getChildren().add(player.imageView);
        layout.getChildren().addAll(scoreTxt, playerMaxHpTxt);

        // Initialize player controller
        player.playerMovement(scene, dist);

        // Initialize spawned object movement
        spawnObj1.npcMovement(speed, delay, scene, npcYPositionHolder, player, defaultStringImage);
        spawnObj2.npcMovement(speed, delay, scene, npcYPositionHolder, player, defaultStringImage);
        spawnObj3.npcMovement(speed, delay, scene, npcYPositionHolder, player, defaultStringImage);
        spawnObj4.npcMovement(speed, delay, scene, npcYPositionHolder, player, defaultStringImage);

        //Set the score/hp texts position
        setText(scoreTxt, 30, 10, scene.getHeight() - (scene.getHeight() - 50),Color.YELLOW);
        setText(playerMaxHpTxt, 30, 450, scene.getHeight() - (scene.getHeight() - 50),Color.RED);

        //Setup default stats class
        stats.setScore(scoreDef);
        stats.setPlayerMaxHp(playerMaxHpDef);
        playerMaxHpTxt.setText(String.valueOf(stats.getPlayerMaxHp()));

        //Set the main stage where the scene>layouts>characters are added
        primaryStage.setTitle("The Car Game");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    private int npcSpawnPos() {
        //Sets the npc spawn positions Y
        int additive = dist;
        int increment = 0;
        //Sets the length of the list containing coordinates
        int maxListLocations = ((int) (scene.getWidth() / dist) - 2);
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

    // Returns random object graphics
    public String rndGfx() {
        int getGfx = random.nextInt(randomObjGfxPaths.length);
        return randomObjGfxPaths[getGfx];
    }

    private void setText(Text text, int size, double posX, double posY,Color color) {
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, size));
        text.setFill(color);
        text.setStrokeWidth(1);
        text.setStroke(Color.BLACK);
        text.setLayoutX(posX);
        text.setLayoutY(posY);
    }
}
