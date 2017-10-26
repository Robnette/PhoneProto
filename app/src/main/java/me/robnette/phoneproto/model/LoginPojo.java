package me.robnette.phoneproto.model;

/**
 * Created by etienne.clercboichut on 25/10/2017.
 */

public class LoginPojo {
    private String username;
    private String password;

    public LoginPojo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
