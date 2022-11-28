package Triviador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class QuestionFrame extends JFrame implements ActionListener {
    private int width = 700;
    private int height=500;
    private Container container;
    private String username;

    private JLabel questionLabel;
    private JLabel statusLabel;
    private JLabel tryLabel;
    private JButton answer1;
    private JButton answer2;
    private JButton answer3;
    private JButton answer4;
    private Area attackedArea;
    private JButton pressedButton;

    private int totalTries=0;
    private int playerTries=0;
    private Timer swingTimer;
    private JLabel timerLabel;
    private int startTimer=30;
    private static String defaultStatusLabelText = "Try to answer the question quickly";
    private Question activeQuestion;

    public QuestionFrame(String username, Area attackedArea) {
        this.username=username;
        this.attackedArea=attackedArea;
        this.setTitle("Question");
        this.setBounds(200,100,width,height);
        this.container=this.getContentPane();
        this.container.setBackground(Color.decode("#303330"));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        setUpQuestion();
        answer1=setButtonWithAnswer(100,200,150,50,activeQuestion.answer);
        answer1.addActionListener(this);
        answer2=setButtonWithAnswer(100,300,150,50,activeQuestion.firstWrongAnswer);
        answer2.addActionListener(this);
//        answer2.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                pressedButton=answer2;
//            }
//        });

        answer3=setButtonWithAnswer(300,200,150,50,activeQuestion.secondWrongAnswer);
        answer3.addActionListener(this);
        answer4=setButtonWithAnswer(300,300,150,50,activeQuestion.trhirdWrongAnswer);
        answer4.addActionListener(this);
        //ТРЯБВА ДА СЕ ИЗМИСЛИ КАК ЩЕ СЕ ОПРЕДЕЛЯТ НА РАНДЪМ ПРИНЦИП!!!!!!!!!
        // updateQuestion();
        this.setVisible(true);
        //updateQuestion();
    }

    public void onClick(ActionEvent x) {
        System.out.println(x);
    }


    private JButton setButtonWithAnswer(int x, int y, int width, int height, String text) {
        JButton button = new JButton(text);
        button.setBounds(x,y,width,height);
        container.add(button);
        return button;
    }
    private void setUpQuestion() { //zarejda sledvastiq vapros
        this.activeQuestion=Start.db.getActiveQuestion();
        if(this.activeQuestion==null) {
            System.exit(0); //tuk trqbva da se kaje game over ili nesto takova
        }


        playerTries=0;
        totalTries=0;
        this.questionLabel.setText(activeQuestion.text);
        this.playerTries=0;
        this.repaint(); //kakvo praveshe tova
    }

    private void init() {
        this.container.setLayout(null);
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 20);
        Font statusFont = new Font(Font.SANS_SERIF, Font.ITALIC, 14);

        questionLabel= createLabel("", titleFont, Color.white, 60,30,500,30);
        statusLabel= createLabel(defaultStatusLabelText, statusFont, Color.white, 60,100,500,30);
        if(attackedArea.isKingdom) {
            tryLabel= createLabel("", titleFont, Color.white, 520,60, 150,60);
            this.tryLabel.setBorder(BorderFactory.createLineBorder(Color.white,2));
           // Start.updateTries();
            this.updateTries();
        }

        timerLabel=new JLabel("Starting timer");
        timerLabel.setBounds(1000,300, 100,100);
        timerLabel.setForeground(Color.white);
        container.add(timerLabel);
        swingTimer=new Timer(1000,this::timerOneMethod);
        swingTimer.start();
    }
    private void timerOneMethod(ActionEvent e)
    {
        System.out.println("hello world");
        startTimer--;
        if(startTimer>=1) {
            timerLabel.setText("Timer : "+startTimer);
        } else {
            timerLabel.setText("Timeout");
        }
        swingTimer.stop();
    }

    private JLabel createLabel(String text, Font font, Color color, int x, int y, int width, int height) {
        JLabel label =new JLabel(text);
        label.setForeground(color); //foreground is for the object colour, and background i for the background colour
        label.setFont(font);
        label.setBounds(x, y, width, height);
        this.container.add(label);
        return  label;
    }

    public void setTotalTries(int newTotalTries) {
        this.totalTries=newTotalTries;
        updateTries();
    }
    private void updateTries() {
        String tryHtml="<html><div style='padding: 7px'>Total tries: "+totalTries+"<br/>Your tries: "+playerTries+"</div></html>";
        //izpolzvam tuk html tag, za
        // da moga da formatiram po-dobre leibyla, toj e ot dva reda, a ne iskah da pravq dwa leibyla, moveh da go napravq kato
        // string na 2 reda, no togava nqmashe kak da formatiram dobre bordyra i zatova naj-dobriq variant be[e html
        this.tryLabel.setText(tryHtml);
    }

//    void updateQuestion() {
//        QuestionFrame outer = this;
//        Thread thread = new Thread(){
//            public void run() {
//                do {
//                    Start.pause(300);
//                    String winner = Start.db.getQuestionWinner(Start.gf.activeQuestion.ID);
//                    if(winner!=null) {
//                        Start.qf.setUpQuestion();
//                    }
//                } while(true);
//            }
//        };
//        thread.start();
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        pressedButton=new JButton();
        if(e.getSource()==answer1) {
            pressedButton=answer1;
            statusLabel.setText("Correct answer!");
        } else if(e.getSource()==answer2) {
            pressedButton=answer2;
            statusLabel.setText("Uncorrect answer!");
        } else if(e.getSource()==answer3) {
            pressedButton=answer3;
            statusLabel.setText("Uncorrect answer!");
        } else {
            pressedButton=answer4;
            statusLabel.setText("Uncorrect answer!");
        }
        Start.db.addAnswerTry(pressedButton.getText(),activeQuestion.ID, pressedButton.getText()==activeQuestion.answer, username);
        dispose();
        Start.mgf=new MainGameFrame(Start.username);
    }
}
