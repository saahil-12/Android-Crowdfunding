package com.example.crowdfunding;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity implements View.OnClickListener {

    EditText username,password;
//    TextView usersign,orgsign;
    Button login,usersign;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username=findViewById(R.id.editTextTextPersonName2);
        password=findViewById(R.id.editTextTextPersonName3);
        usersign=findViewById(R.id.textView);
        usersign.setOnClickListener(this);
        login=findViewById(R.id.button2);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==usersign){
            Intent i =new Intent(getApplicationContext(),signup.class);
            startActivity(i);
        }
        String uname = username.getText().toString();
        String pass = password.getText().toString();

        if (uname.length()==0){
            username.setError("required");
            username.requestFocus();

        }
        else if(pass.length()==0) {
            password.setError("required");
            password.requestFocus();
        }
        else{
            SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            final String maclis=sh.getString("mac_list","");
            String uid=sh.getString("uid","");
            String hu = sh.getString("ip", "");
            String url = "http://" + hu + ":5000/and_user_login";



            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                            // response
                            try {
                                JSONObject jsonObj = new JSONObject(response);
                                if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                    if(jsonObj.getString("type").equalsIgnoreCase("user")) {

                                        SharedPreferences.Editor ed = sh.edit();
                                        ed.putString("lid", jsonObj.getString("lid"));
                                        ed.commit();

                                        Intent i = new Intent(getApplicationContext(), Home.class);
                                        startActivity(i);

                                        Toast.makeText(getApplicationContext(), "Successfull", Toast.LENGTH_LONG).show();

                                    }
                                    else{
                                        Toast.makeText(login.this, "Invalid login credentials", Toast.LENGTH_SHORT).show();
                                    }

                                }


                                // }
                                else {
                                    Toast.makeText(getApplicationContext(), "Invalid login credentials", Toast.LENGTH_LONG).show();
                                }

                            }    catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // error
                            Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() {
                    SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    Map<String, String> params = new HashMap<String, String>();


                    params.put("username",uname);
                    params.put("password",pass);
//                params.put("mac",maclis);

                    return params;
                }
            };

            int MY_SOCKET_TIMEOUT_MS=100000;

            postRequest.setRetryPolicy(new DefaultRetryPolicy(
                    MY_SOCKET_TIMEOUT_MS,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(postRequest);

        }
    }
}