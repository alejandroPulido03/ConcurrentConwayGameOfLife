import Game.Game;

/**
 * Controller
 * 
 * Recibe los datos, crea las instancias y
 * ejecuta la simulacion
 */

public class Controller {
    private boolean[][] initialState;
    private int numGenerations;
    private Game game;

    public Controller(int matrixSize, boolean[][] initialState, int numGenerations) {
        this.initialState = initialState;
        this.numGenerations = numGenerations;
        this.game = new Game(initialState, numGenerations);
    }

    public void startSimulation() {

    }

    public boolean[][] getFinalState() {
        return null;
    }

}