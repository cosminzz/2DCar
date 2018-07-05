package sample;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

// Create separate task for each object
public class Spawns {
    ImageView obj;
    double speed;
    long delay;
    Scene scene;
    double posY = 0;
    ArrayList<Integer> randomPosition;
    Random random = new Random();
    UiManager uiManager = new UiManager();
    String stringImage;
    NpC player;
    TranslateTransition transition = new TranslateTransition();
    boolean collided = false;

    Stats stats = new Stats();

    //Used for spawned objects
    public Spawns(ImageView obj, double speed, long delay, Scene scene, ArrayList<Integer> randomPosition, NpC player, String defaultStringImage) {
        this.obj = obj;
        this.speed = speed;
        this.delay = delay;
        this.scene = scene;
        this.player = player;
        this.randomPosition = randomPosition;
        this.stringImage = defaultStringImage;
    }

    //Used for looping background
    public Spawns(ImageView obj, double speed, Scene scene, double posY) {
        this.obj = obj;
        this.speed = speed;
        this.scene = scene;
        this.posY = posY;
    }

    // The method that starts the Animation
    public void objMovement() {
        // Set the direction the object is heading
        double randomYDirection = random.nextInt(500) + scene.getHeight() - obj.getLayoutY();
        transition.setDuration(Duration.seconds((random.nextInt((5 - 3) + 1) + 5) * speed));
        transition.setToY(randomYDirection);
        transition.setNode(obj);

        transition.setOnFinished(event -> {
            //Reset GFX
            String stringImage = uiManager.rndGfx();
            this.stringImage = stringImage;
            Image newImage = new Image(stringImage);
            obj.setImage(newImage);

            //Reset Position
            obj.setLayoutX((this.randomPosition.get(random.nextInt(this.randomPosition.size()))) - obj.getFitWidth() / 2);
            obj.setLayoutY((-this.randomPosition.get(random.nextInt(this.randomPosition.size()))) - randomYDirection);

            if (!obj.isVisible())
                obj.setVisible(true);

            // Recursive!
            objMovement();
            this.collided = false;
        });
        transition.play();
    }

    public void collisionDetection() {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                getCollision();
            }
        };
        animationTimer.start();
    }

    private void getCollision() {
        if ((obj.getBoundsInParent().intersects(player.imageView.getBoundsInParent())) && (this.collided == false)) {
            if (stringImage.equals("sample/coin.png")) {
                this.collided = true;
                stats.setScore(stats.getScore() + 10);
                uiManager.scoreTxt.setText(String.valueOf(stats.getScore()));
                obj.setVisible(false);

            } else if (stringImage.equals("sample/mine.png")) {
                this.collided = true;
                stats.setPlayerMaxHp(stats.getPlayerMaxHp() - 1);
                uiManager.playerMaxHpTxt.setText(String.valueOf(stats.getPlayerMaxHp()));
                obj.setVisible(false);

                if (stats.getPlayerMaxHp() <= 0) {
                    // On player death change the scene elements
                    uiManager.setText(uiManager.gameOverTxt, 45, 110, 100, Color.RED);
                    uiManager.gameOverTxt.setVisible(true);
                    uiManager.restart.setVisible(true);
                    uiManager.reset.setVisible(true);

                    player.imageView.setVisible(false);
                    player.imageView.setTranslateZ(1000);

                    // Restart positions on button press
                    uiManager.restart.setOnAction(event -> {
                        System.out.println("Button Restart Pressed");
                        defaultPositions();
                    });

                    // Delete DB entries on button press
                    uiManager.reset.setOnAction(event -> System.out.println("Reset Button Pressed"));
                }
            }
        }
    }

    public void backgroundMovement() {
        transition.setDuration(Duration.seconds(5));
        transition.setToY(1000);
        transition.setNode(obj);
        transition.setCycleCount(Animation.INDEFINITE);
        transition.setInterpolator(Interpolator.LINEAR);
        transition.play();
    }

    private void defaultPositions() {
        System.out.println("Player default");
        // Handle player status
        player.imageView.setVisible(true);
        player.imageView.setTranslateZ(0);
        player.imageView.setLayoutX(scene.getWidth() / 2 - uiManager.dist);
        player.imageView.setLayoutY(scene.getHeight() - 110);

        System.out.println("Ui Default");
        // Handle UI status
        uiManager.reset.setVisible(false);
        uiManager.restart.setVisible(false);
        uiManager.gameOverTxt.setVisible(false);
        stats.setPlayerMaxHp(uiManager.playerMaxHpDef);
        uiManager.playerMaxHpTxt.setText(String.valueOf(stats.getPlayerMaxHp()));
        stats.setScore(0);
        uiManager.scoreTxt.setText(String.valueOf(stats.getScore()));
    }
}



