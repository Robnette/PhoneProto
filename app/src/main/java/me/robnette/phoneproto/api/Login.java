package me.robnette.phoneproto.api;

import me.robnette.phoneproto.model.LoginPojo;
import me.robnette.phoneproto.model.ServerResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by etienne.clercboichut on 25/10/2017.
 */

public interface Login {
    //This method is used for "POST"
    @POST("authenticate")
    Call<ServerResponse> post(@Body LoginPojo loginPojo);

    //This method is used for "GET"
    @GET("authenticate_test")
    Call<ServerResponse> get(
            @Query("username") String username,
            @Query("password") String password
    );

}
