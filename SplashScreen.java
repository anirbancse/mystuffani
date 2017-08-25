package com.example.arkac.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash_screen);

        Thread t= new Thread(){
            @Override
            public  void run(){

                try{
                    sleep(4000);
                    Intent i =new Intent(getApplicationContext(),Login.class);
                    startActivity(i);
                    finish();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }
}

