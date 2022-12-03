package triviador2.ui;

import triviador2.Game;
import triviador2.GameState;
import triviador2.ui.fonts.Fonts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WaitingForPlayersPanel {
    public static JFrame frame;

//    public WaitingPanel() {
//        MainLogic.mainFrame.repaint();
//        MainLogic.mainFrame.getContentPane().removeAll();
//        MainLogic.mainFrame.setContentPane(init());
//        //MainLogic.mainFrame.getContentPane().add(init());
//    }

    public static void init() {
        frame = new JFrame();
        JPanel panel = (JPanel)frame.getContentPane();
        panel.setBackground(Color.decode("#303330"));
        JLabel text = new JLabel("Waiting for other player...");
        text.setFont(Fonts.waitingFont);
        text.setBounds(150,200, 200,200);
        text.setForeground(Color.WHITE);
        panel.add(text);

        frame.setContentPane(panel);
        frame.setBounds(50, 50, GameUI.width, GameUI.height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        updateGUI();
    }

    public static void updateGUI() {
        if (Game.state.gameState == GameState.WAITING_FOR_PLAYERS) {
            if (!frame.isVisible()) {
                frame.setVisible(true);
            }
        } else {
            frame.setVisible(false);
        }
    }
}
