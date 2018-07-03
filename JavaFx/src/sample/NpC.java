package sample;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;

public class NpC {
    ImageView imageView;
    // Sets the default characteristics of the objects
    public void theNpc(String imageHolder, int imgHeight, int imgWidth, double defaultPosX, double defaultPosY) {
        this.imageView = new ImageView(imageHolder);

        imageView.setFitHeight(imgHeight);
        imageView.setFitWidth(imgWidth);

        imageView.setLayoutX(defaultPosX);
        imageView.setLayoutY(defaultPosY);
        imageView.setScaleZ(0);
    }

    // Player movement
    public void playerMovement(Scene scene, int dist) {
        int xLimitation = (int) scene.getWidth() - (dist * 2);

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

    // Add the movement functionality to each object on a different thread
    public void npcMovement(double speed, long delay, Scene scene, ArrayList<Integer> randomPos, NpC player,String stringImage) {
        Spawns spawns = new Spawns(imageView, speed, delay, scene, randomPos, player,stringImage);
        spawns.objMovement();
        spawns.collisionDetection();
    }
}
