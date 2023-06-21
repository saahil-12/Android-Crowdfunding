package com.example.crowdfunding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class payment_details extends AppCompatActivity implements View.OnClickListener {

    EditText accountnumber,keyid,amount;
    Button submit;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent ij=new Intent(getApplicationContext(),Home.class);
        startActivity(ij);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);

        accountnumber=findViewById(R.id.editTextTextPersonName24);
        keyid=findViewById(R.id.editTextTextPersonName25);
        amount=findViewById(R.id.editTextTextPersonName26);
        submit=findViewById(R.id.button8);
        submit.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        String Account_Number= accountnumber.getText().toString();
        String Key_id= keyid.getText().toString();
        String Amount= amount.getText().toString();

        if(Account_Number.length()==0){
            accountnumber.setError("required");
            accountnumber.requestFocus();
        }
        else if(Key_id.length()==0){
            keyid.setError("required");
            keyid.requestFocus();
        }
        else if(Amount.length()==0){
            amount.setError("required");
            amount.requestFocus();
        }
        else{

            SharedPreferences spa= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

            float s= Float.parseFloat(spa.getString("fs",""));

            Toast.makeText(getApplicationContext(), s+"", Toast.LENGTH_SHORT).show();

            if(Float.parseFloat(Amount) >s)
            {
                Toast.makeText(getApplicationContext(), "Maximum amount is" + s, Toast.LENGTH_SHORT).show();
            }
            else {


                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                final String maclis = sh.getString("mac_list", "");
                String uid = sh.getString("uid", "");
                String hu = sh.getString("ip", "");
                String url = "http://" + hu + ":5000/and_payment_details";


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

                                        Toast.makeText(payment_details.this, "Success", Toast.LENGTH_SHORT).show();

                                        startActivity(new Intent(getApplicationContext(), Home.class));

                                    }


                                    // }
                                    else {
                                        Toast.makeText(getApplicationContext(), "Invalid login credentials", Toast.LENGTH_LONG).show();
                                    }

                                } catch (Exception e) {
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


                        params.put("account_number", Account_Number);
                        params.put("key_id", Key_id);
                        params.put("amount", Amount);
                        params.put("r_id", sh.getString("rid", ""));
                        params.put("lid", sh.getString("lid", ""));
//                params.put("mac",maclis);

                        return params;
                    }
                };

                int MY_SOCKET_TIMEOUT_MS = 100000;

                postRequest.setRetryPolicy(new DefaultRetryPolicy(
                        MY_SOCKET_TIMEOUT_MS,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                requestQueue.add(postRequest);

            }



        }

    }
}