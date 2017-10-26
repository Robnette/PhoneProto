package me.robnette.phoneproto.api;

import me.robnette.phoneproto.model.ServerResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by etienne.clercboichut on 25/10/2017.
 */

public interface User {
    @GET("user")
    Call<me.robnette.phoneproto.model.User> get();
}
