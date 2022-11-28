package Triviador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainGameFrame extends JFrame implements ActionListener{
    private int width=1000;
    private int height=600;
    private Container container;
    private String username;
    private JLabel area1;
    private JLabel area2;
    private JLabel area3;
    private JLabel area4;
    private JLabel area5;
    private JLabel area6;
    private JLabel area7;
    private JLabel area8;

    private JButton area1Button;
    private JButton area2Button;
    private JButton area3Button;
    private JButton area4Button;
    private JButton area5Button;
    private JButton area6Button;
    private JButton area7Button;
    private JButton area8Button;

    private Area attackedArea;
    private QuestionFrame qf;

    public static List<Area> collectionFromAreas; //ok li e

    public MainGameFrame(String username) {
        collectionFromAreas=new ArrayList<>();
        generateCollectionFromAreas();
        this.username=username;
        this.setTitle("TriviadorGame");
        this.setBounds(50, 50, width, height);
        this.container=this.getContentPane();
        this.container.setBackground(Color.decode("#303330"));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        init();
        this.setVisible(true);
    }

    private void init() {
        container.setLayout(null);
        Font titleFont = new Font(Font.SANS_SERIF, Font.ITALIC, 20);

        JLabel hello = new JLabel();
        hello.setText("Hi "+username+", welcome to Triviador!");
        hello.setForeground(Color.white);
        hello.setFont(titleFont);
        hello.setBounds(200, 10, 500, 100); //trqbva da se donaglasi
        container.add(hello);

        area1Button = createButton(collectionFromAreas.get(0).getButtonFileName(),250,190,20,50 );
        area1=createAreaLabel(collectionFromAreas.get(0).getFileName(),150,85,200,250);

        area2Button=createButton(collectionFromAreas.get(1).getButtonFileName(),380,220,20,50 );
        area2=createAreaLabel(collectionFromAreas.get(1).getFileName(),285,135,200,250);

        area3Button=createButton(collectionFromAreas.get(2).getButtonFileName(),540,230,20,50 );
        area3=createAreaLabel(collectionFromAreas.get(2).getFileName(),445,115,200,250);

        area4Button=createButton(collectionFromAreas.get(3).getButtonFileName(),270,320,20,50 );
        area4=createAreaLabel(collectionFromAreas.get(3).getFileName(),140,200,300,300);

        area5Button=createButton(collectionFromAreas.get(4).getButtonFileName(),380,400,20,50 );
        area5=createAreaLabel(collectionFromAreas.get(4).getFileName(),220,285,300,300);

        area6Button=createButton(collectionFromAreas.get(5).getButtonFileName(), 510,380,20,50);
        area6=createAreaLabel(collectionFromAreas.get(5).getFileName(),365,270,300,300);

        area7Button=createButton(collectionFromAreas.get(6).getButtonFileName(), 650,340,20,50);
        area7=createAreaLabel(collectionFromAreas.get(6).getFileName(),517,215,300,300);

        area8Button=createButton(collectionFromAreas.get(7).getButtonFileName(), 710,195,20,50);
        area8=createAreaLabel(collectionFromAreas.get(7).getFileName(),565,75,300,300);

        //if(Start.currentGameState.getState().equals(Start.player.state)) {
            area1Button.addActionListener(this);
            area2Button.addActionListener(this);
            area3Button.addActionListener(this);
            area4Button.addActionListener(this);
            area5Button.addActionListener(this);
            area6Button.addActionListener(this);
            area7Button.addActionListener(this);
            area8Button.addActionListener(this);
      //  }
    }
    private void generateCollectionFromAreas() {
        for (int i = 1; i <= 8; i++) {
            boolean isBlue=false;
            boolean isKingdom=false;
            if(i==1||i==2||i==4||i==5) {
                isBlue=true;
            }
            if(i==4 || i==8) {
                isKingdom=true;
            }
            Area area=new Area(i, isBlue, isKingdom);
            collectionFromAreas.add(area);
        }
    }

    private JLabel createAreaLabel(String fileName, int x, int y, int width, int height) {
        ImageIcon icon=new ImageIcon(fileName);
        JLabel label = new JLabel(icon);
        label.setBounds(x,y,width,height);
        container.add(label);
        return label;
    }

    private JButton createButton(String fileName, int x, int y, int width, int height) {
        ImageIcon icon=new ImageIcon(fileName);
        JButton button = new JButton(icon);
        button.setBounds(x,y,width,height);
        container.add(button);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==area1Button) {
            attackedArea=collectionFromAreas.get(0);
        } else if(e.getSource()==area2Button) {
            attackedArea=collectionFromAreas.get(1);
        } else if(e.getSource()==area3Button) {
            attackedArea=collectionFromAreas.get(2);
        } else if(e.getSource()==area4Button) {
            attackedArea=collectionFromAreas.get(3);
        } else if(e.getSource()==area5Button) {
            attackedArea=collectionFromAreas.get(4);
        } else if(e.getSource()==area6Button) {
            attackedArea=collectionFromAreas.get(5);
        } else if(e.getSource()==area7Button) {
            attackedArea=collectionFromAreas.get(6);
        } else if(e.getSource()==area8Button) {
            attackedArea=collectionFromAreas.get(7);
        }
        //this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        qf = new QuestionFrame(username,attackedArea);
        dispose();
    }
}

