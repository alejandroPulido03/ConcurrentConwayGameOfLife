package Game;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import MailBox.Mailbox;

public class Cell extends Thread {
    private boolean status;
    private int lifeAround;
    private int numRow;
    private Mailbox mailbox;
    private List<Cell> neighborhood;
    private int[] id;
    private CyclicBarrier matrix_barrier;

    public Cell(int numRow, int numCol, boolean status, CyclicBarrier barrier) {
        this.status = status;
        this.numRow = numRow;
        this.lifeAround = 0;
        this.neighborhood = new ArrayList<Cell>();
        this.id = new int[] { numRow, numCol };
        this.matrix_barrier = barrier;
    }

    public boolean getStatus() {
        return this.status;
    }

    public List<Cell> getNeighborhood() {
        return neighborhood;
    }

    public Mailbox getMailbox() {
        return mailbox;
    }

    public void addNeighbor(Cell cell) {
        this.neighborhood.add(cell);
    }

    int updates = 0;

    public void updateLifeAround(boolean update) {
        if (update)
            this.lifeAround++;
        this.updates++;
    }

    public String getCellId() {
        return id[0] + " " + id[1];
    }

    private void calculateStatus() {
        if (this.status)
            this.status = !(this.lifeAround > 3 || this.lifeAround < 1);
        else
            this.status = this.lifeAround == 3;
    }

    public void initMailBox() {
        this.mailbox = new Mailbox(this.numRow + 1, this.neighborhood.size(), this);
    }

    public void run() {
        this.mailbox.runCommunications();
        this.calculateStatus();
        try {
            this.matrix_barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

}