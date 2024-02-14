import Game.Game;

public class Controller {
    private Game game;

    public Controller(int matrixSize, boolean[][] initialState, int numGenerations) {
        this.game = new Game(initialState, numGenerations);
    }

    /*
     * Inicia la simulación completa, se ejecuta una vez
     */
    public void startSimulation() {
        
    }

    /*
     * Obtiene el estado final de la simulación, leyendolo de game
     */
    public boolean[][] getFinalState() {
        return null;
    }

}