package me.robnette.phoneproto.model;

import java.io.Serializable;

/**
 * Created by etienne.clercboichut on 25/10/2017.
 */

public class ServerResponse implements Serializable {
    private User user;
    private String token;

    public ServerResponse(User user, String token) {
        this.user = user;
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
