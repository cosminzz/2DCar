package sample;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;

public class Spawns extends Thread {
    ImageView obj;
    double speed;
    boolean isMoving = true;
    long delay;
    Scene scene;

    UiManager testt = new UiManager();

    public Spawns(ImageView obj, double speed, long delay, Scene scene) {
        this.obj = obj;
        this.speed = speed;
        this.delay = delay;
        this.scene = scene;
    }

    @Override
    public void run() {
        while (isMoving) {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            obj.setLayoutY(obj.getLayoutY() + speed);
            if (obj.getLayoutY() >= scene.getWidth() + 100) {
                System.out.println(obj.getLayoutY());
//                obj.setLayoutY(testt.test);
            }
        }
    }
}



