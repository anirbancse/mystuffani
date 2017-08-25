package com.example.arkac.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Doctor_Home extends AppCompatActivity  {

    Button b1,b2,b3;
    CheckAppointments ch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor__home);

        b1= (Button) findViewById(R.id.AddPatient);
        b2= (Button) findViewById(R.id.SearchByName);
        b3= (Button) findViewById(R.id.SearchByDate);
       /* Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("username");
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();*/

    }

    public void addPatient(View v){
        Intent i = new Intent(this,ToAddPatient.class);
        startActivity(i);
    }

    public void search_name(View v){
        Intent i = new Intent(this,search_by_name.class);
        startActivity(i);

    }
    public void search_date(View v){
        Intent i = new Intent(this,search_by_date.class);
        startActivity(i);

    }

    public void open_history(View v){
        Intent i = new Intent(this,CheckAppointments.class);
        startActivity(i);


    }


}
