package Game;

import java.util.List;

import MailBox.Mailbox;

public class Cell {
    private boolean state;
    private int lifeAround;
    private Mailbox mailbox;
    private List<Cell> neighborhood;

    public Cell(int numRow, boolean state, List<Cell> neighborhood) {
        this.state = state;
        this.lifeAround = 0;
        this.neighborhood = neighborhood;
    }

    public boolean getState() {
        return this.getState();
    }

    public void addNeighbor(Cell cell) {

    }

    public void updateLifeAround(boolean update){

    }

    private void calculateStatus() {

    }

    public void runUpdate() {

    }

    private void initMailBox() {

    }
}