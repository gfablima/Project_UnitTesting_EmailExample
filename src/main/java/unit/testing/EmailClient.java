package unit.testing;

import java.time.LocalDate;
import java.util.ArrayList;

public class EmailClient {

    private EmailService emailService;
    private ArrayList<EmailAccount> accounts = new ArrayList<EmailAccount>();

    public void setEmailService(EmailService emailService) {

        this.emailService = emailService;
    }

    public boolean isValidAddress(String emailAddress) {

        String emailCharacters = "^([\\._A-Za-z0-9-]+)(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z0-9]{2,})$";

        if (emailAddress.matches(emailCharacters)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isValidEmail(Email email) {

        if (email.getCreationDate() == null) {
            return false;
        } else {

            if (isValidAddress(email.getFrom())) { //Valid sender email account

                if (email.getTo() != null && email.getTo().size() > 0) {

                    ArrayList<String> listOfTo = email.getTo();

                    Boolean atLeastOneTOValid = false;
                    for (String eachTo : listOfTo) {
                        if (isValidAddress(eachTo) == true) { //If at least 1 TO is valid, check Bcc and Cc
                            atLeastOneTOValid = true;
                        }
                    }

                    if (atLeastOneTOValid == true) {
                        ArrayList<String> listOfCc = email.getCc();

                        if (listOfCc != null && listOfCc.size() > 0) {
                            for (String eachCc : listOfCc) { //If any CC is invalid, the email becomes invalid
                                if (isValidAddress(eachCc) == false) {
                                    return false;
                                }
                            }
                            //If ALL Cc emails are valid, check Bcc emails
                            ArrayList<String> listOfBcc = email.getBcc();
                            if (listOfBcc != null && listOfBcc.size() > 0) {
                                for (String eachBcc : listOfBcc) { //If any Bcc email is invalid, the email becomes invalid
                                    if (isValidAddress(eachBcc) == false) {
                                        return false;
                                    }
                                }
                                return true;//All Cc and Bcc emails are valid
                            }
                            return true; //All Cc emails valid and 0 Bcc emails
                        } else /*Cc list is NULL or empty, check Bcc list */ {

                            ArrayList<String> listOfBcc = email.getBcc();

                            if ((listOfBcc != null) && (listOfBcc.size() > 0)) {
                                for (String eachBcc : listOfBcc) { //If any Bcc email is invalid, the email becomes invalid
                                    if (isValidAddress(eachBcc) == false) {
                                        return false;
                                    }
                                }
                                return true; /*All Bcc emails are valid */
                            } else {
                                return true; /*No Bcc and no Cc*/
                            }
                        }
                    } else { //All TO emails are invalid
                        return false;
                    }
                } else {
                    return false;//list of emailAccount TO = 0 or null
                }
            } else {
                return false;/*Invalid From*/
            }
        }
    }

    public ArrayList<Email> emailList(EmailAccount emailAccount) {

        if ((emailAccount.getPassword().length() > 6)) {
            if (emailAccount.verifyPasswordExpiration() == false) {
                return emailService.emailList(emailAccount);
            } else {
                throw new RuntimeException("Password Expired!");
            }
        } else {
            throw new RuntimeException("Password must not contain more than 6 characters!");
        }
    }

    public void sendEmail(Email email) {

        if (isValidEmail(email)) {
            if (!emailService.sendEmail(email)) {
                throw new RuntimeException("Email did not sent!");
            }
        } else {
            throw new RuntimeException("Invalid Email!"); } }

    public boolean createAccount(EmailAccount account) {

        String address = account.getUser() + "@" + account.getDomain();

        if (isValidAddress(address) && account.getPassword().length() > 6) {
            account.setLastPasswordUpdate(LocalDate.now());
            accounts.add(account);
            return true;
        } else {
            return false;
        }
    }
}