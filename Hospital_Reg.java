package com.example.arkac.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class Hospital_Reg extends AppCompatActivity {

    databasediary db1;
    EditText Nam, id, p_word, cnfp_word, addr, contact_no, mail_id, uname  ;
    Button b1,b2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospital__reg);

        Nam = (EditText) findViewById(R.id.hname);
       // id = (EditText) findViewById(R.id.h_id);
        p_word = (EditText) findViewById(R.id.pass_word);
        cnfp_word = (EditText) findViewById(R.id.cnfrm_p);
        addr = (EditText) findViewById(R.id.address);
        contact_no = (EditText) findViewById(R.id.contact_number);
        //mail_id = (EditText) findViewById(R.id.mail);
        uname = (EditText) findViewById(R.id.u_name);
        b1 = (Button) findViewById(R.id.button3);
        //b2 = (Button) findViewById(R.id.button);

        db1 = new databasediary(this);
    }
    public void addHospital(View v) {

        String hospitalname = Nam.getText().toString();
      //  String idno = id.getText().toString();
        String pass = p_word.getText().toString();
        String cnfrmpass = cnfp_word.getText().toString();
        String thikana = addr.getText().toString();
        String contact = contact_no.getText().toString();
        String unam = uname.getText().toString();
       // String mail = mail_id.getText().toString();
        int p1=pass.length();
        int p2=cnfrmpass.length();

        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}";
        String regexStr = "^[0-9]{10}$";


        if (hospitalname.equals("")||pass.equals("") || cnfrmpass.equals("") || thikana.equals("") || contact.equals("") || unam.equals("")) {
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
        else {
            db1.InserthospitalData(hospitalname, contact, thikana, pass, unam, cnfrmpass);
            Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, CheckAppointments.class);
            startActivity(i);

        }
    }


    public void details(View v){

        String ms = db1.viewhospitaldata();
        Toast.makeText(getApplicationContext(), ms, Toast.LENGTH_LONG).show();
    }




}
