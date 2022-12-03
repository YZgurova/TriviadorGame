package triviador2.ui;
import triviador2.Area;
import triviador2.Game;
import triviador2.GameState;
import triviador2.Questions;
import triviador2.ui.fonts.Fonts;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GameMapPanel {
    private static List<JLabel> areaLabels = new ArrayList<>();

    private static List<JButton> areaButtons = new ArrayList<>();
    private static JLabel hello;

    public static JFrame frame;
    public static final List<Area> areaCollection = new ArrayList<>();

    public static void init2() {
        frame = new JFrame();
        JPanel panel = (JPanel)frame.getContentPane();
        panel.setBackground(Color.decode("#303330"));

        panel.setLayout(null);

        hello = new JLabel();
       // hello.setText(String.valueOf(Game.state.playerOnTurn));
        hello.setText(String.format("Hi, %s whelcome to Triviador", Game.thisPlayerUsername));
        hello.setForeground(Color.white);
        hello.setFont(Fonts.titleFont);
        hello.setBounds(200, 10, 500, 100);
        panel.add(hello);

        generateAreaCollection();
        areaButtons.add(createButton(panel, areaCollection.get(0).buttonFileName, 250, 190, 20, 50));
        areaLabels.add(createAreaLabel(panel, areaCollection.get(0).fileName, 150, 85, 200, 250));

        areaButtons.add(createButton(panel, areaCollection.get(1).buttonFileName, 380, 220, 20, 50));
        areaLabels.add(createAreaLabel(panel, areaCollection.get(1).fileName, 285, 135, 200, 250));

        areaButtons.add(createButton(panel, areaCollection.get(2).buttonFileName, 270, 320, 20, 50));
        areaLabels.add(createAreaLabel(panel, areaCollection.get(2).fileName, 140, 200, 300, 300));

        areaButtons.add(createButton(panel, areaCollection.get(3).buttonFileName, 380, 400, 20, 50));
        areaLabels.add(createAreaLabel(panel, areaCollection.get(3).fileName, 220, 285, 300, 300));

        areaButtons.add(createButton(panel, areaCollection.get(4).buttonFileName, 540, 230, 20, 50));
        areaLabels.add(createAreaLabel(panel, areaCollection.get(4).fileName, 445, 115, 200, 250));

        areaButtons.add(createButton(panel, areaCollection.get(5).buttonFileName, 510, 380, 20, 50));
        areaLabels.add(createAreaLabel(panel, areaCollection.get(5).fileName, 365, 270, 300, 300));

        areaButtons.add(createButton(panel, areaCollection.get(6).buttonFileName, 650, 340, 20, 50));
        areaLabels.add(createAreaLabel(panel, areaCollection.get(6).fileName, 517, 215, 300, 300));

        areaButtons.add(createButton(panel, areaCollection.get(7).buttonFileName, 710, 195, 20, 50));
        areaLabels.add(createAreaLabel(panel, areaCollection.get(7).fileName, 565, 75, 300, 300));

        frame.setContentPane(panel);
        frame.setBounds(50, 50, GameUI.width, GameUI.height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if(Game.state.player1Territories.size()==0 || Game.state.player2Territories.size()==0) {
            Game.atomicStateUpdate(() -> Game.state.gameState=GameState.GAME_END);
        }
        updateGUI();
    }

    public static void updateGUI() {
        if (Game.state.gameState == GameState.PLAYER1_TURN || Game.state.gameState == GameState.PLAYER2_TURN) {
            if (!frame.isVisible()) {
                if(Game.state.gameState.equals(Game.thisPlayerState)) {
                    Game.atomicStateUpdate(() -> Game.state.currentQuestion = Questions.generateRandomQuestion());
                    ClickAction();
                }
                frame.setVisible(true);
            }

            for (int i = 0; i < 8; i++) {
                if (Game.state.player1Territories.contains(i)) {
                    areaLabels.get(i).setIcon(new ImageIcon(String.format("area%dblue.png", i+1)));
                    areaButtons.get(i).setIcon(new ImageIcon("blueArcher.png"));
                } else {
                    areaLabels.get(i).setIcon(new ImageIcon(String.format("area%dred.png", i+1)));
                    areaButtons.get(i).setIcon(new ImageIcon("redArcher.png"));
                }
            }
        } else {
            frame.setVisible(false);
        }
    }

    private static void ClickAction() {
        ClickAction click = new ClickAction();
        areaButtons.get(0).addActionListener(click);
        areaButtons.get(1).addActionListener(click);
        areaButtons.get(2).addActionListener(click);
        areaButtons.get(3).addActionListener(click);
        areaButtons.get(4).addActionListener(click);
        areaButtons.get(5).addActionListener(click);
        areaButtons.get(6).addActionListener(click);
        areaButtons.get(7).addActionListener(click);
    }

    private static class ClickAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int attckedArea = 0;
            if (e.getSource() == areaButtons.get(1)) {
                attckedArea=1;
            } else if (e.getSource() == areaButtons.get(2)) {
                attckedArea=2;
            } else if (e.getSource() == areaButtons.get(3)) {
                attckedArea=3;
            } else if (e.getSource() == areaButtons.get(4)) {
                attckedArea=4;
            } else if (e.getSource() == areaButtons.get(5)) {
                attckedArea=5;
            } else if (e.getSource() == areaButtons.get(6)) {
                attckedArea=6;
            } else if (e.getSource() == areaButtons.get(7)) {
                attckedArea=7;
            };
            Questions.generateRandomQuestion();
            GameState currentState = Game.state.gameState;
            Game.atomicStateUpdate(() -> Game.state.gameState = currentState.getNextState(currentState));
            int finalAttckedArea = attckedArea;
            Game.atomicStateUpdate(() -> Game.state.attackedArea= finalAttckedArea);
        }
    }

    private static JLabel createAreaLabel(JPanel panel, String fileName, int x, int y, int width, int height) {
        ImageIcon icon = new ImageIcon(fileName);
        JLabel label = new JLabel(icon);
        label.setBounds(x, y, width, height);
        panel.add(label);
        return label;
    }

    private static JButton createButton(JPanel panel, String fileName, int x, int y, int width, int height) {
        ImageIcon icon = new ImageIcon(fileName);
        JButton button = new JButton(icon);
        button.setBounds(x, y, width, height);
        panel.add(button);
        return button;
    }

    private static void generateAreaCollection() {
        for (int i = 1; i <= 8; i++) {
            boolean isBlue=true;
            if(Game.state.player2Territories.contains(Integer.valueOf(i))) {
                isBlue=false;
            }
            areaCollection.add(new Area(i, isBlue, false));
        }
    }
}
