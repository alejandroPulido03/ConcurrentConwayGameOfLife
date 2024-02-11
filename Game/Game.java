package Game;

public class Game {
    private Cell[][] state;
    private int numGenerations;

    public Game(boolean[][] initialState, int numGenerations) {
        this.numGenerations = numGenerations;
        this.initGame(initialState);
    }

    private void initGame(boolean[][] initialState) {
        int matrix_size = initialState.length;
        this.state = new Cell[matrix_size][matrix_size];

    }

    public void runGeneration() {

    }
}