package sample;

import java.sql.SQLException;
import java.sql.Statement;

public class Stats {
    static int score = 0;
    static int playerMaxHp = 0;

    public int getPlayerMaxHp() {
        return playerMaxHp;
    }

    public void setPlayerMaxHp(int playerMaxHp) {
        Stats.playerMaxHp = playerMaxHp;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int value) {
        this.score = value;
    }

    public void insertIntoDB(Statement statement, String query) throws SQLException {
        statement.executeUpdate(query);
    }

    public void clearDB(Statement statement) throws SQLException {
        statement.executeUpdate("TRUNCATE `admin`.`scores`");
    }


}
