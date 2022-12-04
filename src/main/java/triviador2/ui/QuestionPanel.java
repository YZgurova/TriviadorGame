package triviador2.ui;

import triviador2.Game;
import triviador2.GameState;
import triviador2.Questions;
import triviador2.ui.fonts.Fonts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class QuestionPanel {
    private static List<JButton> answerButtons = new ArrayList<>();
    private static JLabel questionLabel;
    private static JFrame frame;

    public static void init() {
        frame=new JFrame();
        if(Game.thisPlayerState.equals(GameState.PLAYER1_TURN)) {
            frame.setName(Game.state.player1);
        } else {
            frame.setName(Game.state.player2);
        }

        JPanel panel = (JPanel) frame.getContentPane();
        panel.setBackground(Color.decode("#303330"));

        panel.setLayout(null);

        questionLabel = createLabel(panel, Questions.questions.get(Game.state.currentQuestion), Fonts.titleFont, Color.white, 200, 80, 900, 200);

        answerButtons.add(setButtonWithAnswer(panel, 200,300,180,90,Questions.answers.get(Game.state.currentQuestion).get(1)));
        answerButtons.add(setButtonWithAnswer(panel,200,400,180,90,Questions.answers.get(Game.state.currentQuestion).get(2)));
        answerButtons.add(setButtonWithAnswer(panel,420,300,180,90,Questions.answers.get(Game.state.currentQuestion).get(3)));
        answerButtons.add(setButtonWithAnswer(panel,420,400,180,90,Questions.answers.get(Game.state.currentQuestion).get(4)));
        answerButtons.add(setButtonWithAnswer(panel, 730, 400, 180, 90, "Back to game"+Game.thisPlayerUsername));

        frame.setContentPane(panel);
        frame.setBounds(50, 50, GameUI.width, GameUI.height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ClickSomeButton();
        updateGUI();
    }

    private static void ClickSomeButton() {
        ClickAction click = new ClickAction();
        answerButtons.get(0).addActionListener(click);
        answerButtons.get(1).addActionListener(click);
        answerButtons.get(2).addActionListener(click);
        answerButtons.get(3).addActionListener(click);
        System.out.println(Game.state.playerOnTurn);
        answerButtons.get(4).addActionListener(click);

    }

    private static class ClickAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String givenAnswer=null;
            if(e.getSource()==answerButtons.get(0)) {
                givenAnswer=answerButtons.get(0).getText();
            } else if(e.getSource()==answerButtons.get(1)) {
                givenAnswer=answerButtons.get(1).getText();
            } else if(e.getSource()==answerButtons.get(2)) {
                givenAnswer=answerButtons.get(2).getText();
            } else if(e.getSource()==answerButtons.get(3)) {
                givenAnswer=answerButtons.get(3).getText();
            } else if(Game.state.player1Answer!=null && Game.state.player2Answer!=null && Game.thisPlayerState.equals(Game.state.playerOnTurn)){
                if(Game.thisPlayerState.equals(GameState.PLAYER1_TURN) && Game.state.player1Answer.equals(Questions.answers.get(Game.state.currentQuestion).get(0)) && !Game.state.player2Answer.equals(Questions.answers.get(Game.state.currentQuestion).get(0))) {
                    Game.atomicStateUpdate(() -> Game.state.player1Territories.add(Game.state.attackedArea));
                    Game.atomicStateUpdate(() -> Game.state.player2Territories.remove(Integer.valueOf(Game.state.attackedArea)));
                    GameMapPanel.resultFromAttackLabel.setText(Game.state.player1 + "captures the attacked territory");
                } else if(Game.thisPlayerState.equals(GameState.PLAYER2_TURN) && Game.state.player2Answer.equals(Questions.answers.get(Game.state.currentQuestion).get(0)) && !Game.state.player1Answer.equals(Questions.answers.get(Game.state.currentQuestion).get(0))) {
                    Game.atomicStateUpdate(() -> Game.state.player2Territories.add(Game.state.attackedArea));
                    Game.atomicStateUpdate(() -> Game.state.player1Territories.remove(Integer.valueOf(Game.state.attackedArea)));
                    GameMapPanel.resultFromAttackLabel.setText(Game.state.player2 + "captures the attacked territory");
                }
                if(Game.state.player1Territories.size()==0 || Game.state.player2Territories.size()==0) {
                    Game.atomicStateUpdate(() -> Game.state.gameState=GameState.GAME_END);
                } else {
                    Game.state.player1Answer = null;
                    Game.state.player2Answer = null;
                    GameState currentState = Game.state.gameState;
                    GameState currNextState = GameState.getNextState(currentState);

                    Game.atomicStateUpdate(() -> Game.state.gameState = currNextState);
                    Game.atomicStateUpdate(() -> Game.state.playerOnTurn=currNextState);
                    Game.atomicStateUpdate(() -> Game.state.leftCountQuestions-=1);
                }
            }
            if(e.getSource()!=answerButtons.get(4)) {
                String finalGivenAnswer = givenAnswer;
                if(Game.thisPlayerState.equals(GameState.PLAYER1_TURN)) {
                    Game.atomicStateUpdate(() -> Game.state.player1Answer= finalGivenAnswer);
                } else {
                    Game.atomicStateUpdate(() -> Game.state.player2Answer= finalGivenAnswer);
                }
            }
        }
    }

    private static JLabel createLabel(JPanel panel, String text, Font font, Color color, int x, int y, int width, int height) {
        JLabel label =new JLabel(text);
        label.setForeground(color);
        label.setFont(font);
        label.setBounds(x, y, width, height);
        panel.add(label);
        return  label;
    }

    public static JButton setButtonWithAnswer(JPanel panel, int x, int y, int width, int height, String text) {
        JButton button = new JButton(text);
        button.setBounds(x,y,width,height);
        panel.add(button);
        return button;
    }

    public static void updateGUI() {
        if(Game.state.gameState == GameState.QUESTION1 || Game.state.gameState == GameState.QUESTION2) {
            if(!frame.isVisible()) {
                questionLabel.setText(Questions.questions.get(Game.state.currentQuestion));
                answerButtons.get(0).setText(Questions.answers.get(Game.state.currentQuestion).get(1));
                answerButtons.get(1).setText(Questions.answers.get(Game.state.currentQuestion).get(2));
                answerButtons.get(2).setText(Questions.answers.get(Game.state.currentQuestion).get(3));
                answerButtons.get(3).setText(Questions.answers.get(Game.state.currentQuestion).get(4));
                frame.setVisible(true);
            }
        } else {
            frame.setVisible(false);
        }
    }
}
