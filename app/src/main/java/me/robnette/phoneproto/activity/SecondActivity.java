package me.robnette.phoneproto.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.otto.Subscribe;

import me.robnette.phoneproto.App;
import me.robnette.phoneproto.R;
import me.robnette.phoneproto.event.ErrorEvent;
import me.robnette.phoneproto.event.ServerEvent;
import me.robnette.phoneproto.model.ServerResponse;
import me.robnette.phoneproto.model.User;
import me.robnette.phoneproto.service.BusProvider;
import me.robnette.phoneproto.service.Communicator;

/**
 * Created by etienne.clercboichut on 25/10/2017.
 */

public class SecondActivity extends AppCompatActivity {
    private TextView generalText;
    private Communicator communicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seconde_activity );

        generalText = (TextView)findViewById(R.id.generalText);

        communicator = new Communicator();
        callUser();

//        Intent intent = getIntent();
//        if (intent != null){
//            User user = intent.getParcelableExtra("user");
//            if (user != null){
//                Log.i("USER", user.toString());
//            }
//        }
    }

    private void callUser(){
        communicator.callUser();
    }

    @Override
    public void onResume(){
        super.onResume();
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onPause(){
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    @Subscribe
    public void onServerEvent(ServerEvent serverEvent){

        User user = (User)serverEvent.getData();
        if(user != null){
            Toast.makeText(this, user.getUsername(), Toast.LENGTH_SHORT).show();
            Gson gson = new Gson();
            generalText.setText(gson.toJson(user));
        }
//        Toast.makeText(this, ""+serverEvent.getServerResponse().getMessage(), Toast.LENGTH_SHORT).show();
//        if(serverEvent.getServerResponse().getMessage() != null){
//            information.setText("Message: "+serverEvent.getServerResponse().getMessage());
//        }
//        extraInformation.setText("" + serverEvent.getServerResponse().getMessage());
    }

    @Subscribe
    public void onErrorEvent(ErrorEvent errorEvent){
        Toast.makeText(this,""+errorEvent.getErrorMsg(), Toast.LENGTH_SHORT).show();

        App.setToken(null);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
