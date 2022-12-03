package triviador2;

public enum GameState {
    WAITING_FOR_PLAYERS,
    PLAYER1_TURN,
    QUESTION1,
    PLAYER2_TURN,
    QUESTION2,
    GAME_END;

    public static GameState getNextState(GameState s)
    {
        return switch (s) {
            case PLAYER1_TURN -> QUESTION1;
            case QUESTION1 -> PLAYER2_TURN;
            case PLAYER2_TURN -> QUESTION2;
            case QUESTION2 -> PLAYER1_TURN;
            default -> GAME_END;
        };
    }
}
