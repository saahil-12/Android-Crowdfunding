package com.example.crowdfunding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edip;
    Button bconnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edip=findViewById(R.id.editTextTextPersonName);
        bconnect=findViewById(R.id.button);
        bconnect.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        String ip= edip.getText().toString();

        if(ip.length()==0){
            edip.setError("required");
            edip.requestFocus();

        }
        else{
            SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor ed=sh.edit();
            ed.putString("ip",ip);
            ed.commit();

            Intent i =new Intent(getApplicationContext(),login.class);
            startActivity(i);

        }
    }
}