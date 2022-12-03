package triviador2;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    public static class State {
        public GameState gameState;
        public GameState playerOnTurn;
        public String player1;
        public String player2;

        public Integer currentQuestion=0;
        public String player1Answer;
        public String player2Answer;

        public List<Integer> player1Territories;
        public List<Integer> player2Territories;
        public int attackedArea;

        public State() {
            this.gameState = GameState.WAITING_FOR_PLAYERS;
            player1 = "none";
            player2 = "none";
            this.player1Territories = new ArrayList<>();
            this.player1Territories.add(0);
            this.player1Territories.add(1);
            this.player1Territories.add(2);
            this.player1Territories.add(3);

            this.player2Territories = new ArrayList<>();
            this.player2Territories.add(4);
            this.player2Territories.add(5);
            this.player2Territories.add(6);
            this.player2Territories.add(7);
        }
    }
    public static volatile State state = new State();
    public static String thisPlayerUsername;
    public static GameState thisPlayerState;
    private static final Database database = new Database();

    public static void init() {
        state = database.getLatestGameData();
        if (state != null) {
            hostOrJoinGamePrompt();
        }

        if (state == null) {
            database.addFirstData(new State());
            state = database.getLatestGameData();
        }

        atomicStateUpdate(() -> {
            if (state.player1.equals("none")) {
                state.gameState = GameState.WAITING_FOR_PLAYERS;
                thisPlayerState = GameState.PLAYER1_TURN;
                state.player1=thisPlayerUsername;
            } else {
                state.gameState = GameState.PLAYER1_TURN;
                thisPlayerState = GameState.PLAYER2_TURN;
                state.player2=thisPlayerUsername;
                state.playerOnTurn=GameState.PLAYER1_TURN;
            }
        });
    }

    private static void hostOrJoinGamePrompt() {
        while (true) {
            String answer = JOptionPane.showInputDialog("Hi, there seems to be an on-going game. Would you like to join it or host a new one? [host/join]");
            thisPlayerUsername=JOptionPane.showInputDialog("Input username");
            if (answer.equals("host")) {
                database.truncateGameTable();
                state = null;
                return;
            } else if (answer.equals("join")) {
                return;
            } else {
                System.exit(0);
            }
        }

    }

    public static synchronized void atomicStateUpdate(Runnable r) {
        r.run();
        updateInDB();
    }

    private static void updateInDB() {
        database.addUpdatedData(state);
    }

    public static void syncWithDB() {
        state = database.getLatestGameData();
    }
}
