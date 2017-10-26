package me.robnette.phoneproto.service;

import android.content.Context;
import android.os.storage.StorageManager;

import me.robnette.phoneproto.App;
import okhttp3.Request;

import static me.robnette.phoneproto.util.Constant.BEARER_AUTHENTICATION;
import static me.robnette.phoneproto.util.Constant.TOKEN_FILE_NAME;

/**
 * Created by etienne.clercboichut on 25/10/2017.
 */

public class TokenService {
    private static TokenService tokenService;
    private static Context context;
//    private static String token;

    public static TokenService getInstance() {
        if (tokenService == null) {
            init();
        }
        return tokenService;
    }

    private static void init() {

        Class clazz = TokenService.class;
        synchronized (clazz) {
            tokenService = new TokenService();
            tokenService.context = App.getAppContext();
        }
    }

    public synchronized String getToken(){
//        if(token != null){
//            return token;
//        }
//        if(StorageService.isData(context, TOKEN_FILE_NAME)){
//            token = StorageService.loadData(context, TOKEN_FILE_NAME, String.class);
//        }
        return App.getToken();
    }

    public static Request.Builder requestBuild(Request request, String token) {
        return request.newBuilder()
                .header("Accept", "application/json")
                .header("Authorization", BEARER_AUTHENTICATION + token)
                .method(request.method(), request.body());
    }
}
