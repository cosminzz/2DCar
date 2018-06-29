package sample;

import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;

public class NpC {
    Image imageHolder;
    ImageView imageView;
    Thread thread;
//    Spawns thread;

    public void theNpc(String imageHolder, int imgHeight, int imgWidth, double defaultPosX, double defaultPosY) {
        this.imageHolder = new Image(imageHolder);
        this.imageView = new ImageView(this.imageHolder);

        imageView.setFitHeight(imgHeight);
        imageView.setFitWidth(imgWidth);

        imageView.setLayoutX(defaultPosX);
        imageView.setLayoutY(defaultPosY);
        imageView.setScaleZ(0);
    }

    public void playerMovement(Scene scene, int dist) {
        int xLimitation = (int) scene.getWidth() - (dist*2);

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.RIGHT) {
                imageView.setLayoutX(imageView.getLayoutX() + dist);
                if (imageView.getLayoutX() >= xLimitation) {
                    imageView.setLayoutX(xLimitation);
                }
            } else if (event.getCode() == KeyCode.LEFT) {
                imageView.setLayoutX(imageView.getLayoutX() - dist);
                if (imageView.getLayoutX() <= -dist) {
                    imageView.setLayoutX(0);
                }
            }
        });
    }

    public void npcMovement(double speed, long delay, Scene scene, ArrayList<Integer> randomPos) {
//        thread = new Spawns(imageView, speed, delay, scene, randomPos);
//        thread.start();

        Task test = new TEST(imageView, speed, delay, scene, randomPos);
        thread = new Thread(test);
        thread.setDaemon(true);
        thread.start();

//        IteratingTask task = new IteratingTask(8000000);
//        System.out.println(task);
    }
}
