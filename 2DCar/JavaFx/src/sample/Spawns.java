package sample;

import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Random;

// Create separate task for each object
public class Spawns extends Task<Void> {
    ImageView obj;
    double speed;
    boolean isMoving = true;
    long delay;
    Scene scene;
    ArrayList<Integer> randomPosition;
    Random random = new Random();
    UiManager uiManager = new UiManager();
    String stringImage;
    NpC player;

    public Spawns(ImageView obj, double speed, long delay, Scene scene, ArrayList<Integer> randomPosition, NpC player, String defaultStringImage) {
        this.obj = obj;
        this.speed = speed;
        this.delay = delay;
        this.scene = scene;
        this.player = player;
        this.randomPosition = randomPosition;
        this.stringImage = defaultStringImage;

        System.out.println(stringImage);
    }

    // The method that starts the Task/Thread
    @Override
    protected Void call() {
        while (isMoving) {
            objMovement();
            getCollision();
        }
        return null;
    }

    private void objMovement() {
        try {
            Thread.sleep(delay);
            double newPosition = obj.getLayoutY() + speed;
            System.out.println(Thread.currentThread().getName() + " move at position " + newPosition + "<<<<<<<<<<<");
            obj.setLayoutY(newPosition);

            if (obj.getLayoutY() >= scene.getHeight() + 100) {
                // Reset Object Gfx
                String stringImage = uiManager.rndGfx();
                this.stringImage = stringImage;
                Image newImage = new Image(stringImage);
                obj.setImage(newImage);

                // Reset Object Positions
                obj.setLayoutX((this.randomPosition.get(random.nextInt(this.randomPosition.size()))) - obj.getFitWidth() / 2);
                obj.setLayoutY(-this.randomPosition.get(random.nextInt(this.randomPosition.size())));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void getCollision() {
        if ((obj.getBoundsInParent().intersects(player.imageView.getBoundsInParent()))) {
            if (stringImage.equals("sample/coin.png")) {
                System.out.println(stringImage);
            }else if (stringImage.equals("sample/mine.png")){
                System.out.println(stringImage);
            }
        }
    }
}



