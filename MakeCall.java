package com.example.arkac.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MakeCall extends Activity {

    ListView lv;
    Context context;
    String message="";
    public static String[] DocNameList = {"Dr Ashok batra", "Dr Mishra", "Dr Sen", "Dr Chowdhury", "Dr Bose","Dr Ghosh","Dr PK BOSE","Dr A Mishra"
            ,"Dr P K Sanyal","Dr Ashok Kumar","Dr A K Banerjee","Dr Abhishek Bose"};
    public static String[] Specialist = {"Dentist","Cardiologist", "Neurologist","Allergist","Plastic Surgeon","Allergist","Dermetologist","Endocrinologist","Immunologist","Microbiologist","Neurologist","Cardiologist"};
    public static String[] phoneNumber = {"9836366645", "8504992251", "7452122224", "8741254789", "9874561238","7896541235","45212154211","561212123","564654","56564654","6564623223","5465123544"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_call);

        context = this;
        lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(new CustomAdapter(this, DocNameList, Specialist,phoneNumber));

    }

    public void lp(View v) {

        String p= v.getTag().toString();
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+p));
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(callIntent);
    }

    public void ms(View v) {

        String p= v.getTag().toString();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + p));
        intent.putExtra("", message);
        startActivity(intent);
    }
}
