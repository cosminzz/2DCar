package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

// Create separate task for each object
public class Spawns {
    ImageView obj;
    double speed;
    long delay;
    Scene scene;
    ArrayList<Integer> randomPosition;
    Random random = new Random();
    UiManager uiManager = new UiManager();
    String stringImage;
    NpC player;
    TranslateTransition transition = new TranslateTransition();

    public Spawns(ImageView obj, double speed, long delay, Scene scene, ArrayList<Integer> randomPosition, NpC player, String defaultStringImage) {
        this.obj = obj;
        this.speed = speed;
        this.delay = delay;
        this.scene = scene;
        this.player = player;
        this.randomPosition = randomPosition;
        this.stringImage = defaultStringImage;
    }

    // The method that starts the Animation
    public void objMovement() {
        // Set the direction the object is heading
        double randomYDirection = random.nextInt(500) + scene.getHeight() - obj.getLayoutY();
        transition.setDuration(Duration.seconds(random.nextInt(10) + 1));
        transition.setToY(randomYDirection);
        transition.setNode(obj);

        transition.setOnFinished(event -> {
            System.out.println("End of the line!! ");

            //Reset GFX
            String stringImage = uiManager.rndGfx();
            this.stringImage = stringImage;
            Image newImage = new Image(stringImage);
            obj.setImage(newImage);

            //Reset Position
            obj.setLayoutX((this.randomPosition.get(random.nextInt(this.randomPosition.size()))) - obj.getFitWidth() / 2);
            obj.setLayoutY((-this.randomPosition.get(random.nextInt(this.randomPosition.size()))) - randomYDirection);

            // Recursive!
            objMovement();
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

    private void getCollision () {
        if ((obj.getBoundsInParent().intersects(player.imageView.getBoundsInParent()))) {
            if (stringImage.equals("sample/coin.png")) {
                System.out.println(stringImage);
            } else if (stringImage.equals("sample/mine.png")) {
                System.out.println(stringImage);
            }
        }
    }
}



