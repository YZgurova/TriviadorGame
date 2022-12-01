package Triviador;
import com.google.gson.Gson;

public class Start {
    public static void main(String[] args) throws InterruptedException {
        MainLogic.inputUsernames();

        Thread thread = new Thread(() -> {
            do {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
                MainLogic.currentGameState = new Gson().fromJson(MainLogic.db.getJSONFromDB(), Game.class);
                MainLogic.db.addUpdatedData(MainLogic.currentGameState);
            } while (!MainLogic.currentGameState.getState().equals(StateEnum.GameEnd));
        });
        thread.start();

        MainLogic.mainLogicOfGame();

        thread.join();
        Thread.sleep(100000);
    }
}

