package me.robnette.phoneproto.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import me.robnette.phoneproto.R;
import me.robnette.phoneproto.model.User;

public class FirstActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);



    }

    public void changeActivity(View view){
        User user = new User("Toto", "Titi");

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}
