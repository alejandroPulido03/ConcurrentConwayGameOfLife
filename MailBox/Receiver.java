package MailBox;

public class Receiver extends Thread {
    Mailbox mailbox;

    public Receiver(Mailbox mailbox) {
        this.mailbox = mailbox;
    }

    private void checkMailbox() throws InterruptedException {
        for (int i = 0; i < mailbox.getNetworkLength(); i++) {
            this.mailbox.checkMailbox();
        }
    }

    public void run() {
        try {
            this.checkMailbox();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
