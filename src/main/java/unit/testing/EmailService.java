package unit.testing;

import java.util.ArrayList;

public interface EmailService {

    boolean sendEmail(Email email);

    ArrayList<Email> emailList(EmailAccount account);
}