package unit.testing.utils;

import unit.testing.Email;

import java.time.Instant;
import java.util.ArrayList;

public class EmailBuilder {

    public EmailBuilder(){}

    private Instant creationDate;
    private String from;
    private ArrayList<String> to;
    private ArrayList<String> cc;
    private ArrayList<String> bcc;
    private String subject;
    private String message;

    public EmailBuilder setCreationDateBuilder(Instant creationDate) {

        this.creationDate = creationDate;
        return this;
    }

    public EmailBuilder setFromBuilder(String from) {

        this.from = from;
        return this;
    }

    public EmailBuilder setToBuilder(ArrayList<String> to) {

        this.to = to;
        return this;
    }

    public EmailBuilder setCcBuilder(ArrayList<String> cc) {

        this.cc = cc;
        return this;
    }

    public EmailBuilder setBccBuilder(ArrayList<String> bcc) {

        this.bcc = bcc;
        return this;
    }

    public EmailBuilder setSubjectBuilder(String subject) {

        this.subject = subject;
        return this;
    }

    public EmailBuilder setMessageBuilder(String message) {

        this.message = message;
        return this;
    }

    public Email build(){

        return new Email(creationDate, from, to, cc, bcc, subject, message);

    }

}