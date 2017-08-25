package com.example.arkac.myapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class Patient_Medical_Condition extends AppCompatActivity {

    EditText f1,f2,f3,f4,f5;
    Button b,b1;
    databasediary d;
    Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient__medical__condition);

        b = (Button) findViewById(R.id.s1);
        f1= (EditText) findViewById(R.id.e1);
        f2= (EditText) findViewById(R.id.e2);
        f3= (EditText) findViewById(R.id.e3);
        f4= (EditText) findViewById(R.id.e4);
        f5= (EditText) findViewById(R.id.e5);
        d = new databasediary(this);
        f4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                new DatePickerDialog(Patient_Medical_Condition.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    private void updateLabel() {

        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        f4.setText(sdf.format(myCalendar.getTime()));
    }

    public void patient_condi(View v){
        String p1 = f1.getText().toString();
        String p2 = f2.getText().toString();
        String p3 = f3.getText().toString();
        String p4 = f4.getText().toString();
        String p5 = f5.getText().toString();

        if(p1.equals("")||p2.equals("")||p3.equals("")||p4.equals("")||p5.equals(""))
        {
            showMessage("Error","Field Vaccant");
            return;
        }
        else if(Email_Validate(p5)==false) {
            Toast.makeText(getApplicationContext(), "check your mail please", Toast.LENGTH_LONG).show();
        }
        else
        {
            d.Medical_Condition(p1,p2,p3,p4,p5);
            showMessage("Success","Data Inserted");
            Intent i = new Intent(this,Doctor_Home.class);
            startActivity(i);
        }
    }

    private boolean Email_Validate(String email)
    {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
