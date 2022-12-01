package Triviador;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private boolean isStarted;
    private volatile StateEnum state;
    private List<Integer> player1Areas;
    private List<Integer> player2Areas;
    private Player playerOnTurn;

    private int givenAnswers=0;

    public int getGivenAnswers() {
        return givenAnswers;
    }

    public void setGivenAnswers(int givenAnswers) {
        this.givenAnswers = givenAnswers;
    }

    private Area currentAttackedArea;

    public Game(Player player) {
        isStarted=true;
        state= StateEnum.WaitingForClient;
        player1Areas=new ArrayList<>();
        player1Areas.add(1);
        player1Areas.add(2);
        player1Areas.add(3);
        player1Areas.add(4);
        player2Areas=new ArrayList<>();
        player2Areas.add(5);
        player2Areas.add(6);
        player2Areas.add(7);
        player2Areas.add(8);
        playerOnTurn=player;
        currentAttackedArea =null;
    }

    public Area getCurrentAttackedArea() {
        return currentAttackedArea;
    }

    public void setCurrentAttackedArea(Area currentAttackedArea) {
        this.currentAttackedArea = currentAttackedArea;
    }

    public Player getPlayerOnTurn() {
        return playerOnTurn;
    }

    public void setPlayerOnTurn(Player playerOnTurn) {
        this.playerOnTurn = playerOnTurn;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    public StateEnum getState() {
        return state;
    }

    public void setState(StateEnum state) {
        this.state = state;
    }

    public List<Integer> getPlayer1Areas() {
        return player1Areas;
    }

    public List<Integer> getPlayer2Areas() {
        return player2Areas;
    }

}
