package MailBox;

import java.util.ArrayList;
import java.util.List;

import Game.Cell;

public class Mailbox {
    private int bufferSize;
    private int networkLength;
    private List<Boolean> buffer;
    private Cell owner;
    public Sender sender;
    public Receiver receiver;

    public Mailbox(int mailboxSize, int networkLength, Cell owner) {
        this.bufferSize = mailboxSize;
        this.networkLength = networkLength;
        this.owner = owner;
        this.buffer = new ArrayList<Boolean>();
    }

    /*
     * Crea para cada ejecucion las clases Sender y Receiver con las direcciones de
     * los vecinos
     */
    public void initMessageThreads() {
        Mailbox[] addresses = new Mailbox[this.networkLength];
        boolean status = this.owner.getStatus();

        for (int i = 0; i < this.networkLength; i++) {
            Mailbox address = owner.getNeighborhood().get(i).getMailbox();
            addresses[i] = address;
        }
        this.sender = new Sender(addresses, status);
        this.receiver = new Receiver(this);
    }

    
    public int getNetworkLength() {
        return networkLength;
    }

    public void processMessage(boolean message) {
        owner.updateLifeAround(message);
    }

    /*
     * Agrega un mensaje al buffer del mailbox (productor)
     */
    public synchronized void addMessage(boolean message) throws InterruptedException {
        while (this.buffer.size() == this.bufferSize)
            wait();
        this.buffer.add(message);
        notifyAll();
    }

    /*
     * Remueve un mensaje del buffer del mailbox (consumidor)
     */
    public synchronized void checkMailbox() throws InterruptedException {
        while (this.buffer.isEmpty())
            wait();
        boolean message = this.buffer.remove(0);
        notifyAll();
        this.processMessage(message);
    }


    public void runCommunications() {
        // Los joins esperan a que los hilos mueran antes de continuar
        // Hay que cambiarlo por la implementacion de barreras con CyclicBarrier
        try {
            this.initMessageThreads();
            this.sender.start();
            this.receiver.start();
            this.receiver.join();
            this.sender.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}