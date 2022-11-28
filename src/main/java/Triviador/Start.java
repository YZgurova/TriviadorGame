package Triviador;

import com.google.gson.Gson;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Start {
    static DB db;
    static MainGameFrame mgf;
    static Game currentGameState;

    static QuestionFrame qf;
    static Player player;
    static List<Area> firstPlayerAreas;
    static List<Area> secondPlayerAreas;
    static String username;
    public static void main(String[] args) {
        db= new DB();
        currentGameState= new Gson().fromJson(db.getJSONFromDB(), Game.class);
        if(currentGameState==null || !currentGameState.isStarted()) {
            firstPlayerAreas =new ArrayList<>();
            firstPlayerAreas.add(new Area(1,true,false));
            firstPlayerAreas.add(new Area(2,true,false));
            firstPlayerAreas.add(new Area(4,true,true));
            firstPlayerAreas.add(new Area(5,true,false));
            secondPlayerAreas =new ArrayList<>();
            firstPlayerAreas.add(new Area(3,true,false));
            firstPlayerAreas.add(new Area(6,true,false));
            firstPlayerAreas.add(new Area(7,true,false));
            firstPlayerAreas.add(new Area(8,true,true));
            currentGameState=new Game(true,State.WaitingForClient, firstPlayerAreas,secondPlayerAreas);
            db.addUpdatedData(currentGameState);
            username = JOptionPane.showInputDialog("Welcome, you are host on this game!\n Write your nickname and please wait other player!");
            player = new Player(username,PlayersEnum.Player1Turn);
        } else {
            currentGameState.setState(State.Player1Turn);
            db.addUpdatedData(currentGameState);
            currentGameState=new Game(true,State.WaitingForClient, firstPlayerAreas,secondPlayerAreas);
            username = JOptionPane.showInputDialog("Welcome, please input your nickname?");
            player = new Player(username,PlayersEnum.Player2Turn);
        }
        //if(currentGameState.getState().equals(State.Player1Turn)) {
            mgf = new MainGameFrame(username);
//            if (currentGameState.getState().equals(State.Player1Turn)) {
//                currentGameState.setState(State.Player2Turn);
//            } else {
//                currentGameState.setState(State.Player1Turn);
//            }
//            db.addUpdatedData(currentGameState);
//        }
       // } while(!currentGameState.getState().equals(State.GameEnd));

    }

    //Check for a winner in a parallel thread
    //Check if this answer was answered correctly By someone else

    static void getUpdates() {
        Thread thread=new Thread() {
            public void run() {
                do {
                    pause(1000);
                    db.addUpdatedData(currentGameState);
                    currentGameState= new Gson().fromJson(db.getJSONFromDB(), Game.class);
                }while(true);
            }
        };
        thread.start();
    }
//    static void checkForUpdates() {
//        Thread thread=new Thread() {
//            public void run() {
//                do {
//                    pause(1000);
//                    db.addUpdatedData(currentGameState);
//                    gf.setTotalTries(tries);
//                }while(true);
//            }
//        };
//        thread.start();
//    }
    //Run a parallel thread
//    static void updateTries() {
//        Thread thread=new Thread() {
//            public void run() {
//                do {
//                    pause(1000);
//                    int tries = db.getTotalTries(gf.activeQuestion.ID);
//                    gf.setTotalTries(tries);
//                }while(true);
//            }
//        };
//        thread.start();
//    }

    static void  pause(int millis) {
        try {

        } catch (Exception e) {

        }
    }
}
