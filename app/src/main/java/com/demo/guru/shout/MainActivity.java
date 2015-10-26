package com.demo.guru.shout;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;



public class MainActivity extends Activity {
//Declarations
    EditText user;
    EditText pass;
    Intent i;
  public static final String loginUrl ="http://navjotsingh.me/shouts.php";
    ProgressDialog progressDialog;
    public   static final String LOGINFO = "LogInfo";
    SharedPreferences  log;
    Button signin;

    //oncrate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    //username n password inputs
        user = (EditText)findViewById(R.id.userinput);
        pass= (EditText)findViewById(R.id.passinput);
        signin =(Button)findViewById(R.id.signin);

      //intent for shout page
       i = new Intent(getApplicationContext(),Shout.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

    //SharedPreferences for state management of login
     log  = this.getSharedPreferences(LOGINFO,this.MODE_PRIVATE);
if(log.getBoolean("login",false))
{//if already saved login go to shout activity
    startActivity(i);

}

        //progress dialoge
        progressDialog= new ProgressDialog(this);
        progressDialog.setCancelable(false);
    }

    //onrestart coming from another activity
    @Override
    protected void onRestart() {
        super.onRestart();
        progressDialog.hide();
    }



    public void signIn(View view)
{

    String email = user.getText().toString().trim();
    String password = pass.getText().toString().trim();



//check if input is not empty
    if(email.isEmpty() || password.isEmpty())
    {
        Toast.makeText(getApplicationContext(),"Please Enter both values",Toast.LENGTH_SHORT).show();
    }
    else
    {// call signin is input filled
        signedIn(email,password);

    }

}
    //sign in operaton using Volley request

public void signedIn(final String email,final String pass)
{
String tag_request = "req_signin";
    progressDialog.setMessage("Logging...");

    showDialog();

    StringRequest stringRequest = new StringRequest(Request.Method.GET, loginUrl, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {

            startActivity(i);
            SharedPreferences.Editor editor = log.edit();
            editor.putBoolean("login",true);
            editor.commit();
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getApplicationContext(),error.getMessage().toString(),Toast.LENGTH_SHORT).show();
            hideDialog();
        }
    }


    );



VollySingelton.getmInstance().addToRequestQueue(stringRequest,tag_request);
}


    //signup button is pressed
    public void signUp(View view)
    {
        Intent intent = new Intent(this,Signup.class);
        startActivity(intent);

    }


    //fb login is pressed
    public  void fblogin(View view)
    {



    }



    private  void  showDialog()
    {
        progressDialog.show();
    }

    private void hideDialog()
    {
        if(progressDialog.isShowing())
            progressDialog.hide();
    }

}

