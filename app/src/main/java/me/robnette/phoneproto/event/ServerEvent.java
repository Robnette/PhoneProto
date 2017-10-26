package me.robnette.phoneproto.event;

import me.robnette.phoneproto.model.ServerResponse;
import me.robnette.phoneproto.model.User;

/**
 * Created by etienne.clercboichut on 25/10/2017.
 */

public class ServerEvent<T> {
    private T data;

//    private ServerResponse serverResponse;
//    private User user;

    public ServerEvent(T data){
        this.data = data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData(){
        return data;
    }

//    public ServerEvent(ServerResponse serverResponse) {
//        this.serverResponse = serverResponse;
//    }
//
//    public ServerEvent(User user) {
//        this.user = user;
//    }
//
//    public ServerResponse getServerResponse() {
//        return serverResponse;
//    }
//
//    public void setServerResponse(ServerResponse serverResponse) {
//        this.serverResponse = serverResponse;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
}