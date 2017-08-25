package com.example.arkac.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.sql.ResultSet;

public class databasediary extends AppCompatActivity {

    database dbh;
    Context context;

    public databasediary(Context context){

        dbh = new database(context);
        this.context= context;

    }

    public void InsertPatientData(String txt1,String txt2,String txt3,String txt4,String txt5,String txt6,String txt7) {



        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(database.FNAME,txt1);
        contentValues.put(database.LNAME, txt2);
        contentValues.put(database.CONTACT_NO,txt3);
        contentValues.put(database.PASSWORD,txt4);
        contentValues.put(database.USERNAME,txt5);
        contentValues.put(database.CNFRM_PASS, txt6);
        contentValues.put(database.MAIL_ID, txt7);
        db.insert(dbh.tb1, null, contentValues);
        db.close();
    }

    public void InsertDoctorData(String txt1,String txt2,String txt3,String txt4,String txt5,String txt6,String txt7,String txt8) {


        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(database.NAME,txt1);
        contentValues.put(database.DAYS,txt4);
        contentValues.put(database.PASSWORD,txt5);
        contentValues.put(database.CNFRM_PASS, txt7);
        contentValues.put(database.SPECIAL,txt3);
        contentValues.put(database.CONTACT_NO,txt2);
        //contentValues.put(database.ADDRESS, txt5);
        contentValues.put(database.MAIL_ID, txt8);
        contentValues.put(database.USERNAME, txt6);
        db.insert(dbh.tb2, null, contentValues);
        db.close();
    }

    public void Medical_Condition(String txt1,String txt2,String txt3,String txt4,String txt5){


        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(database.Condition,txt1);
        contentValues.put(database.Medication,txt2);
        contentValues.put(database.Note, txt3);
        contentValues.put(database.DATE, txt4);
        contentValues.put(database.MAIL_ID, txt5);
        db.insert(dbh.tb5, null, contentValues);
        db.close();

    }

    public void InserthospitalData(String txt1,String txt2,String txt3,String txt4,String txt5,String txt6){

        //toast(context,"inside hospital");
        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(database.Hospital_name,txt1);
        contentValues.put(database.CNFRM_PASS,txt6);
        contentValues.put(database.ADDRESS, txt3);
        contentValues.put(database.CONTACT_NO,txt2);
        contentValues.put(database.PASSWORD, txt4);
        contentValues.put(database.USERNAME, txt5);
        db.insert(dbh.tb3, null, contentValues);
        db.close();
    }

    public void SavePatientInfo(String txt1,String txt2,String txt3,String txt4,String txt5,String txt6,String txt7,String txt8){

        //toast(context,"PATIENT_INFO");
        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(database.FNAME,txt1);
        contentValues.put(database.LNAME,txt2);
        contentValues.put(database.BLOODGROUP,txt7);
        contentValues.put(database.ADDRESS,txt4);
        contentValues.put(database.CONTACT_NO,txt3);
        contentValues.put(database.AGE, txt6);
        contentValues.put(database.MAIL_ID, txt5);
        contentValues.put(database.GENDER, txt8);
        db.insert(dbh.tb4, null, contentValues);
        db.close();
    }

    public void SaveAppointment(String txt1,String txt2,String txt3,String txt4,String txt5,String txt6,String txt7){

        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(database.FNAME,txt1);
        contentValues.put(database.LNAME,txt2);
        contentValues.put(database.DATE,txt3);
        contentValues.put(database.Time,txt4);
        contentValues.put(database.Doctor_Name,txt7);
        contentValues.put(database.Catagory, txt6);
        contentValues.put(database.Clinic_Name, txt5);
        db.insert(dbh.tb6, null, contentValues);
        db.close();
    }
    public String viewpatientdata(){

        //toast(context,"fetching data");
        SQLiteDatabase db= dbh.getWritableDatabase();
        String[] columns={database.UID,database.FNAME,database.LNAME,database.PASSWORD,database.USERNAME};
        Cursor cursor  = db.query(database.tb1, columns,null,null,null,null,null);

        StringBuffer br= new StringBuffer();
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String fname = cursor.getString(1);
            String lname =cursor.getString(2);
            String pass=cursor.getString(3);
            String userid=cursor.getString(4);


            br.append(pass+"  "+userid+" "+ "\n");
        }

        return br.toString();
    }

    public String viewdoctordata(){

       // toast(context,"fetching data");
        SQLiteDatabase db= dbh.getWritableDatabase();
        String[] columns={database.UID,database.NAME,database.REG,database.USERNAME,database.PASSWORD};
        Cursor cursor  = db.query(database.tb2, columns, null, null, null, null, null);

        StringBuffer br= new StringBuffer();
        while(cursor.moveToNext()){

            int id = cursor.getInt(0);
            String Name = cursor.getString(1);
            String Register =cursor.getString(2);
            String pass=cursor.getString(4);
            String userid=cursor.getString(3);


            br.append(pass+"  "+userid+" "+ "\n");
        }

        return br.toString();
    }

    public String viewhospitaldata(){


        SQLiteDatabase db= dbh.getWritableDatabase();
        String[] columns={database.Hospital_id,database.Hospital_name,database.USERNAME,database.PASSWORD};
        Cursor cursor  = db.query(database.tb3, columns,null,null,null,null,null);

        StringBuffer br= new StringBuffer();
        while(cursor.moveToNext()){

            int id = cursor.getInt(0);
            String Name = cursor.getString(1);
            String pass=cursor.getString(3);
            String userid=cursor.getString(2);

            br.append(id+"  "+Name+"  "+pass+"  "+userid+" "+ "\n");
        }

        return br.toString();
    }


    public boolean validatefields(String txt1, String txt2) {

        String[] columns = {database.UID};
        SQLiteDatabase db = dbh.getReadableDatabase();
        String selection = database.FNAME + " = ?" + " AND " + database.LNAME + " = ?";

        String[] selectionArgs = {txt1, txt2};
        Cursor cursor = db.query(database.tb4,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }


    public Cursor getAllData(String FNAME,String LNAME) {//searchbyname

        SQLiteDatabase db = dbh.getReadableDatabase();
        String s1 = "select * from patient_details_table where FirstName= '"+FNAME+"' and LastName='"+LNAME+"'";
        Cursor resl = db.rawQuery(s1, null);
        return resl;
    }

   public Cursor getAllData1(String FNAME,String LNAME ) {

        SQLiteDatabase db = dbh.getReadableDatabase();
        String s1 = "select * from(select * from Patient_Details_table e, Patient_Condition_table p " +
       "where e.mail_id = p.mail_id) where firstname='"+FNAME+"'"+"and lastname='"+LNAME+"'";
        Cursor resl = db.rawQuery(s1, null);
        return resl;
    }

    public Cursor getdata_by_date(String date){//searchbydate
        SQLiteDatabase db = dbh.getReadableDatabase();
        String s1 = "select * from(select * from Patient_Details_table e, Patient_Condition_table p " +
                "where e.mail_id = p.mail_id)where date= '"+date+"'";

        Cursor resl = db.rawQuery(s1, null);
        return resl;
    }

    public Cursor get_Appointments(String date,String name){//getAppointmnets
        SQLiteDatabase db = dbh.getReadableDatabase();
        String s1 = "select * from "+database.tb6+" where date= '"+date+"' and doctor_name= '"+name+"'";
        Cursor resl = db.rawQuery(s1, null);
        return resl;
    }

    public Cursor getdoctor(String catagory){//searchbydate
        SQLiteDatabase db = dbh.getReadableDatabase();
        String s1 = "select * from(select * from Patient_Details_table e, Patient_Condition_table p " +
                "where e.mail_id = p.mail_id)where SPECIAL= '"+catagory+"'";

        Cursor resl = db.rawQuery(s1, null);
        return resl;
    }

    public Cursor getdoctor_name(String catagory){//doctor_name

        //toast(context,"getting");
        SQLiteDatabase db = dbh.getReadableDatabase();
        String s1 = "select * from Doctor_Table where "+ database.SPECIAL+"= '"+catagory+"'";
        Cursor resl = db.rawQuery(s1, null);

        return resl;
    }


    public Cursor getdoc_name(String catagory){//doctor_name

        SQLiteDatabase db = dbh.getReadableDatabase();
        String s1 = "select * from Doctor_Table where "+ database.USERNAME+"= '"+catagory+"'";
        Cursor resl = db.rawQuery(s1, null);

        return resl;
    }

    public boolean checkdoctor(String uname, String password) {


        String[] columns = {database.UID};
        SQLiteDatabase db = dbh.getReadableDatabase();
        String selection = database.USERNAME + " = ?" + " AND " + database.PASSWORD + " = ?";


        String[] selectionArgs = {uname, password};
        Cursor cursor = db.query(database.tb2,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    public boolean checkhospital(String uname, String password) {


        String[] columns = {database.Hospital_id};
        SQLiteDatabase db = dbh.getReadableDatabase();
        String selection = database.USERNAME + " = ?" + " AND " + database.PASSWORD + " = ?";


        String[] selectionArgs = {uname, password};
        Cursor cursor = db.query(database.tb3,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }



    public boolean checkPatient(String uname, String password) {


        String[] columns = {database.UID};
        SQLiteDatabase db = dbh.getReadableDatabase();
        String selection = database.USERNAME + " = ?" + " AND " + database.PASSWORD + " = ?";


        String[] selectionArgs = {uname, password};
        Cursor cursor = db.query(database.tb1,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }


    static class database extends SQLiteOpenHelper {

        private static final String db_name = "alldata";
        private static final String tb1 = "Patient_Reg_table";
        private static final String tb2 = "Doctor_Table";
        private static final String tb3 = "Hospital_Table";
        private static final String tb4 = "Patient_Details_table";
        private static final String tb5 = "Patient_Condition_table";
        private static final String tb6 = "Patient_Appointment_table";
        private static final int db_version = 44;
        private static final String UID = "Id";
        private static final String BLOODGROUP = "Blood_Group";
        private static final String AGE = "Age";
        private static final String GENDER = "Gender";
        private static final String LNAME = "LastName";
        private static final String FNAME = "FirstName";
        private static final String CONTACT_NO = "Contact_no";
        private static final String ADDRESS = "Address";
        private static final String USERNAME = "Username";
        private static final String Clinic_Name = "Clinic_Name";
        private static final String Time = "Appointment_Time";
        private static final String MAIL_ID = "Mail_Id";
        private static final String PASSWORD = "Password";
        private static final String CNFRM_PASS = "CnfPassword";
        private static final String SPECIAL = "Specialization";
        private static final String DAYS = "Days";
        private static final String NAME= "Name";
        private static final String REG= "Register_no";
        private static final String Hospital_name= "Hospital_name";
        private static final String Hospital_id = "Hospital_id";
        private static final String Condition = "Patient_Condition";
        private static final String Medication = "Medical_Condition";
        private static final String Note = "Medical_Note";
        private static final String DATE = "Date";
        private static final String Catagory = "Catagory";
        private static final String Doctor_Name = "Doctor_Name";


        private static final String patient_reg_table = "CREATE TABLE " +tb1+ "(" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + FNAME + " VARCHAR(255) ,"
                + LNAME + " VARCHAR(255) ," + PASSWORD + " VARCHAR(25) ,"
                + MAIL_ID + " VARCHAR(100) ," + USERNAME + " VARCHAR(200) ," + CONTACT_NO + " INTEGER(20) ,"
                + CNFRM_PASS + " VARCHAR(255));";

        private static final String patient_condition_table = "CREATE TABLE " +tb5+ "(" +MAIL_ID + " VARCHAR(100),"+ Condition + " VARCHAR(255) ,"
                + Medication + " VARCHAR(255) ,"+ DATE + " VARCHAR(100) ,"+ Note + " VARCHAR(25));";

        private static final String appointment_table = "CREATE TABLE " +tb6+ "(" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +FNAME + " VARCHAR(100),"+ LNAME + " VARCHAR(255) ,"
                + Catagory + " VARCHAR(255) ,"+ Doctor_Name + " VARCHAR(100) ,"+ DATE + " VARCHAR(25) ,"+ Time + " VARCHAR(25),"+ Clinic_Name + " VARCHAR(25));";


        private static final String patient_details_table = "CREATE TABLE " +tb4+ "(" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"+ FNAME + " VARCHAR(255) ,"
                + LNAME + " VARCHAR(255) ," + GENDER + " VARCHAR(25) ,"
                + MAIL_ID + " VARCHAR(100) ,"+ BLOODGROUP + " VARCHAR(200) ," + CONTACT_NO + " INTEGER(20) ,"
                + ADDRESS + " VARCHAR(255) ,"+ AGE +" INTEGER(20));";

        private static final String doctor_reg_table = "CREATE TABLE " +tb2+ "(" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + NAME + " VARCHAR(255) ,"
                + PASSWORD + " VARCHAR(25) ,"
                + MAIL_ID + " VARCHAR(100) ," + USERNAME + " VARCHAR(200) ," + CONTACT_NO + " INTEGER(20) ,"
                + DAYS + " VARCHAR(255) ,"+ CNFRM_PASS + " VARCHAR(255),"+ SPECIAL +" VARCHAR(30)); ";

        private static final String hospital_reg_table = "CREATE TABLE " +tb3+ "(" + Hospital_id + " INTEGER PRIMARY KEY ," + Hospital_name + " VARCHAR(255) ," + PASSWORD + " VARCHAR(25) ,"
                + USERNAME + " VARCHAR(200) ," + CONTACT_NO + " INTEGER(20) ,"
                + ADDRESS + " VARCHAR(255) ,"+ CNFRM_PASS + " VARCHAR(255));";

        /* private static final String newDoc =  "INSERT INTO "+ tb1 +"("+name+","+REG+","+PASSWORD+","+mail_id+","+USERNAME+","+CONTACT_NO+","+ADDRESS+","+CNFRM_PASS+","+
                +) VALUES('this is','03/04/2005','5000','tran','y')"*/


        private static final String DROP_PTABLE = "DROP TABLE IF EXISTS " + tb1;
        private static final String DROP_DTABLE = "DROP TABLE IF EXISTS " + tb2;
        private static final String DROP_HTABLE = "DROP TABLE IF EXISTS " + tb3;
        private static final String DROP_PITABLE = "DROP TABLE IF EXISTS " + tb4;
        private static final String DROP_PCTABLE = "DROP TABLE IF EXISTS " + tb5;
        private static final String DROP_ATABLE = "DROP TABLE IF EXISTS " + tb6;
       // private static final String DROP_CTABLE = "DROP TABLE IF EXISTS " + tb6;
        private Context context;


        public database(Context context) {
            super(context, db_name, null, db_version);
            this.context = context;

        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            try {
                toast(context, "OnCreate called");
                db.execSQL(patient_reg_table);
                db.execSQL(patient_details_table);
                db.execSQL(doctor_reg_table);
                db.execSQL(hospital_reg_table);
                db.execSQL(patient_condition_table);
                db.execSQL(appointment_table);

            } catch (SQLException e) {
                toast(context, "" + e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            try {

                db.execSQL(DROP_PTABLE);
                db.execSQL(DROP_DTABLE);
                db.execSQL(DROP_HTABLE);
                db.execSQL(DROP_PITABLE);
                db.execSQL(DROP_PCTABLE);
                db.execSQL(DROP_ATABLE);
                onCreate(db);
            } catch (SQLException e) {
                toast(context, "" + e);
            }
        }
    }

    public static void toast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
