package Triviador;

public class Player {
    public final String nickname;
    public final PlayersEnum state;

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    private int points;

    public Player(String nickname, PlayersEnum state) {
        this.nickname = nickname;
        this.state = state;
    }
}
