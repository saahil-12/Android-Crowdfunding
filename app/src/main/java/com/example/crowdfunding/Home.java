package com.example.crowdfunding;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.crowdfunding.databinding.ActivityHomeBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


        ListView fundrequire;
        String[] R_id, Org_lid, Name, Amount, Address1, Address2, Address3, Purpose, Date, Status, Organization_Name,remainingamnt;


    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarHome.toolbar);
//        binding.appBarHome.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);



        {
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
                                    remainingamnt=new String[js.length()];


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
                                        remainingamnt[i]=u.getString("Amount");


                                    }
                                    fundrequire.setAdapter(new custom_requests (getApplicationContext(),R_id,Org_lid,Name,Amount,Address1,Address2,Address3,Purpose,Date,Status,Organization_Name,remainingamnt));


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            Intent i = new Intent(getApplicationContext(), Home.class);
            startActivity(i);
        }
        if (id == R.id.profile) {
            Intent i = new Intent(getApplicationContext(), view_profile.class);
            startActivity(i);
        }


        if (id == R.id.change_password) {
            Intent i = new Intent(getApplicationContext(), change_password.class);
            startActivity(i);
        }
//        if (id == R.id.send_complaint) {
//            Intent i = new Intent(getApplicationContext(), send_complaint.class);
//            startActivity(i);
//
//        }
        if (id == R.id.view_reply) {
            Intent i = new Intent(getApplicationContext(), view_reply.class);
            startActivity(i);
        }
        if (id == R.id.view_fund_requirements) {
            Intent i = new Intent(getApplicationContext(), view_fund_requirements.class);
            startActivity(i);

        }
        if (id == R.id.logout) {
            Intent i = new Intent(getApplicationContext(), login.class);
            startActivity(i);

        }
        if (id == R.id.transactions) {
            Intent i = new Intent(getApplicationContext(), view_result.class);
            startActivity(i);

        }
        return false;
    }
}

