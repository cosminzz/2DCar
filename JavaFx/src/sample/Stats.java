package sample;

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
}
