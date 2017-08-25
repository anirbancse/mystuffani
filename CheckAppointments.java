package com.example.arkac.myapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
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

public class CheckAppointments extends AppCompatActivity {

    Button b;
    EditText e,f;
    databasediary d;
    Make_appointment m;
    Doctor_Home h;
    String message,name;
    Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_appointments);
        b = (Button) findViewById(R.id.check);
        e= (EditText) findViewById(R.id.date);
        f= (EditText) findViewById(R.id.ename);
        d= new databasediary(this);
        m= new Make_appointment();
        h= new Doctor_Home();
       /* Bundle bundle = getIntent().getExtras();
        message = bundle.getString("username");
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();*/

        e.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                new DatePickerDialog(CheckAppointments.this, date, myCalendar
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

        e.setText(sdf.format(myCalendar.getTime()));
    }


    public void display(View v){

        String f4 = e.getText().toString();
        String name = "Dr "+f.getText().toString();
        StringBuffer buffer1 = new StringBuffer();
        Cursor r = d.get_Appointments(f4, name);
        int m = r.getCount();
        if(m!=0){
            while (r.moveToNext()) {
                buffer1.append("Patient Name--> " + r.getString(1)+" "+ r.getString(2) + "\n");
                //buffer1.append("Appointment_Date: " + r.getString(5) + "\n");
                buffer1.append("Appointment_Time--> " + r.getString(6) + "\n");
                buffer1.append("Clinic_Name--> " + r.getString(7) + "\n\n");
            }
            showMessage(f4, buffer1.toString());
        }
        else{
            showMessage("ERROR", "No Data Found");
        }
    }


    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}

