package sample;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class NpC {
    Image imageHolder;
    ImageView imageView;
    Spawns t1;

    public void theNpc(String charsImg, int imgHeight, int imgWidth, double defaultPosX, double defaultPosY) {
        this.imageHolder = new Image(charsImg);
        this.imageView = new ImageView(imageHolder);

        imageView.setFitHeight(imgHeight);
        imageView.setFitWidth(imgWidth);

        imageView.setX(defaultPosX);
        imageView.setY(defaultPosY);
    }

    public void playerMovement(Scene scene, int dist) {

        int xLimitation = ((int) ((scene.getWidth() / 2) / dist) - 1) * dist;

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.RIGHT) {
                imageView.setLayoutX(imageView.getLayoutX() + dist);
                if (imageView.getLayoutX() >= xLimitation) {
                    imageView.setLayoutX(xLimitation);
                }


            } else if (event.getCode() == KeyCode.LEFT) {
                imageView.setLayoutX(imageView.getLayoutX() - dist);
                if (imageView.getLayoutX() <= -xLimitation) {
                    imageView.setLayoutX(-xLimitation);
                }
            }
        });
    }

    public void npcMovement(double speed, long delay) {
        t1 = new Spawns(imageView, speed, delay);
        t1.start();
    }
}
