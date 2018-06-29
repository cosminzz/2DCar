package sample;

import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class TEST extends Task<Void> {
    ImageView obj;
    double speed;
    boolean isMoving = true;
    long delay;
    Scene scene;
    ArrayList<Integer> randomPosition;
    Random random = new Random();

    public TEST(ImageView obj, double speed, long delay, Scene scene, ArrayList<Integer> randomPosition) {
        this.obj = obj;
        this.speed = speed;
        this.delay = delay;
        this.scene = scene;
        this.randomPosition = randomPosition;

    }

    @Override
    protected Void call() throws Exception {
        System.out.println("HELLOOOOOOOOOOOOOOOO");
        while (isMoving) {
            hue();
        }
        return null;
    }

    public void hue() {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double newPosition = obj.getLayoutY() + speed;
        System.out.println(Thread.currentThread().getName() + " move at position " + newPosition + "<<<<<<<<<<<");
        obj.setLayoutY(newPosition);

        if (obj.getLayoutY() >= scene.getHeight() + 100) {
            obj.setLayoutY(100);
        }
    }

}



