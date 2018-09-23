package unit.testing;

import java.time.Instant;
import java.util.ArrayList;

public class Email {

    private Instant creationDate;
    private String from;
    private ArrayList<String> to;
    private ArrayList<String> cc;
    private ArrayList<String> bcc;
    private String subject;
    private String message;


    public Instant getCreationDate() {
        return creationDate;
    }

    public String getFrom() {
        return from;
    }

    public ArrayList<String> getTo() {
        return to;
    }

    public ArrayList<String> getCc() {
        return cc;
    }

    public ArrayList<String> getBcc() {
        return bcc;
    }

    public Email(Instant creationDate, String from, ArrayList<String> to, ArrayList<String> cc, ArrayList<String> bcc, String subject, String message) {

        this.creationDate = creationDate;
        this.from = from;
        this.to = to;
        this.bcc = bcc;
        this.cc = cc;
        this.subject = subject;
        this.message = message;
    }
}