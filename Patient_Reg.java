package com.example.arkac.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Patient_Reg extends AppCompatActivity {

    databasediary db1;
    EditText fname, lname, p_word, cnfp_word, address, contact_no, mail_id, uname;
    Button b,a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient__reg);
        fname = (EditText) findViewById(R.id.f_name);
        lname = (EditText) findViewById(R.id.l_name);
        p_word = (EditText) findViewById(R.id.pass_word);
        cnfp_word = (EditText) findViewById(R.id.cnfrm_p);
       // address = (EditText) findViewById(R.id.address);
        contact_no = (EditText) findViewById(R.id.contact_number);
        mail_id = (EditText) findViewById(R.id.mail);
        uname = (EditText) findViewById(R.id.u_name);
        b = (Button) findViewById(R.id.button3);


        db1 = new databasediary(this);
    }

    public void addUser(View v) {

        String firstname = fname.getText().toString();
        String lastname = lname.getText().toString();
        String pass = p_word.getText().toString();
        String cnfrmpass = cnfp_word.getText().toString();
       // String thikana = address.getText().toString();
        String contact = contact_no.getText().toString();
        String mail = mail_id.getText().toString();
        String unam = uname.getText().toString();
        int p1=pass.length();
        int p2=cnfrmpass.length();
        String regexStr = "^[0-9]{10}$";
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}";
        int  passcheck= pass.length();
        //String regexStr = "\+\d(-\d{3}){2}-\d{4}";
        //Pattern pattern = Pattern.compile("\\d{3}-\\d{7}");
        //Matcher matcher = pattern.matcher(contact);

        if (firstname.equals("") || lastname.equals("") || pass.equals("") || cnfrmpass.equals("") || contact.equals("") || mail.equals("") || unam.equals("")) {
            Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
            return;
        }
        else if (!pass.equals(cnfrmpass)) {
            Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
            return;
        }
        else if (p1<8 && p2<8) {
            Toast.makeText(getApplicationContext(), "Password must be min of 8 characters", Toast.LENGTH_LONG).show();
            return;
        }
        else if (pass.matches(pattern)){
            Toast.makeText(getApplicationContext(), "Password must contain atleast one number,one uppercase,one lowercase" +
                    "and one number ", Toast.LENGTH_LONG).show();
            return;
        }
        else if(!contact.matches(regexStr)){
            Toast.makeText(getApplicationContext(), "Check Number", Toast.LENGTH_LONG).show();
        }
        else if(Email_Validate(mail)==false) {
            Toast.makeText(getApplicationContext(), "check your mail please", Toast.LENGTH_LONG).show();
        }
        else {
            db1.InsertPatientData(firstname, lastname, contact, pass, unam, cnfrmpass, mail);
            Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, Patient_Home.class);
            startActivity(i);
        }
    }


   /* public void details(View v){

        String ms = db1.viewpatientdata();
        Toast.makeText(getApplicationContext(),ms, Toast.LENGTH_LONG).show();
    }*/

    private boolean Email_Validate(String email)
    {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
