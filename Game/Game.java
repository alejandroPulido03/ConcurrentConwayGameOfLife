package Game;

public class Game {
    private Cell[][] state;
    private int numGenerations;

    public Game(boolean[][] initialState, int numGenerations) {
        this.numGenerations = numGenerations;
        this.initCells(initialState);
        this.initNeighborhood();
        this.initMailBoxes();
    }

    private void initCells(boolean[][] initialState) {
        int matrix_size = initialState.length;
        this.state = new Cell[matrix_size][matrix_size];
        for (int i = 0; i < matrix_size; i++) {
            for (int j = 0; j < matrix_size; j++) {
                this.state[i][j] = new Cell(i, j, initialState[i][j]);
            }
        }
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

    public void runGeneration() {
        for (int i = 0; i < this.state.length; i++)
            for (int j = 0; j < this.state.length; j++)
                this.state[i][j].start();
        
        //Implementar con barrera
        for (int i = 0; i < this.state.length; i++)
            for (int j = 0; j < this.state.length; j++) {
                try {
                    this.state[i][j].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

    }

    public static void main(String[] args) {
        boolean[][] initialState = { { false, true, false }, { true, true, true }, { false, false, false } };

        Game game = new Game(initialState, 10);
        game.runGeneration();
        // Print state
        for (int i = 0; i < initialState.length; i++) {
            for (int j = 0; j < initialState.length; j++) {
                System.out.print(game.state[i][j].getStatus() + " ");
            }
            System.out.println();
        }
    }
}