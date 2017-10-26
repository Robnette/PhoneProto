package me.robnette.phoneproto.service;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by etienne.clercboichut on 25/10/2017.
 */

public class TokenInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request initialRequest = chain.request();
        Response response = chain.proceed(TokenService.requestBuild(initialRequest, TokenService.getInstance().getToken()).build());
        return response;
    }
}
