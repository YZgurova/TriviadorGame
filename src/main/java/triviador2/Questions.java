package triviador2;

import java.util.*;

public class Questions {
    public static final List<String> questions = new ArrayList<>(Arrays.asList(
            "<html>You're 4th place right now in a race. <br>What place will you be in when <br>you pass the person in 3rd place?</html>",
            "<html>How many months have 28 days?</html>",
            "<html>How many 0.5cm slices can you cut <br>from a bread that is 25cm long?</html>",
            "<html>At a length of 4,132 miles, <br>what is the longest river in the world?</html>",
            "<html>What is the tallest mountain in the world?</html>",
            "<html>Which of the following was <br>Australia's first national park?</html>",
            "<html>When did Chicago first start dying <br>their river green for St Paddy’s Day?</html>",
            "<html>When were fireworks first officially <br>used in a celebration for 4th July?</html>",
            "<html>Who directed Fahrenheit 911?</html>",
            "<html>Which nation has won the most World Cups?</html>",
            "<html>Who painted The Last Supper over <br>a three-year period <br>between 1495 to 1498?</html>"
    ));

    public static final HashMap<Integer, List<String>> answers = new HashMap<>() {{
        put(0, Arrays.asList("3rd", "1st", "2nd", "3rd", "None of the above"));
        put(1,Arrays.asList("Depends if there's a leap year or not","2","Depends if there's a leap year or not","1","All of them"));
        put(2,Arrays.asList("50","50","25","39","None of the above"));
        put(3,Arrays.asList("Nile","Amazon","Thames","Nile","Mekong"));
        put(4,Arrays.asList("Mount Everest","K2","Mount Kilimanjaro","Mount Rainier","Mount Everest"));
        put(5,Arrays.asList("Royal National Park","Lamington National Park","Springbrook National Park","The Grampians National Park","Royal National Park"));
        put(6,Arrays.asList("1962","1962","1972","1982","1992"));
        put(7,Arrays.asList("1777","1770","1777","1901","1972"));
        put(8,Arrays.asList("Michael Moore","Michael Moore","Louis Theroux","Martin Scorsese","Paul Greengrass"));
        put(9,Arrays.asList("Brazil","Germany","Italy","Colombia","Brazil"));
        put(10,Arrays.asList("Leonardo da Vinci","Michaelangelo","Vincent Van Gogh","Leonardo da Vinci","Botticelli"));
    }};

    public static Integer generateRandomQuestion() {
        int questionNum = (int)(Math.random()*10);
        while(Game.state.listOfGivenQuestions.contains(questionNum)) {
            questionNum=(int)(Math.random()*10);
        }
        int finalQuestionNum = questionNum;
        Game.atomicStateUpdate(() -> Game.state.listOfGivenQuestions.add(finalQuestionNum));
        return questionNum;

    }
}
