package triviador2;

import java.util.*;

public class Questions {
    public static final List<String> questions = new ArrayList<>(Arrays.asList(
            "You're 4th place right now in a race.\n What place will you be in when\n you pass the person in 3rd place?",
            "How many months have 28 days?",
            "How many 0.5cm slices \ncan you cut from \na bread that is 25cm long?",
            "At a length of 4,132 miles, " + "\nwhat is the longest river in the world?",
            "What is the tallest mountain in the world?",
            "Which of the following was \nAustralia's first national park?",
            "When did Chicago first start dying their river green for St Paddy’s Day?",
            "When were fireworks first officially used in a celebration for 4th July?",
            "Who directed Fahrenheit 911?'",
            "Which nation has won the most World Cups?",
            "Who painted The Last Supper over a three-year period between 1495 to 1498?"
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

    private static List<Integer> givenQuestions=new ArrayList<>();

    public static Integer generateRandomQuestion() {
        int questionNum = (int)(Math.random()*10);
        while(givenQuestions.contains(questionNum)) {
            questionNum=(int)(Math.random()*10);
        }
        givenQuestions.add(questionNum);
        System.out.println(questionNum);
        return questionNum;

    }
}
