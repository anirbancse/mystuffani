package com.example.arkac.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Patient_Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient__home);

        ImageButton b= (ImageButton) findViewById(R.id.imageButton);
        ImageButton bp = (ImageButton) findViewById(R.id.imageButton3);

        bp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(),MakeCall.class);
                startActivity(i);

            }
        });
    }

    public void nxtwindow(View v){

        Intent i = new Intent(this,Make_appointment.class);
        startActivity(i);
    }
}
