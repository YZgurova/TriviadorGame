package triviador2;

import triviador2.ui.GameUI;

import javax.swing.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Game.init();
        GameUI.init();
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(10);
        exec.scheduleAtFixedRate(() -> {
            Game.syncWithDB();
            SwingUtilities.invokeLater(GameUI::updateGUI);
        }, 1, 1, TimeUnit.SECONDS);
    }
}
