package sample;

import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main extends Application {
    UiManager mainLayout = new UiManager();
    Stage stage = new Stage();
    static Statement statement;
    Spawns spawns = new Spawns();
    AudioManager audioManager = new AudioManager();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) {
        mainLayout.display(stage);

        connectToDb();
        spawns.statement=this.statement;

        audioManager.playSound(true,"D:\\CursJava\\FinalProject\\2DCar\\JavaFx\\src\\sample\\music.wav",-10f);
    }

    public void connectToDb() {
        String dbUrl = "jdbc:mysql://localhost/admin?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
        String user = "root";
        String password = "admin";

        try {
            Connection myConnection = DriverManager.getConnection(dbUrl, user, password);
            Statement statement = myConnection.createStatement();
            this.statement = statement;

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
