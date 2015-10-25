package com.demo.guru.shout;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
    EditText user;
    EditText pass;
    Button signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    user = (EditText)findViewById(R.id.userinput);
        pass= (EditText)findViewById(R.id.passinput);
        signin =(Button)findViewById(R.id.signin);

    }

public void signIn(View view)
{

    String email = user.getText().toString().trim();
    String password = pass.getText().toString().trim();

    if(email.isEmpty() && password.isEmpty())
    {
        Toast.makeText(getApplicationContext(),"Please Enter both values",Toast.LENGTH_SHORT).show();
    }
    else
    {
        signedIn(email,password);

    }

}

public void signedIn(String a,String b)
{


}

}

