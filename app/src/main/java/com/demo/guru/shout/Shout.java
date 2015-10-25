package com.demo.guru.shout;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Shout extends AppCompatActivity {
Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shout);
        SharedPreferences log = getSharedPreferences(MainActivity.LOGINFO,this.MODE_PRIVATE);
        SharedPreferences.Editor editor =log.edit();

       logout = (Button)findViewById(R.id.logout);

    }

    public void logout(View view)
    {
        SharedPreferences log = getSharedPreferences(MainActivity.LOGINFO,this.MODE_PRIVATE);
        SharedPreferences.Editor editor =log.edit();
        editor.clear();
        editor.commit();

      this.finish();

    }


}
