package sample;

import javafx.scene.image.ImageView;

public class Spawns extends Thread {
    ImageView obj;
    double speed;
    boolean isMoving = true;
    long delay;

    public Spawns(ImageView obj, double speed, long delay) {
        this.obj = obj;
        this.speed = speed;
        this.delay = delay;
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
            System.out.println(obj.getLayoutY());
        }
    }


}
