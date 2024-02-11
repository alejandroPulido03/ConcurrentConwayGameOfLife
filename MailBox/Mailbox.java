package MailBox;

import java.util.List;

import javax.sound.midi.Receiver;

import Game.Cell;

public class Mailbox {
    private int bufferSize;
    private int mailboxSize;
    private int networkLength;
    private int numReceived;
    private int numSent;
    private List<Boolean> buffer;
    private Cell owner;
    private Sender sender;
    private Receiver receiver;

    public Mailbox(int mailboxSize, int networkLength, Cell owner) {

    }

    public void processMessage(boolean message) {
        
    }

    public void runCommunications() {

    }

}