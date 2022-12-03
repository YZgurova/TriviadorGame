package triviador2.ui;

import triviador2.Game;
import triviador2.GameState;
import triviador2.ui.fonts.Fonts;

import javax.swing.*;
import java.awt.*;

public class WinnerPanel {
    public static JFrame frame;
    private static JLabel text;
    public static void init() {
        frame = new JFrame();
        JPanel panel = (JPanel)frame.getContentPane();
        panel.setBackground(Color.decode("#303330"));

        text = new JLabel();
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
        if (Game.state.gameState == GameState.GAME_END) {
            if (!frame.isVisible()) {
                if(Game.thisPlayerState.equals(GameState.PLAYER1_TURN)) {
                    if(Game.state.player1Territories.size()==0) {
                        text.setText("Sorry, you lose thise game :(");
                    } else {
                        // ImageIcon image = new ImageIcon("crown.png");
                        text.setText("Congratulation, you are winner");
                    }
                }else {
                    if(Game.state.player2Territories.size()==0) {
                        text.setText("Sorry, you lose thise game :(");
                    } else {
                        // ImageIcon image = new ImageIcon("crown.png");
                        text.setText("Congratulation, you are winner");
                    }
                }
                frame.setVisible(true);
            }
        } else {
            frame.setVisible(false);
        }
    }
}
