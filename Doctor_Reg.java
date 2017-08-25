package com.example.arkac.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Doctor_Reg extends AppCompatActivity {

    databasediary db2;
    EditText Nam , p_word, cnfp_word, days, contact_no, mail_id, uname , special;
    Button b;
    String str[] = {"Specialization..","Audiologists", "Allergist", "Cardiologist", "Dentist", "Dermatologists"};
    String str1[] = {"Enter Days..","Monday", "Tuesday", "Wednesday", "Thirsday", "Friday","Saturday","Sunday"};
    Spinner s;
    String m="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor__reg);

        Nam = (EditText) findViewById(R.id.name);
        p_word = (EditText) findViewById(R.id.pass_word);
        cnfp_word = (EditText) findViewById(R.id.cnfrm_p);
        days = (EditText) findViewById(R.id.address);
        contact_no = (EditText) findViewById(R.id.contact_number);
        mail_id = (EditText) findViewById(R.id.mail);
        uname = (EditText) findViewById(R.id.u_name);
       // special= (EditText) findViewById(R.id.cata);
        b = (Button) findViewById(R.id.dsubmit);
        db2 = new databasediary(this);

        s = (Spinner) findViewById(R.id.po);

        ArrayAdapter<String> ar = new ArrayAdapter<String>(Doctor_Reg.this, android.R.layout.simple_dropdown_item_1line, str);
        ar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(ar);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                m = s.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void addDoctor(View v) {

        String name = "Dr "+Nam.getText().toString();
        //String reg_no = register.getText().toString();
        String pass = p_word.getText().toString();
        //String speciality = special.getText().toString();
        String speciality =m;
        String cnfrmpass = cnfp_word.getText().toString();
        String contact = contact_no.getText().toString();
        String mail = mail_id.getText().toString();
        String unam = uname.getText().toString();
        int p1=pass.length();
        int p2=cnfrmpass.length();
        String dayzz = days.getText().toString() ;
        String regexStr = "^[0-9]{10}$";
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}";

        if(name.equals("")||pass.equals("")|| speciality.equals("") ||cnfrmpass.equals("")||dayzz.equals("")||contact.equals("")||mail.equals("")||unam.equals(""))
        {
            Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
            return;
        }
        if(!pass.equals(cnfrmpass))
        {
            Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
            return;
        }
        else if (p1<8 && p2<8) {
            Toast.makeText(getApplicationContext(), "Password must be min of 8 characters", Toast.LENGTH_LONG).show();
            return;
        }
        else if(speciality.equals("Specialization..")){
            Toast.makeText(getApplicationContext(),  "Option Not Selected", Toast.LENGTH_LONG).show();
        }
        else if(!contact.matches(regexStr)){
            Toast.makeText(getApplicationContext(), "Check Number", Toast.LENGTH_LONG).show();
        }
        else if (pass.matches(pattern)){
            Toast.makeText(getApplicationContext(), "Password must contain atleast one number,one uppercase,one lowercase" +
                    "and one number ", Toast.LENGTH_LONG).show();
            return;
        }
        else if(Email_Validate(mail)==false) {
            Toast.makeText(getApplicationContext(), "check your mail please", Toast.LENGTH_LONG).show();
        }
        else
        {
            db2.InsertDoctorData(name,contact,speciality,dayzz, pass, unam, cnfrmpass, mail);
            Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, Doctor_Home.class);
            startActivity(i);
        }
    }

    /*public void view_doc(View v){

        String ms = db2.viewdoctordata();
        Toast.makeText(getApplicationContext(),ms, Toast.LENGTH_LONG).show();
    }
*/
    private boolean Email_Validate(String email)
    {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
