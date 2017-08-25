package com.example.arkac.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Login extends AppCompatActivity {


    Button b1, b2;
    //ImageButton imageButton;
    CheckBox ch1, ch2, ch3;

    EditText logedit, passedit;
    databasediary d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        b1 = (Button) findViewById(R.id.log);
        b2 = (Button) findViewById(R.id.reg);
        ch1 = (CheckBox) findViewById(R.id.patient);
        ch2 = (CheckBox) findViewById(R.id.hospital);
        ch3 = (CheckBox) findViewById(R.id.doctor);
        logedit = (EditText) findViewById(R.id.editLogin);
       // imageButton= (ImageButton) findViewById(R.id.hlpBtn);
        passedit = (EditText) findViewById(R.id.editPass);
        d = new databasediary(this);

        /*imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Login.this, CardDemoActivity.class);
                startActivity(intent);
            }
        });*/
    }

    public void onCHECKED(View v) {

        switch (v.getId()) {

            case R.id.patient: {

                ch2.setChecked(false);
                ch3.setChecked(false);
                break;
            }
            case R.id.hospital: {

                ch1.setChecked(false);
                ch3.setChecked(false);
                break;
            }
            case R.id.doctor: {

                ch2.setChecked(false);
                ch1.setChecked(false);
                break;

            }
            default:
                Toast.makeText(this, " check your option ", Toast.LENGTH_LONG).show();
        }
    }


    public void Login(View v) {

        // Toast.makeText(this, " check 1 ", Toast.LENGTH_LONG).show();
        String txt1 = logedit.getText().toString();
        String txt2 = passedit.getText().toString();

        if(ch1.isChecked()) {

            Boolean che = d.checkPatient(txt1, txt2);

            if (che == true ) {
                Intent i = new Intent(this, Patient_Home.class);
                Toast.makeText(this, "login successfull", Toast.LENGTH_LONG).show();
                startActivity(i);
            }
            else if(che == false){

                Toast.makeText(this, "check your credentials", Toast.LENGTH_LONG).show();
            }
            else if (!ch1.isChecked()) {
                Toast.makeText(this, "please check your option", Toast.LENGTH_LONG).show();
            }
            else if (ch1.isChecked()) {
                Toast.makeText(this, "fill up the fields", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this, "require the fields", Toast.LENGTH_LONG).show();
            }
        }

        else if(ch3.isChecked()){

            Boolean che = d.checkdoctor(txt1, txt2);

            if (che == true ) {


                //Toast.makeText(this, m , Toast.LENGTH_LONG).show();
                Intent j= new Intent(this,CheckAppointments.class);
                j.putExtra("username", logedit.getText().toString());

                Intent i = new Intent(this, Doctor_Home.class);
                Toast.makeText(this, "login successfull", Toast.LENGTH_LONG).show();
               // startActivity(j);
                startActivity(i);

            }
            else if(che == false){

                Toast.makeText(this, "check your credentials", Toast.LENGTH_LONG).show();
            }
            else if (!ch1.isChecked()) {
                Toast.makeText(this, "please check your option", Toast.LENGTH_LONG).show();
            }
            else if (ch1.isChecked()) {
                Toast.makeText(this, "fill up the fields", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this, "require the fields", Toast.LENGTH_LONG).show();
            }


        }
        else if(ch2.isChecked()){

            Boolean che = d.checkhospital(txt1, txt2);

            if (che == true ) {
                Intent i = new Intent(this, CheckAppointments.class);
                Toast.makeText(this, "login successfull", Toast.LENGTH_LONG).show();
                startActivity(i);
            }
            else if(che == false){

                Toast.makeText(this, "check your credentials", Toast.LENGTH_LONG).show();
            }
            else if (!ch1.isChecked()) {
                Toast.makeText(this, "please check your option", Toast.LENGTH_LONG).show();
            }
            else if (ch1.isChecked()) {
                Toast.makeText(this, "fill up the fields", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this, "require the fields", Toast.LENGTH_LONG).show();
            }

        }
        else {
            Toast.makeText(this, "  check your option ", Toast.LENGTH_LONG).show();
        }

    }


    public void Register(View v) {

        if (ch1.isChecked()) {
            Intent i = new Intent(this, Patient_Reg.class);
            startActivity(i);
        } else if (ch3.isChecked()) {
            Intent i = new Intent(this, Doctor_Reg.class);
            startActivity(i);

        } else if (ch2.isChecked()) {
            Intent i = new Intent(this, Hospital_Reg.class);
            startActivity(i);

        } else {
            Toast.makeText(this, "  check your option ", Toast.LENGTH_LONG).show();
        }
    }

}
