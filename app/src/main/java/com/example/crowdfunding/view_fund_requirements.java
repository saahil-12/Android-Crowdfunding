package com.example.crowdfunding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class view_fund_requirements extends AppCompatActivity {

    ListView fundrequire;
    String[]R_id,Org_lid,Name,Amount,Address1,Address2,Address3,Purpose,Date,Status,Organization_Name;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent ij=new Intent(getApplicationContext(),Home.class);
        startActivity(ij);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_fund_requirements);
        fundrequire=findViewById(R.id.fund);

        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String maclis=sh.getString("mac_list","");
        String uid=sh.getString("uid","");
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":5000/and_user_view_fund_requirements";



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


                                JSONArray js= jsonObj.getJSONArray("data");
                                R_id=new String[js.length()];
                                Org_lid=new String[js.length()];
                                Name=new String[js.length()];

                                Amount=new String[js.length()];
                                Address1=new String[js.length()];
                                Address2=new String[js.length()];
                                Address3=new String[js.length()];
                                Date=new String[js.length()];
                                Purpose=new String[js.length()];
                                Status=new String[js.length()];
                                Organization_Name=new String[js.length()];


                                for(int i=0;i<js.length();i++)
                                {
                                    JSONObject u=js.getJSONObject(i);
                                    R_id[i]=u.getString("R_id");
                                    Org_lid[i]=u.getString("Org_lid");
                                    Name[i]=u.getString("Name");

                                    Amount[i]=u.getString("am");
                                    Address1[i]=u.getString("Address1");
                                    Address2[i]=u.getString("Address2");
                                    Address3[i]=u.getString("Address3");
                                    Purpose[i]=u.getString("Purpose");
                                    Date[i]=u.getString("Date");
                                    Status[i]=u.getString("Status");
                                    Organization_Name[i]=u.getString("Org_Name");


                                }
                                fundrequire.setAdapter(new custom_view_fund_requirements (getApplicationContext(),R_id,Org_lid,Name,Amount,Address1,Address2,Address3,Purpose,Date,Status,Organization_Name));


                            }


                            // }
                            else {
                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
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

                String id=sh.getString("uid","");
                params.put("uid",id);
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