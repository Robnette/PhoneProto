package me.robnette.phoneproto.service;

import android.util.Log;

import com.squareup.otto.Produce;

import org.json.JSONException;
import org.json.JSONObject;

import me.robnette.phoneproto.api.Login;
import me.robnette.phoneproto.api.User;
import me.robnette.phoneproto.event.ErrorEvent;
import me.robnette.phoneproto.event.ServerEvent;
import me.robnette.phoneproto.model.LoginPojo;
import me.robnette.phoneproto.model.ServerResponse;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by etienne.clercboichut on 25/10/2017.
 */

public class Communicator {
    private static  final String TAG = "Communicator";
    private static final String SERVER_URL = "http://10.94.200.24:8080/SpringAngular/";

    public void loginPost(LoginPojo loginPojo){

        //Here a logging interceptor is created
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        //The logging interceptor will be added to the http client
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        //The Retrofit builder will have the client attached, in order to get connection logs
        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SERVER_URL)
                .build();
        Login service = retrofit.create(Login.class);

        Call<ServerResponse> call = service.post(loginPojo);

        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if(response.isSuccessful()){
                    BusProvider.getInstance().post(new ServerEvent(response.body()));
                    Log.i(TAG,"Success");
                }else{
                    BusProvider.getInstance().post(new ErrorEvent(response.code(), "Login or password incorrect."));
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                // handle execution failures like no internet connectivity
                BusProvider.getInstance().post(new ErrorEvent(-2,t.getMessage()));
            }
        });
    }

    public void callUser(){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new TokenInterceptor());

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SERVER_URL)
                .build();
        User service = retrofit.create(User.class);

        Call<me.robnette.phoneproto.model.User> call = service.get();

        call.enqueue(new Callback<me.robnette.phoneproto.model.User>() {
            @Override
            public void onResponse(Call<me.robnette.phoneproto.model.User> call, Response<me.robnette.phoneproto.model.User> response) {
                if(response.isSuccessful()){
                    BusProvider.getInstance().post(new ServerEvent(response.body()));
                    Log.i(TAG,"Success");
                }else{
                    String message = "Error !!";
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        message = jObjError.getString("message");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    BusProvider.getInstance().post(new ErrorEvent(response.code(), message));




                }
            }

            @Override
            public void onFailure(Call<me.robnette.phoneproto.model.User> call, Throwable t) {
                // handle execution failures like no internet connectivity
                BusProvider.getInstance().post(new ErrorEvent(-2,t.getMessage()));
            }
        });
    }

//    public void loginGet(String username, String password){
//        //Here a logging interceptor is created
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        //The logging interceptor will be added to the http client
//        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//        httpClient.addInterceptor(logging);
//
//        //The Retrofit builder will have the client attached, in order to get connection logs
//        Retrofit retrofit = new Retrofit.Builder()
//                .client(httpClient.build())
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl(SERVER_URL)
//                .build();
//
//        Login service = retrofit.create(Login.class);
//
//        Call<ServerResponse> call = service.get(username,password);
//
//        call.enqueue(new Callback<ServerResponse>() {
//            @Override
//            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
//                if(response.isSuccessful()){
//                    BusProvider.getInstance().post(new ServerEvent(response.body()));
//                    Log.i(TAG,"Success");
//                }else{
//                    BusProvider.getInstance().post(new ErrorEvent(response.code(), "Login or password incorrect."));
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<ServerResponse> call, Throwable t) {
//                // handle execution failures like no internet connectivity
//                BusProvider.getInstance().post(new ErrorEvent(-2,t.getMessage()));
//            }
//        });
//    }

    @Produce
    public ServerEvent produceServerEvent(ServerResponse serverResponse) {
        return new ServerEvent(serverResponse);
    }

    @Produce
    public ErrorEvent produceErrorEvent(int errorCode, String errorMsg) {
        return new ErrorEvent(errorCode, errorMsg);
    }
}
