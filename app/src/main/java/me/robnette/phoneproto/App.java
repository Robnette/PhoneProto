package me.robnette.phoneproto;

import android.app.Application;
import android.content.Context;

/**
 * Created by etienne.clercboichut on 25/10/2017.
 */

public class App extends Application {
    private static Context context;
    private static String token;

    public void onCreate() {
        super.onCreate();
        App.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return App.context;
    }

    public static void setToken(String token){
        App.token = token;
    }

    public static String getToken(){
        return App.token;
    }
}
