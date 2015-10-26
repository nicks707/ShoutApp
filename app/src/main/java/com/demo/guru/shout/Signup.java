package com.demo.guru.shout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {
EditText name;
    EditText email;
    EditText pass;
    Button register;
    Button gotosignin;

    public static final String postUrl = "http://navjotsingh.me/shouts.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    name = (EditText)findViewById(R.id.name);
    email =(EditText)findViewById(R.id.email);
     pass =(EditText)findViewById(R.id.passwordsignup);
        register =(Button)findViewById(R.id.register);
        gotosignin =(Button)findViewById(R.id.goback);


    }
    public  void gotosignin(View view)
    {
        this.finish();

    }
    public void register(View view) {
        String  em = email.getText().toString();
        String ps = pass.getText().toString();
        String nm = name.getText().toString();


        if (em.isEmpty() || ps.isEmpty() || nm.isEmpty()) {
            Toast.makeText(this, "Please enter all Values", Toast.LENGTH_SHORT).show();
        } else {
            signup(nm, em, ps);

        }

    }

public  void signup(final String  name , final String email, final String password )
{

    StringRequest req = new StringRequest(Method.POST, postUrl, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
      Toast.makeText(getApplicationContext(),"Postive response"+response,Toast.LENGTH_SHORT).show();
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getApplicationContext(),"Error Response"+error,Toast.LENGTH_SHORT).show();
        }
    })
    {
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String,String> params = new HashMap<String,String>();
            params.put("name",name);
            params.put("email",email);
            params.put("password",password);

            return params;
        }
    };

    VollySingelton.getmInstance().addToRequestQueue(req);
}





}
