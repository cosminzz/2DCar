package sample;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.awt.*;

public class Main2 extends Application {
    UiManager mainLayout = new UiManager();
    Stage stage = new Stage();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage theStage) throws Exception {
        theStage.setTitle("Canvas Example");

        Group root = new Group();
        Scene theScene = new Scene(root,500,500);
        theStage.setScene(theScene);

       Button button = new Button("test");
        root.getChildren().addAll(button);
        root.setLayoutX(theScene.getWidth()/2+button.getWidth()/2);
        root.setLayoutY(50);
/*
        Canvas canvas = new Canvas( 400, 200 );
        root.getChildren().add( canvas );

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill( Color.RED );
        gc.setStroke( Color.BLACK );
        gc.setLineWidth(2);
        Font theFont = Font.font( "Times New Roman", FontWeight.BOLD, 48 );
        gc.setFont( theFont );
        gc.fillText( "Hello, World!", 60, 50 );
        gc.strokeText( "Hello, World!", 60, 50 );

        Image earth = new Image( "sample/car.png" );
        gc.drawImage( earth, 180, 100 );
*/
        theStage.show();

    }

}
