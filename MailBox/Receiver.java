package MailBox;
public class Receiver extends Thread{
    Mailbox mailbox;

    public Receiver(Mailbox mailbox){
        this.mailbox = mailbox;
    }

    private void checkMailbox(){

    }

    public void run(){
        checkMailbox();
    }
}
