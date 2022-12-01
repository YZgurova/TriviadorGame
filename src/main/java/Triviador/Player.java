package Triviador;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public final int id;
    public final String nickname;
    public final StateEnum state;

    private List<Integer> areas;

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    private int points;

    public Player(String nickname, StateEnum state, int id, int area1, int area2, int area3, int area4) {
        this.nickname = nickname;
        this.state = state;
        this.id=id;
        this.areas= new ArrayList<>();
        areas.add(area1);
        areas.add(area2);
        areas.add(area3);
        areas.add(area4);
    }
}
