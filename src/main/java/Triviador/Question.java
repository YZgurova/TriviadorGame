package Triviador;

public class Question {
    int ID;
    String text;
    String answer;

    String firstWrongAnswer;
    String secondWrongAnswer;
    String trhirdWrongAnswer;

    public Question(int ID, String text, String answer,String firstWrongAnswer,String secondWrongAnswer, String trhirdWrongAnswer) {
        super();
        this.ID = ID;
        this.text = text;
        this.answer = answer;
        this.firstWrongAnswer=firstWrongAnswer;
        this.secondWrongAnswer=secondWrongAnswer;
        this.trhirdWrongAnswer=trhirdWrongAnswer;
    }
}
