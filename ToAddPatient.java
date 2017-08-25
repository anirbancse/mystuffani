package com.example.arkac.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ToAddPatient extends AppCompatActivity {

    EditText fname, lname, age, B_G, address, contact_no, mail_id;
    Button b;
    RadioButton r1,r2,r;
    RadioGroup RG;
    databasediary db1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to_add_patient);

        fname = (EditText) findViewById(R.id.f_name);
        lname = (EditText) findViewById(R.id.l_name);
        age = (EditText) findViewById(R.id.Age);
        address = (EditText) findViewById(R.id.address);
        contact_no = (EditText) findViewById(R.id.contact_number);
        mail_id = (EditText) findViewById(R.id.mail);
        B_G = (EditText) findViewById(R.id.BG);
        RG= (RadioGroup) findViewById(R.id.rg);
        b = (Button) findViewById(R.id.Save);
        r1= (RadioButton) findViewById(R.id.Male);
        r2= (RadioButton) findViewById(R.id.Female);
        db1 = new databasediary(this);

    }

    public void addPatient(View v) {

        int selectedId = RG.getCheckedRadioButtonId();
         r = (RadioButton) findViewById(selectedId);


        String firstname = fname.getText().toString();
        String lastname = lname.getText().toString();
        String ag = age.getText().toString();
        String blood_grp = B_G.getText().toString();
        String thikana = address.getText().toString();
        String contact = contact_no.getText().toString();
        String mail = mail_id.getText().toString();
        String option_selected = "";
        String regexStr = "^[0-9]{10}$";


        try {
            option_selected = r.getText().toString();
        }
        catch(Exception e){
            //showMessage("ERROR", "option not checked");
        }

        if (firstname.equals("") || lastname.equals("") || ag.equals("") || blood_grp.equals("") || thikana.equals("") || contact.equals("") || mail.equals("")) {
            Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
            return;
        }
        else if(!contact.matches(regexStr)){
            Toast.makeText(getApplicationContext(), "Check Number", Toast.LENGTH_LONG).show();
        }
        else if(Email_Validate(mail)==false) {
            Toast.makeText(getApplicationContext(), "Check your mail id", Toast.LENGTH_LONG).show();
        }
        /*else if(option_selected.equals("")) {
            showMessage("ERROR", "Enter Gender");
        }*/
        else {
             //option_selected = r.getText().toString();
            db1.SavePatientInfo(firstname, lastname, contact, thikana, mail, ag, blood_grp,option_selected);
            showMessage("SUCCESS", "Patient Information Saved");
            Intent i = new Intent(this,Patient_Medical_Condition.class);
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

    private boolean Email_Validate(String email)
    {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
