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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class search_by_date extends AppCompatActivity {

    Button b;
    EditText e;
    databasediary d;
    Calendar myCalendar = Calendar.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_by_date);
        b = (Button) findViewById(R.id.button5);
        e= (EditText) findViewById(R.id.date);
        d= new databasediary(this);
        e.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                new DatePickerDialog(search_by_date.this, date, myCalendar
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

    public void check(View v){

        String f4 = e.getText().toString();
        StringBuffer buffer1 = new StringBuffer();
        Cursor r = d.getdata_by_date(f4);
        int m = r.getCount();
        if(m!=0){
            while (r.moveToNext()) {
                buffer1.append("Name: " + r.getString(0) + r.getString(1) + "\n");
                buffer1.append("Mail: " + r.getString(4) + "\n");
                buffer1.append("Condition: " + r.getString(10) + "\n");
                buffer1.append("Medication: " + r.getString(11) + "\n\n");
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

