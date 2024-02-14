package MailBox;

public class Sender extends Thread {
    Mailbox[] addresses;
    boolean newStatus;

    public Sender(Mailbox[] addresses, boolean newStatus) {
        this.addresses = addresses;
        this.newStatus = newStatus;
    }

    /*
     * Envia mensajes a todos los buzones de los vecinos
     */
    private void sendMessages() throws InterruptedException {
        for (Mailbox mailbox : addresses) {
            mailbox.addMessage(newStatus);
        }
    }

    public void run() {
        try {
            this.sendMessages();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
