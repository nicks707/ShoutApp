package com.demo.guru.shout;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
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
    EditText user;
    EditText pass;
  public static final String loginUrl ="http://navjotsingh.me/shouts.php";
    ProgressDialog progressDialog;
    Button signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    //username n password inputs
        user = (EditText)findViewById(R.id.userinput);
        pass= (EditText)findViewById(R.id.passinput);
        signin =(Button)findViewById(R.id.signin);

        //progress dialoge
        progressDialog= new ProgressDialog(this);
        progressDialog.setCancelable(false);
    }

public void signIn(View view)
{

    String email = user.getText().toString().trim();
    String password = pass.getText().toString().trim();

    if(email.isEmpty() || password.isEmpty())
    {
        Toast.makeText(getApplicationContext(),"Please Enter both values",Toast.LENGTH_SHORT).show();
    }
    else
    {
        signedIn(email,password);

    }

}

public void signedIn(final String email,final String pass)
{
String tag_request = "req_signin";
    progressDialog.setMessage("Logging...");

    showDialog();

    StringRequest stringRequest = new StringRequest(Request.Method.GET, loginUrl, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Intent i = new Intent(getApplicationContext(),Shout.class);
            startActivity(i);
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getApplicationContext(),error.getMessage().toString(),Toast.LENGTH_SHORT).show();
            hideDialog();
        }
    }


    );
VollySingelton v = VollySingelton.getmInstance();


VollySingelton.getmInstance().addToRequestQueue(stringRequest,tag_request);
}

    private  void  showDialog()
    {if(!progressDialog.isShowing())
        progressDialog.show();
    }

    private void hideDialog()
    {
        if(progressDialog.isShowing())
            progressDialog.hide();
    }

}

