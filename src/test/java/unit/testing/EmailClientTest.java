package unit.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import unit.testing.utils.EmailAccountBuilder;
import unit.testing.utils.EmailBuilder;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmailClientTest {

    @Mock
    EmailService emailService;
    @InjectMocks
    EmailClient emailClient;

    @Test
    public void isValidAddress_Valid_True() {
        Assertions.assertTrue(emailClient.isValidAddress(".sdgh_-jk@ksdco.mbr"));
    }

    @Test
    public void isValidAddress_NotValid_False() {

        Assertions.assertFalse(emailClient.isValidAddress("gfL8._k@faksd.com."));
    }

    @Test
    public void IsValidEmail_NotValidCreationDateNull_False() {

        Email emailBuilder = new EmailBuilder()
                .build();
        Assertions.assertFalse(emailClient.isValidEmail(emailBuilder));
    }

    @Test
    public void IsValidEmail_InvalidFrom_False() {
        Email emailBuilder = new EmailBuilder()
                .setCreationDateBuilder(Instant.now())
                .setFromBuilder("#2fsdffsdf.com")
                .build();
        Assertions.assertFalse(emailClient.isValidEmail(emailBuilder));
    }

    @Test
    public void IsValidEmail_NullToList_False() {

        Email emailBuilder = new EmailBuilder()
                .setCreationDateBuilder(Instant.now())
                .setFromBuilder("2f_sdff@sdf.com")
                .build();
        Assertions.assertFalse(emailClient.isValidEmail(emailBuilder));
    }

    @Test
    public void IsValidEmail_EmptyToList_False() {

        ArrayList<String> listOfTo = new ArrayList<String>();

        Email emailBuilder = new EmailBuilder()
                .setCreationDateBuilder(Instant.now())
                .setFromBuilder("2f_sdff@sdf.com")
                .setToBuilder(listOfTo)
                .build();
        Assertions.assertFalse(emailClient.isValidEmail(emailBuilder));
    }

    @Test
    public void IsValidEmail_AllToListInvalid_False() {

        ArrayList<String> listOfTo = new ArrayList<String>();
        listOfTo.add("sdfs44-dgdddd");
        listOfTo.add("23&%fsdf@gsdg.c");

        Email emailBuilder = new EmailBuilder()
                .setCreationDateBuilder(Instant.now())
                .setFromBuilder("2f_sdff@sdf.com")
                .setToBuilder(listOfTo)
                .build();
        Assertions.assertFalse(emailClient.isValidEmail(emailBuilder));
    }

    @Test
    public void IsValidEmail_ValidEmailCcListNullBccListNull_True() {

        ArrayList<String> listOfTo = new ArrayList<String>();
        listOfTo.add("sdfs44-dg@cdsc.com");

        Email emailBuilder = new EmailBuilder()
                .setCreationDateBuilder(Instant.now())
                .setFromBuilder("2f_sdff@sdf.com")
                .setToBuilder(listOfTo)
                .build();
        Assertions.assertTrue(emailClient.isValidEmail(emailBuilder));
    }

    @Test
    public void IsValidEmail_ValidEmailCcListNullBccListEmpty_True() {

        ArrayList<String> listOfTo = new ArrayList<String>();
        listOfTo.add("sdfs44-dg@cdsc.com");

        ArrayList<String> BccList = new ArrayList<String>();

        Email emailBuilder = new EmailBuilder()
                .setCreationDateBuilder(Instant.now())
                .setFromBuilder("2f_sdff@sdf.com")
                .setToBuilder(listOfTo)
                .setBccBuilder(BccList)
                .build();
        Assertions.assertTrue(emailClient.isValidEmail(emailBuilder));
    }

    @Test
    public void IsValidEmail_ValidEmailCcListNullBccInvalid_False() {

        ArrayList<String> listOfTo = new ArrayList<String>();
        listOfTo.add("sdfs44-dg@cdsc.com");

        ArrayList<String> BccList = new ArrayList<String>();
        BccList.add("@sfdsf.com");

        Email emailBuilder = new EmailBuilder()
                .setCreationDateBuilder(Instant.now())
                .setFromBuilder("2f_sdff@sdf.com")
                .setToBuilder(listOfTo)
                .setBccBuilder(BccList)
                .build();
        Assertions.assertFalse(emailClient.isValidEmail(emailBuilder));
    }

    @Test
    public void IsValidEmail_ValidEmailCcListNullBccValid_True() {

        ArrayList<String> listOfTo = new ArrayList<String>();
        listOfTo.add("sdfs44-dg@cdsc.com");

        ArrayList<String> BccList = new ArrayList<String>();
        BccList.add("dsfds@sfdsf.com");

        Email emailBuilder = new EmailBuilder()
                .setCreationDateBuilder(Instant.now())
                .setFromBuilder("2f_sdff@sdf.com")
                .setToBuilder(listOfTo)
                .setBccBuilder(BccList)
                .build();
        Assertions.assertTrue(emailClient.isValidEmail(emailBuilder));
    }

    @Test
    public void IsValidEmail_ValidEmailCcListEmptyBccListNull_True() {

        ArrayList<String> listOfTo = new ArrayList<String>();
        listOfTo.add("sdfs44-dg@cdsc.com");

        ArrayList<String> CcList = new ArrayList<String>();

        Email emailBuilder = new EmailBuilder()
                .setCreationDateBuilder(Instant.now())
                .setFromBuilder("2f_sdff@sdf.com")
                .setToBuilder(listOfTo)
                .setCcBuilder(CcList)
                .build();
        Assertions.assertTrue(emailClient.isValidEmail(emailBuilder));
    }

    @Test
    public void IsValidEmail_ValidEmailCcListEmptyBccListEmpty_True() {

        ArrayList<String> listOfTo = new ArrayList<String>();
        listOfTo.add("sdfs44-dg@cdsc.com");

        ArrayList<String> CcList = new ArrayList<String>();

        ArrayList<String> BccList = new ArrayList<String>();

        Email emailBuilder = new EmailBuilder()
                .setCreationDateBuilder(Instant.now())
                .setFromBuilder("2f_sdff@sdf.com")
                .setToBuilder(listOfTo)
                .setCcBuilder(CcList)
                .setBccBuilder(BccList)
                .build();
        Assertions.assertTrue(emailClient.isValidEmail(emailBuilder));
    }

    @Test
    public void IsValidEmail_ValidEmailCcListEmptyBccListInvalid_False() {

        ArrayList<String> listOfTo = new ArrayList<String>();
        listOfTo.add("sdfs44-dg@cdsc.com");

        ArrayList<String> CcList = new ArrayList<String>();

        ArrayList<String> BccList = new ArrayList<String>();
        BccList.add("2#4rdfd");

        Email emailBuilder = new EmailBuilder()
                .setCreationDateBuilder(Instant.now())
                .setFromBuilder("2f_sdff@sdf.com")
                .setToBuilder(listOfTo)
                .setCcBuilder(CcList)
                .setBccBuilder(BccList)
                .build();
        Assertions.assertFalse(emailClient.isValidEmail(emailBuilder));
    }

    @Test
    public void IsValidEmail_ValidEmailCcListEmptyBccListValid_True() {

        ArrayList<String> listOfTo = new ArrayList<String>();
        listOfTo.add("sdfs44-dg@cdsc.com");

        ArrayList<String> CcList = new ArrayList<String>();

        ArrayList<String> BccList = new ArrayList<String>();
        BccList.add("4rdfd@sdfds.com");

        Email emailBuilder = new EmailBuilder()
                .setCreationDateBuilder(Instant.now())
                .setFromBuilder("2f_sdff@sdf.com")
                .setToBuilder(listOfTo)
                .setCcBuilder(CcList)
                .setBccBuilder(BccList)
                .build();
        Assertions.assertTrue(emailClient.isValidEmail(emailBuilder));
    }

    @Test
    public void IsValidEmail_NotValidEmailCcListInvalid_False() {

        ArrayList<String> listOfTo = new ArrayList<String>();
        listOfTo.add("sdfs44-dg@cdsc.com");

        ArrayList<String> CcList = new ArrayList<String>();
        CcList.add("fe#2@fsdf.com");

        Email emailBuilder = new EmailBuilder()
                .setCreationDateBuilder(Instant.now())
                .setFromBuilder("2f_sdff@sdf.com")
                .setToBuilder(listOfTo)
                .setCcBuilder(CcList)
                .build();
        Assertions.assertFalse(emailClient.isValidEmail(emailBuilder));
    }

    @Test
    public void IsValidEmail_ValidEmailCcListValidBccListNull_True() {

        ArrayList<String> listOfTo = new ArrayList<String>();
        listOfTo.add("sdfs44-dg@cdsc.com");

        ArrayList<String> CcList = new ArrayList<String>();
        CcList.add("fe-2@fsdf.com");

        Email emailBuilder = new EmailBuilder()
                .setCreationDateBuilder(Instant.now())
                .setFromBuilder("2f_sdff@sdf.com")
                .setToBuilder(listOfTo)
                .setCcBuilder(CcList)
                .build();
        Assertions.assertTrue(emailClient.isValidEmail(emailBuilder));
    }

    @Test
    public void IsValidEmail_ValidEmailCcListValidBccListEmpty_True() {

        ArrayList<String> listOfTo = new ArrayList<String>();
        listOfTo.add("sdfs44-dg@cdsc.com");

        ArrayList<String> CcList = new ArrayList<String>();
        CcList.add("fe-2@fsdf.com");

        ArrayList<String> BccList = new ArrayList<String>();


        Email emailBuilder = new EmailBuilder()
                .setCreationDateBuilder(Instant.now())
                .setFromBuilder("2f_sdff@sdf.com")
                .setToBuilder(listOfTo)
                .setCcBuilder(CcList)
                .setBccBuilder(BccList)
                .build();
        Assertions.assertTrue(emailClient.isValidEmail(emailBuilder));
    }

    @Test
    public void IsValidEmail_ValidEmailCcListValidBccListValid_True() {

        ArrayList<String> listOfTo = new ArrayList<String>();
        listOfTo.add("sdfs44-dg@cdsc.com");

        ArrayList<String> CcList = new ArrayList<String>();
        CcList.add("fe-2@fsdf.com");

        ArrayList<String> BccList = new ArrayList<String>();
        BccList.add("rwer@safsdf.com.br");
        BccList.add(".12e_qwe@fasdf.com.br");

        Email emailBuilder = new EmailBuilder()
                .setCreationDateBuilder(Instant.now())
                .setFromBuilder("2f_sdff@sdf.com")
                .setToBuilder(listOfTo)
                .setCcBuilder(CcList)
                .setBccBuilder(BccList)
                .build();
        Assertions.assertTrue(emailClient.isValidEmail(emailBuilder));
    }

    @Test
    public void IsValidEmail_NotValidEmailCcListValidBccListInvalid_False() {

        ArrayList<String> listOfTo = new ArrayList<String>();
        listOfTo.add("sdfs44-dg@cdsc.com");

        ArrayList<String> CcList = new ArrayList<String>();
        CcList.add("fe-2@fsdf.com");

        ArrayList<String> BccList = new ArrayList<String>();
        BccList.add("#@rwer@safsdf.com.br");
        BccList.add(".12e_qwe@fasdf.com.br");

        Email emailBuilder = new EmailBuilder()
                .setCreationDateBuilder(Instant.now())
                .setFromBuilder("2f_sdff@sdf.com")
                .setToBuilder(listOfTo)
                .setCcBuilder(CcList)
                .setBccBuilder(BccList)
                .build();
        Assertions.assertFalse(emailClient.isValidEmail(emailBuilder));
    }

    @Test
    public void emailList_ValidPassword_EmailList() {

        EmailAccount emailAccount = new EmailAccountBuilder()
                .setLastPasswordUpdate(LocalDate.now())
                .setPassword("435435rgf")
                .build();

        ArrayList<Email> list = new ArrayList<Email>();

        when(emailService.emailList(emailAccount)).thenReturn(list);
        Assertions.assertEquals(emailClient.emailList(emailAccount), list);
    }


    @Test
    public void emailList_InvalidPassword_False() {

        EmailAccount emailAccount = new EmailAccountBuilder()
                .setLastPasswordUpdate(LocalDate.now())
                .setPassword("5rgf")
                .build();

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            emailClient.emailList(emailAccount);
        });
        Assertions.assertEquals("Password must not contain more than 6 characters!", exception.getMessage());
    }


    @Test
    public void emailList_PasswordExpired_False() {

        EmailAccount emailAccount = new EmailAccountBuilder()
                .setLastPasswordUpdate(LocalDate.of(2018, 6, 1))
                .setPassword("523regf")
                .build();

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            emailClient.emailList(emailAccount);
        });
        Assertions.assertEquals("Password Expired!", exception.getMessage());
    }

    @Test
    public void sendEmail_EmailSent_True() {


        ArrayList<String> listOfTo = new ArrayList<String>();
        listOfTo.add("sdfs44-dg@cdsc.com");

        Email email = new EmailBuilder()
                .setCreationDateBuilder(Instant.now())
                .setFromBuilder("2f_sdff@sdf.com")
                .setToBuilder(listOfTo)
                .build();


        when(emailService.sendEmail(email)).thenReturn(false);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            emailClient.sendEmail(email);
        });
        Assertions.assertEquals("Email did not sent!", exception.getMessage());


    }

    @Test
    public void sendEmail_EmailNotSent_False() {

        Email email1 = new EmailBuilder()
                .setSubjectBuilder("fgdgdf")
                .build();

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            emailClient.sendEmail(email1);
        });
        Assertions.assertEquals("Invalid Email!", exception.getMessage());
    }


    @Test
    public void createAccount_Created_True() {

        EmailAccount emailAccount = new EmailAccountBuilder()
                .setLastPasswordUpdate(LocalDate.now())
                .setPassword("435435rgf")
                .setDomain("dgdsfg.com")
                .setUser("fhahr")
                .build();

        Assertions.assertTrue(emailClient.createAccount(emailAccount));
    }

    @Test
    public void createAccount_NotCreatedInvalidPassword_False() {

        EmailAccount emailAccount = new EmailAccountBuilder()
                .setLastPasswordUpdate(LocalDate.now())
                .setPassword("5rgf")
                .setDomain("dgdsfg.com")
                .setUser("fhahr")
                .build();

        Assertions.assertFalse(emailClient.createAccount(emailAccount));
    }

    @Test
    public void createAccount_NotCreatedInvalidAddress_False() {

        EmailAccount emailAccount = new EmailAccountBuilder()
                .setLastPasswordUpdate(LocalDate.now())
                .setPassword("5dfgdfgrgf")
                .setDomain("dgdsfg..com")
                .setUser("fhahr")
                .build();

        Assertions.assertFalse(emailClient.createAccount(emailAccount));
    }
}