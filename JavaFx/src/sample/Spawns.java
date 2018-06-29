package sample;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.Random;

public class Spawns extends Thread {
    ImageView obj;
    double speed;
    boolean isMoving = true;
    long delay;
    Scene scene;
    ArrayList<Integer> randomPosition;
    Random random = new Random();

    public Spawns(ImageView obj, double speed, long delay, Scene scene, ArrayList<Integer> randomPosition) {
        this.obj = obj;
        this.speed = speed;
        this.delay = delay;
        this.scene = scene;
        this.randomPosition = randomPosition;

//        this.obj.setTranslateZ(random.nextInt(100));
//        obj.setScaleZ(0);
    }

    @Override
    public void run() {
        while (isMoving) {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            double newPosition = obj.getLayoutY() + speed;
            System.out.println(Thread.currentThread().getName() + " move at position " + newPosition + "<<<<<<<<<<<");
            obj.setLayoutY(newPosition);

            if (obj.getLayoutY() >= scene.getHeight() + 100) {
//                obj.setLayoutX((this.randomPosition.get(random.nextInt(this.randomPosition.size())))-obj.getFitWidth()/2);
//                obj.setLayoutY(-this.randomPosition.get(random.nextInt(this.randomPosition.size())));
                obj.setLayoutY(100);
//                System.out.println("NEW POS Y " + obj.getLayoutY());
//                System.out.println("NEW POS X " + obj.getLayoutX());
//                System.out.println("NEW POS Z " + obj.getTranslateZ());
//                System.out.println(this.obj.getTranslateZ());

            }
        }
    }
}



