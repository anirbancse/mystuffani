package com.example.arkac.myapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.preference.DialogPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class Make_appointment extends AppCompatActivity {

    EditText fname, lname, dateset, timeup;
    Button b;
    databasediary db1;
    String str[] = {"Select Specialist..","Audiologists", "Allergist", "Cardiologist", "Dentist", "Dermatologists"};
    String options[]={"Select Doctor Chamber..","Chamber_X","Chamber_Y","Chamber_P","Chamber_Q","Chamber_R","Chamber_S","Chamber_O","Chamber_L"};
  //  String tim[]={"8:0","10:0","10:30","10:45","11:0","11:45","12:0","13:0","14:0"};
    Spinner s,q,r,j;
    String m=null,x=null,z=null,p=null;
   // public String op= null;
    Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_appointment);

        fname = (EditText) findViewById(R.id.Fname);
        lname = (EditText) findViewById(R.id.Lname);
        timeup = (EditText) findViewById(R.id.time);

        b = (Button) findViewById(R.id.Save);
        db1 = new databasediary(this);

        s = (Spinner) findViewById(R.id.catagory);

        ArrayAdapter<String> ar = new ArrayAdapter<String>(Make_appointment.this, android.R.layout.simple_dropdown_item_1line, str);
        ar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(ar);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                m = s.getSelectedItem().toString();
                getdoc_name();
                //getdayzzz();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        r = (Spinner) findViewById(R.id.chamber);
        ArrayAdapter<String> ar1 = new ArrayAdapter<String>(Make_appointment.this, android.R.layout.simple_dropdown_item_1line, options);
        ar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        r.setAdapter(ar1);
        r.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                z = r.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        timeup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Make_appointment.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        timeup.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        dateset = (EditText) findViewById(R.id.date);
        dateset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Make_appointment.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
    }


    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    private void updateLabel() {

        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dateset.setText(sdf.format(myCalendar.getTime()));
    }

    public void getdoc_name() {

        q= (Spinner) findViewById(R.id.doctorname);

        if(!m.equals("Select Specialist..")) {
            Cursor c = db1.getdoctor_name(m);

            ArrayList<String> arrcurval=new ArrayList<String>();
            if (c.moveToFirst()) {
                do {
                    arrcurval.add(c.getString(1));
                } while (c.moveToNext());
            }

            ArrayAdapter<String> ar1= new ArrayAdapter<String>(Make_appointment.this,android.R.layout.simple_dropdown_item_1line,arrcurval);
            ar1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            q.setAdapter(ar1);

            q.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    x = q.getSelectedItem().toString();
                    //  doctor(m);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }

    }

/*    public void getdayzzz() {

        j= (Spinner) findViewById(R.id.days);

        if(!m.equals("Select Catagory..")) {
            Cursor c = db1.getdays(x);
            ArrayList<String> arrcurval=new ArrayList<String>();
            if (c.moveToFirst()) {
                do {
                    arrcurval.add(c.getString(1));

                } while (c.moveToNext());
            }

            ArrayAdapter<String> ar1= new ArrayAdapter<String>(Make_appointment.this,android.R.layout.simple_dropdown_item_1line,arrcurval);
            ar1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            j.setAdapter(ar1);

            j.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    p = j.getSelectedItem().toString();
                    //  doctor(m);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }

    }*/

    public void registered(View v){

        String firstname = fname.getText().toString();
        String lastname = lname.getText().toString();
        String date = dateset.getText().toString();
        String time = timeup.getText().toString();
        String clinic_name =z;
        String doc_catagory = m;
        String doc_name = x;
        //String daywq = p;

       if (firstname.equals("") || lastname.equals("") || date.equals("") || time.equals("") || clinic_name.equals("") || doc_catagory.equals("") || doc_name.equals("")) {
            Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();

        }
       else if(clinic_name.equals("Select Doctor Chamber..") || doc_catagory.equals("Select Specialist..")){
           Toast.makeText(getApplicationContext(),  "Option Not Selected", Toast.LENGTH_LONG).show();
       }
      /* else if(gettime(time)==false){
           Toast.makeText(getApplicationContext(),  "Doc not available", Toast.LENGTH_LONG).show();
       }*/
        else {
            db1.SaveAppointment(firstname, lastname, date, time, clinic_name,doc_catagory, doc_name);
            showMessage("SUCCESS", "Patient Information Saved");
            Intent i = new Intent(this,Patient_Home.class);
            startActivity(i);
        }
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

   /* public boolean gettime(String txt) {

        boolean t=false;
        for (int i = 0; i < tim.length; i++) {
            if (txt.equals(tim[i])){
                t = true;
            }else{
                t= false;
            }
        }
        return t;
    }*/



}




