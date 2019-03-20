package unit.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import unit.testing.utils.EmailAccountBuilder;

import java.time.LocalDate;

public class EmailAccountTest {

    EmailAccount account1 = new EmailAccountBuilder()
            .setUser("gbl")
            .setDomain("gmail")
            .setPassword("la764la")
            .setLastPasswordUpdate(LocalDate.of(2017, 04, 03))
            .build();

    @Test
    public void verifyPasswordExpiration_ExpiredIsTrue_True() {

        account1.setLastPasswordUpdate(LocalDate.of(2014, 9, 1));
        Assertions.assertTrue(account1.verifyPasswordExpiration());
    }


    @Test
    public void verifyPasswordExpiration_ExpiredIsFalse_False() {

        EmailAccount account = new EmailAccount("urdbl", "gmail", "la7654la", LocalDate.of(2018, 11, 1));
        account.setLastPasswordUpdate(LocalDate.of(2018, 8, 1));
        Assertions.assertTrue(account.verifyPasswordExpiration());
    }

}