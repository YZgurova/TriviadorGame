package Triviador;

import com.google.gson.Gson;

import java.sql.*;

public class DB {
        private Connection connection;
        private Statement statement;
        private PreparedStatement preparedStatement;
        private ResultSet resultSet;

    public DB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/triviador?characterEncoding=utf8", "root", "root");//Server=localhost\SQLEXPRESS;Database=master;Trusted_Connection=True;
            this.statement=connection.createStatement();
            this.createTables();
        } catch(Exception e) {
            System.out.println("Problem with DB setup!" + e);
        } //tova e v try catch blok poneje bazata moje da ni varne exception
    }

    public String getJSONFromDB() {
        try {
            String query="SELECT `data` from `tbl_game_statement` order by last_update ASC LIMIT 1";
            this.resultSet=statement.executeQuery(query);
            while(this.resultSet.next()) {
                return resultSet.getString("data");
            }
        } catch (Exception e) {
            System.out.println("Problem with getJSONFromDB "+e);
        }
        return null;
    }
    public void addUpdatedData(Game game) {
        String jo=new Gson().toJson(game);
        String query = "INSERT INTO `tbl_game_statement` (data, last_update) VALUES (?, CURRENT_TIMESTAMP)";
        try {
            preparedStatement=this.connection.prepareStatement(query);
            this.preparedStatement.setString(1, jo);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Problems with adding updated data "+e);
            System.out.println(query);
        }
    }

    public int getTotalTries (int questionID) {
        String query="SELECT COUNT(1) FROM tbl_answer_try where question_id="+questionID;
        try {
            int count = this.resultSet.getInt("count");
            return count;
        } catch (Exception e) {
            System.out.println("Problems with getTotalTries "+e);
            System.out.println(query);
        }
        return 0;
    }


    public void addAnswerTry(String answerTry, int questionID,boolean isCorrect, String playerName) {
        String query = "INSERT INTO tbl_answer_try(question_id, player, `text`, `ìs_correct`, `ìs_winner`, answer_time) \n" +
                "VALUES (?,?,?,?, ((SELECT COUNT(1) FROM tbl_answer_try tb WHERE id=? and ìs_correct=1) = 0 AND ? = 1),CURRENT_TIMESTAMP)";
        try {
            this.preparedStatement = this.connection.prepareStatement(query);
            this.preparedStatement.setInt(1, questionID);
            this.preparedStatement.setString(2, playerName);
            this.preparedStatement.setString(3, answerTry);
            this.preparedStatement.setInt(4, isCorrect ? 1: 0);
            this.preparedStatement.setInt(5, questionID);
            this.preparedStatement.setInt(6, isCorrect ? 1: 0);
            this.preparedStatement.executeUpdate();
            this.checkWinner(questionID);

        } catch(Exception e) {
            System.out.println("Problems with adding answer try "+e);
            System.out.println(query);
        }

    }

    private void checkWinner(int questionID) {
        try {
            String player=this.getQuestionWinner(questionID);
            if(player==null) {
                return;
            }
            setQuestionWinner(questionID,player);
        } catch (Exception e) {
            System.out.println("Problems with checkWinner "+e);
        }
    }

    private void setQuestionWinner(int questionID, String player) {
        try {
            String query="UPDATE tbl_question set winner_name = ?, `ìs_answered`=1 WHERE id = ?";
            preparedStatement=this.connection.prepareStatement(query);
            preparedStatement.setString(1, player);
            preparedStatement.setInt(2,questionID);
            preparedStatement.executeUpdate();
        } catch(Exception e) {
            System.out.println("Problem with setQuestionWinner "+ e);
        }
    }
    public String getQuestionWinner(int questionID) {
        try {
            String query="SELECT player from tbl_answer_try WHERE question_id="+questionID+" AND `ìs_correct`=1 ORDER BY answer_time ASC LIMIT 1";
            this.resultSet=statement.executeQuery(query);
            while(this.resultSet.next()) {
                return resultSet.getString("player");
            }
        } catch (Exception e) {
            System.out.println("Problem with getQuestionQinner "+e);
        }
        return null;
    }

    public Question getActiveQuestion() {
        String query ="SELECT * FROM tbl_question WHERE `ìs_answered`=0 ORDER BY id ASC LIMIT 1";
        try {
            resultSet=statement.executeQuery(query);
            while (resultSet.next()) { //rabotata na tozi while e da tyrsi w tablicata dokato nameri neotgoworen vypros kojto da podade
                int ID=resultSet.getInt("ID");
                String text=resultSet.getString("question");
                String answer=resultSet.getString("answer");
                String firstWrongAnswer=resultSet.getString("first_wrong_answer");
                String secondWrongAnswer=resultSet.getString("second_wrong_answer");
                String thirdWrongAnswer=resultSet.getString("third_wrong_answer");
                Question question = new Question(ID, text, answer,firstWrongAnswer,secondWrongAnswer,thirdWrongAnswer);
                return question;
            }
        } catch (Exception e) {
            System.out.println("Problems with getActiveQuestion " +e);
        }
        return  null;
    }
    private void createTables() {
        String createGameStateTable= """
                CREATE TABLE IF NOT EXISTS `tbl_game_statement` (
                `id` INT(11) NOT NULL AUTO_INCREMENT,
                `data` VARCHAR(100) NOT NULL,
                `last_update` DATETIME NOT NULL,
                PRIMARY KEY (`id`))
                COLLATE='utf8mb4_general_ci'
                ENGINE=InnoDB;""";

        String createQuestionTable= """
                CREATE TABLE IF NOT EXISTS tbl_question (
                id INT(11) NOT NULL AUTO_INCREMENT,
                question VARCHAR(500) NOT NULL DEFAULT '',
                answer VARCHAR(50) NOT NULL DEFAULT '',
                ìs_answered TINYINT(1) NOT NULL DEFAULT 0,
                winner_name VARCHAR(50) NULL DEFAULT NULL,
                PRIMARY KEY (id))
                COLLATE='utf8mb4_general_ci'
                ENGINE=InnoDB;""";

        String createAnswerTable= """
                CREATE TABLE IF NOT EXISTS `tbl_answer_try` (
                `id` INT(11) NOT NULL AUTO_INCREMENT,
                `question_id` VARCHAR(500) NOT NULL,
                `player` VARCHAR(50) NOT NULL,
                `text` VARCHAR(50) NOT NULL,
                `ìs_correct` TINYINT(1) NOT NULL DEFAULT 0,
                `ìs_winner` TINYINT(1) NOT NULL DEFAULT 0,
                `answer_time` DATETIME NOT NULL,
                PRIMARY KEY (`id`))
                COLLATE='utf8mb4_general_ci'
                ENGINE=InnoDB;""";
        this.insert(createQuestionTable);
        this.insert(createAnswerTable);
        this.insert(createGameStateTable);

    }

    private void insert(String query) {
        try {
            statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("Problem with insert query" + e);
            System.out.println(query);
        }
    }
}
