package me.robnette.phoneproto.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import me.robnette.phoneproto.App;
import me.robnette.phoneproto.R;
import me.robnette.phoneproto.event.ErrorEvent;
import me.robnette.phoneproto.event.ServerEvent;
import me.robnette.phoneproto.model.LoginPojo;
import me.robnette.phoneproto.model.ServerResponse;
import me.robnette.phoneproto.service.BusProvider;
import me.robnette.phoneproto.service.Communicator;

/**
 * Created by etienne.clercboichut on 25/10/2017.
 */

public class LoginActivity  extends AppCompatActivity {
    private Communicator communicator;
    private String username, password;
    private EditText usernameET, passwordET;
    private Button loginButtonPost, loginButtonGet;
    private TextView information, extraInformation;
    private final static String TAG = "MainActivity";
    public static Bus bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        communicator = new Communicator();

        usernameET = (EditText)findViewById(R.id.usernameInput);
        passwordET = (EditText)findViewById(R.id.passwordInput);
        //This is used to hide the password's EditText characters. So we can avoid the different hint font.
        passwordET.setTransformationMethod(new PasswordTransformationMethod());

        loginButtonPost = (Button)findViewById(R.id.loginButtonPost);
        loginButtonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = usernameET.getText().toString();
                password = passwordET.getText().toString();
                usePost(username, password);
            }
        });

//        loginButtonGet = (Button)findViewById(R.id.loginButtonGet);
//        loginButtonGet.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                username = usernameET.getText().toString();
//                password = passwordET.getText().toString();
//                useGet(username, password);
//            }
//        });

        information = (TextView)findViewById(R.id.information);
        extraInformation = (TextView)findViewById(R.id.extraInformation);
    }

    private void usePost(String username, String password){
        communicator.loginPost(new LoginPojo(username, password));
    }

//    private void useGet(String username, String password){
//        communicator.loginGet(username, password);
//    }

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

        ServerResponse serverResponce = (ServerResponse)serverEvent.getData();
        if(serverResponce != null && serverResponce.getUser() != null){
            Toast.makeText(this, serverResponce.getUser().getUsername(), Toast.LENGTH_SHORT).show();
            information.setText("Message: " + serverResponce.getUser().toString());
            extraInformation.setText("Token: " + serverResponce.getToken());

            App.setToken(serverResponce.getToken());
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
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
    }
}
