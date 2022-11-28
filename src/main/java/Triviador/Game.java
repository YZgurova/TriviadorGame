package Triviador;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private boolean isStarted;

    private State state;
    private List<Area> player1Areas;
    private List<Area> player2Areas;

    public Game() {
        isStarted=true;
        state=State.WaitingForClient;
        player1Areas=null;
        player2Areas=null;
    }

    public Game(boolean isStarted,State state, List<Area> player1Areas, List<Area> player2Areas) {
        this.state = state;
        player1Areas=new ArrayList<>();
        this.player1Areas = player1Areas;
        player2Areas=new ArrayList<>();
        this.player2Areas = player2Areas;
        this.isStarted=isStarted;
    }
    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<Area> getPlayer1Areas() {
        return player1Areas;
    }

    public void setPlayer1Areas(List<Area> player1Areas) {
        this.player1Areas = player1Areas;
    }

    public List<Area> getPlayer2Areas() {
        return player2Areas;
    }

    public void setPlayer2Areas(List<Area> player2Areas) {
        this.player2Areas = player2Areas;
    }
}
