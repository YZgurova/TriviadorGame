package Triviador.QuestionFrameClasses;

public class Question {
    public final int ID;
    public final String text;
    public final String answer;

    public final String firstWrongAnswer;
    public final String secondWrongAnswer;
    public final String trhirdWrongAnswer;

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
