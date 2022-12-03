package triviador2.ui;

import triviador2.Game;
import triviador2.GameState;

import javax.swing.*;

public class GameUI {
    public static final int width = 1000;
    public static final int height = 600;

    public static void init() {
        WaitingForPlayersPanel.init();
        GameMapPanel.init2();
        QuestionPanel.init();
        WinnerPanel.init();
    }

    public static void updateGUI() {
        WaitingForPlayersPanel.updateGUI();
        GameMapPanel.updateGUI();
        QuestionPanel.updateGUI();
        WinnerPanel.updateGUI();
    }
}
