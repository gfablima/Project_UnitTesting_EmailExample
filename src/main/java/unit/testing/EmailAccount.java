package unit.testing;

import java.time.LocalDate;

public class EmailAccount {

    private String user = "";
    private String domain = "";
    private String password = "";
    private LocalDate lastPasswordUpdate;

    public void setLastPasswordUpdate(LocalDate lastPasswordUpdate) {
        this.lastPasswordUpdate = lastPasswordUpdate;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getDomain() {
        return domain;
    }

    public boolean verifyPasswordExpiration() {

        LocalDate expirationLimit = LocalDate.now().minusDays(90);

        //Password is expired if the last update date is more than 90 days from current date
        if (lastPasswordUpdate.isBefore(expirationLimit)) {
            return true;
        } else {
            return false;
        }

    }

    public EmailAccount(String user, String domain, String password, LocalDate lastPasswordUpdate) {
        this.user = user;
        this.domain = domain;
        this.password = password;
        this.lastPasswordUpdate = lastPasswordUpdate;
    }
}