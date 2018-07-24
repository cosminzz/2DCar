package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class UiManager {
    //Text Displays
    static Text scoreTxt;
    static Text playerMaxHpTxt;
    static Text gameOverTxt;
    static Text getDbScoreTxt;
    static Button restart;
    static Button reset;
    Group layout;
    Scene scene;
    //Character objects
    NpC player;
    NpC spawnObj1, spawnObj2, spawnObj3, spawnObj4;
    NpC background1, background2, background3;
    //Scene sizes
    int sceneWidth = 500;
    int sceneHeight = 500;
    Stats stats = new Stats();
    int scoreDef = 0;
    static int playerMaxHpDef = 2;
    //Character movement
    static int dist = 50;
    double speed = 1;
    long delay = 0;
    Random random = new Random();
    ArrayList<Integer> npcYPositionHolder;
    String[] randomObjGfxPaths = {"sample/coin.png", "sample/mine.png"};

    public void display(Stage primaryStage) {
        layout = new Group();
        scene = new Scene(layout, sceneWidth, sceneHeight);
        scoreTxt = new Text("0");
        playerMaxHpTxt = new Text("0");
        gameOverTxt = new Text("Game Over");
        getDbScoreTxt = new Text("Null");
        restart = new Button();
        reset = new Button();

        player = new NpC();
        spawnObj1 = new NpC();
        spawnObj2 = new NpC();
        spawnObj3 = new NpC();
        spawnObj4 = new NpC();
        background1 = new NpC();
        background2 = new NpC();
        background3 = new NpC();
        String defaultStringImage = rndGfx();

        // Instantiated objects initial object setup
        player.theNpc("sample/car.png", 100, 100, scene.getWidth() / 2 - dist, scene.getHeight() - 110);
        spawnObj1.theNpc(defaultStringImage, 50, 50, npcSpawnPos(), -npcYPositionHolder.get(random.nextInt(npcYPositionHolder.size())));
        spawnObj2.theNpc(defaultStringImage, 50, 50, npcSpawnPos(), -npcYPositionHolder.get(random.nextInt(npcYPositionHolder.size())));
        spawnObj3.theNpc(defaultStringImage, 50, 50, npcSpawnPos(), -npcYPositionHolder.get(random.nextInt(npcYPositionHolder.size())));
        spawnObj4.theNpc(defaultStringImage, 50, 50, npcSpawnPos(), -npcYPositionHolder.get(random.nextInt(npcYPositionHolder.size())));
        background1.theNpc("sample/road1.png", 500, 500, 0, 0);
        background2.theNpc("sample/road2.png", 500, 500, 0, -500);
        background3.theNpc("sample/road3.png", 500, 500, 0, -1000);

        // Add objects to the layout
        layout.getChildren().addAll(background1.imageView, background2.imageView, background3.imageView);
        layout.getChildren().add(spawnObj1.imageView);
        layout.getChildren().add(spawnObj2.imageView);
        layout.getChildren().add(spawnObj3.imageView);
        layout.getChildren().add(spawnObj4.imageView);
        layout.getChildren().add(player.imageView);
        layout.getChildren().addAll(scoreTxt, playerMaxHpTxt);
        // Ui elements to layout
        layout.getChildren().add(gameOverTxt);
        layout.getChildren().add(getDbScoreTxt);
        layout.getChildren().add(restart);
        layout.getChildren().add(reset);

        buttonText(restart, "Retry", 25, 150, 50, 175, 400, Color.BLACK);
        restart.setVisible(false);
        buttonText(reset, "R", 12, 20, 20, 450, 450, Color.RED);
        reset.setVisible(false);

        // Initialize player controller
        player.playerMovement(scene, dist);

        // Initialize spawned object movement
        spawnObj1.npcMovement(speed, delay, scene, npcYPositionHolder, player, defaultStringImage);
        spawnObj2.npcMovement(speed, delay, scene, npcYPositionHolder, player, defaultStringImage);
        spawnObj3.npcMovement(speed, delay, scene, npcYPositionHolder, player, defaultStringImage);
        spawnObj4.npcMovement(speed, delay, scene, npcYPositionHolder, player, defaultStringImage);

        // Initialize background objects movement
        background1.bckMovement(speed, scene, 0);
        background2.bckMovement(speed, scene, -499);
        background3.bckMovement(speed, scene, -999);

        //Set the score/hp texts position
        setText(scoreTxt, 30, 10, scene.getHeight() - (scene.getHeight() - 50), Color.YELLOW);
        setText(playerMaxHpTxt, 30, 450, scene.getHeight() - (scene.getHeight() - 50), Color.RED);

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

    public void setText(Text text, int size, double posX, double posY, Color color) {
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, size));
        text.setFill(color);
        text.setStrokeWidth(1);
        text.setStroke(Color.BLACK);
        text.setLayoutX(posX);
        text.setLayoutY(posY);
    }

    public void buttonText(Button button, String text, int textSize, double sizeX, double sizeY, double posX, double posY, Color colorTxt) {
        button.setText(text);
        button.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, textSize));
        button.setPrefSize(sizeX, sizeY);
        button.setLayoutX(posX);
        button.setLayoutY(posY);
        button.setTextFill(colorTxt);
    }
}
