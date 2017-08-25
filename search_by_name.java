package com.example.arkac.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class search_by_name extends AppCompatActivity {

    EditText e1,e2;
    Button b1,b2;

    databasediary d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_by_name);

        e1= (EditText) findViewById(R.id.Fname);
        e2= (EditText) findViewById(R.id.Lname);
        b1= (Button) findViewById(R.id.save);
        b2= (Button) findViewById(R.id.entry);


        d= new databasediary(this);
    }

    public void Search(View v){


        String firstname = e1.getText().toString();
        String lastname = e2.getText().toString();
        Cursor res=null,res1=null;


        if(firstname.equals("")&& lastname.equals(""))
        {
            showMessage("Error","Field Vaccant");
        }
        else if(lastname.equals("")) {
            showMessage("Error","Enter LastName");
        }
        else if(firstname.equals("")) {
            showMessage("Error","Enter FirstName");
        }
        else {
            Boolean b = d.validatefields(firstname, lastname);

            if (b == true) {

                StringBuffer buffer1 = new StringBuffer();
                StringBuffer buffer2 = new StringBuffer();
                res1 = d.getAllData(firstname, lastname);//join table
                res = d.getAllData1(firstname, lastname);//patient details

                while (res1.moveToNext()) {
                    buffer1.append("Gender: " + res1.getString(3) + "\n");
                    buffer1.append("Mail: " + res1.getString(4) + "\n");
                    buffer1.append("BloodGroup: " + res1.getString(5) + "\n");
                    buffer1.append("Age: " + res1.getString(8) + "\n\n");
                   // buffer1.append("-------------------------------");
                }
                while (res.moveToNext()) {
                    //buffer2.append("Mail: " + res.getString(4) + "\n");
                    buffer2.append("Condition: " + res.getString(10) + "\n");
                    buffer2.append("Medication: " + res.getString(11) + "\n");
                    buffer2.append("Date: " + res.getString(12) + "\n");
                    buffer2.append("Note " + res.getString(13) + "\n\n");
                    //buffer1.append("--------------------------------");
                }
                showMessage(firstname+ " " + lastname,buffer1.toString()+ buffer2.toString());
            }
                else{
                    showMessage("Error", "No Data Found");
                }
            }
        }



    public void reg(View v){

        Intent i = new Intent(this, Patient_Medical_Condition.class);
        startActivity(i);

    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
