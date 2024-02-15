package Game;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Game {
    private Cell[][] state;
    private CyclicBarrier barrier;
    private int numGenerations;
    private boolean[][] actualState;

    public Game(boolean[][] initialState, int numGenerations) {
        this.numGenerations = numGenerations;
        this.barrier = new CyclicBarrier((initialState.length * initialState.length) + 1);
        this.actualState = initialState;
        initGame(initialState);
    }

    private void initGame(boolean[][] actualState) {
        initCells(actualState);
        initNeighborhood();
        initMailBoxes();
    }

    private void initCells(boolean[][] initialState) {
        int matrix_size = initialState.length;
        this.state = new Cell[matrix_size][matrix_size];
        for (int i = 0; i < matrix_size; i++) {
            for (int j = 0; j < matrix_size; j++) {
                this.state[i][j] = new Cell(i, j, initialState[i][j], getBarrier());
            }
        }
    }

    public CyclicBarrier getBarrier() {
        return barrier;
    }

    private void initNeighborhood() {
        int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
        int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };
        for (int i = 0; i < this.state.length; i++) {
            for (int j = 0; j < this.state.length; j++) {
                for (int k = 0; k < 8; k++) {
                    int x = i + dx[k];
                    int y = j + dy[k];
                    if (x >= 0 && x < this.state.length && y >= 0 && y < this.state.length) {
                        this.state[i][j].addNeighbor(this.state[x][y]);
                    }
                }
            }
        }

    }

    private void initMailBoxes() {
        for (int i = 0; i < this.state.length; i++)
            for (int j = 0; j < this.state.length; j++)
                this.state[i][j].initMailBox();
    }

    private void runGeneration() {
        for (int i = 0; i < this.state.length; i++)
            for (int j = 0; j < this.state.length; j++)
                this.state[i][j].start();

        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < this.state.length; i++)
            for (int j = 0; j < this.state.length; j++)
                this.actualState[i][j] = this.state[i][j].getStatus();

        initGame(actualState);

    }

    public void runGame() {
        for (int k = 0; k < this.numGenerations; k++) {
            this.runGeneration();
            // Print state
            for (int i = 0; i < actualState.length; i++) {
                for (int j = 0; j < actualState.length; j++) {
                    System.out.print((this.state[i][j].getStatus() ? "T" : "F") + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        boolean[][] initialState = {
            {true, false, true},
            {false, true, false},
            {true, false, true}};

        Game game = new Game(initialState, 18);
        game.runGame();
    }
}