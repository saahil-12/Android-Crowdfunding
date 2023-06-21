package com.example.crowdfunding;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class signup extends AppCompatActivity implements View.OnClickListener {
    ImageView profile;
    EditText edname,edmail,edcontact,edplace,edpost,edpin,eddistrict,edpass,edconfirm;
    RadioButton male,female,other;
    Button register;
    String path, atype, fname, attach, attatch1;
    byte[] byteArray = null;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edname=findViewById(R.id.editTextTextPersonName17);
        profile=findViewById(R.id.imageView2);
        edmail=findViewById(R.id.editTextTextPersonName18);
        male=findViewById(R.id.radioButton4);
        female=findViewById(R.id.radioButton14);
        other=findViewById(R.id.radioButton15);
        edcontact=findViewById(R.id.editTextTextPersonName19);
        edplace=findViewById(R.id.editTextTextPersonName20);
        edpost=findViewById(R.id.editTextTextPersonName22);
        edpin=findViewById(R.id.editTextTextPersonName23);
        eddistrict=findViewById(R.id.editTextTextPersonName21);
        register=findViewById(R.id.button6);
        register.setOnClickListener(this);
        edpass=findViewById(R.id.editTextTextPersonName15);
        edconfirm=findViewById(R.id.editTextTextPersonName16);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showfilechooser(1);
            }
        });


        male.setChecked(true);
    }
    String gender="";
    @Override
    public void onClick(View view) {
        String name= edname.getText().toString();
        String email= edmail.getText().toString();
        String contact= edcontact.getText().toString();
        String place= edplace.getText().toString();
        String post= edpost.getText().toString();
        String district= eddistrict.getText().toString();
        String pin= edpin.getText().toString();
        String pass=edpass.getText().toString();
        String confirmpass=edconfirm.getText().toString();

        if(male.isChecked()){
            gender=male.getText().toString();
        }
        if(female.isChecked()){
            gender=female.getText().toString();
        }
        if(other.isChecked()){
            gender=other.getText().toString();
        }


        if(name.length()==0){
            edname.setError("required");
            edname.requestFocus();
        }
        else if(email.length()==0){
            edmail.setError("required");
            edmail.requestFocus();
        }
        else if(contact.length()==0){
            edcontact.setError("required");
            edcontact.requestFocus();

        }
        else if(place.length()==0){
            edplace.setError("required");
            edplace.requestFocus();

        }
        else if(post.length()==0){
            edpost.setError("required");
            edpost.requestFocus();


        }
        else if(district.length()==0){
            eddistrict.setError("required");
            eddistrict.requestFocus();

        }
        else if(pin.length()==0){
            edpin.setError("required");
            edpin.requestFocus();
        }
        else{
            SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            final String maclis=sh.getString("mac_list","");
            String uid=sh.getString("uid","");
            String hu = sh.getString("ip", "");
            String url = "http://" + hu + ":5000/and_user_signup";



            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                             Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                            // response
                            try {
                                JSONObject jsonObj = new JSONObject(response);
                                if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                    Toast.makeText(signup.this, "Account Created", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(),login.class));

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
                    params.put("User_Name",name);
                    params.put("Mail_Id",email);
                    params.put("Contact",contact);
                    params.put("Place",place);
                    params.put("Post",post);
                    params.put("District",district);
                    params.put("Pin",pin);
                    params.put("Photo",attach);
                    params.put("Gender",gender);
                    params.put("Password",pass);

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

    void showfilechooser(int string) {
        // TODO Auto-generated method stub
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //getting all types of files

        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), string);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(getApplicationContext(), "Please install a File Manager.", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                ////
                Uri uri = data.getData();

                try {
                    path = FileUtils.getPath(this, uri);

                    File fil = new File(path);
                    float fln = (float) (fil.length() / 1024);
                    atype = path.substring(path.lastIndexOf(".") + 1);


                    fname = path.substring(path.lastIndexOf("/") + 1);
//                    ed15.setText(fname);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }

                try {

                    File imgFile = new File(path);

                    if (imgFile.exists()) {

                        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                        profile.setImageBitmap(myBitmap);

                    }


                    File file = new File(path);
                    byte[] b = new byte[8192];
                    Log.d("bytes read", "bytes read");

                    InputStream inputStream = new FileInputStream(file);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();

                    int bytesRead = 0;

                    while ((bytesRead = inputStream.read(b)) != -1) {
                        bos.write(b, 0, bytesRead);
                    }
                    byteArray = bos.toByteArray();

                    String str = Base64.encodeToString(byteArray, Base64.NO_WRAP);
                    attach = str;


                } catch (Exception e) {
                    Toast.makeText(this, "String :" + e.getMessage().toString(), Toast.LENGTH_LONG).show();
                }

                ///

            }
        }

    }
}
