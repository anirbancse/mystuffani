package com.example.arkac.mplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import java.io.File;
import java.util.ArrayList;

public class player extends AppCompatActivity implements View.OnClickListener {

    ArrayList<File> songsList;
    SeekBar sb;
    static MediaPlayer mp;
    Button btplay, btff, btfb, btnxt, btprv;
    int position;
    Uri u;
    Thread updateSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        sb = (SeekBar) findViewById(R.id.seekBar);
        btplay = (Button) findViewById(R.id.btplay);
        btff = (Button) findViewById(R.id.btff);
        btfb = (Button) findViewById(R.id.btfb);
        btnxt = (Button) findViewById(R.id.btnxt);
        btprv = (Button) findViewById(R.id.btprev);

        btplay.setOnClickListener(this);
        btff.setOnClickListener(this);
        btfb.setOnClickListener(this);
        btnxt.setOnClickListener(this);
        btprv.setOnClickListener(this);


        updateSeekBar= new Thread(){
            @Override
            public void run() {
                int totalDuration = mp.getDuration();
                int currentPosition = 0;
                while(currentPosition<totalDuration){
                    try {
                        sleep(500);
                        currentPosition=mp.getCurrentPosition();
                        sb.setProgress(currentPosition);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        if(mp!=null){
            mp.stop();
            mp.release();
        }

        Intent i = getIntent();
        Bundle b = i.getExtras();
        songsList = (ArrayList) b.getParcelableArrayList("songs");
        position = b.getInt("pos", 0);


        u = Uri.parse(songsList.get(position).toString());
        mp = MediaPlayer.create(getApplicationContext(), u);
        mp.start();
        sb.setMax(mp.getDuration());
        updateSeekBar.start();

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mp.seekTo(seekBar.getProgress());
            }
        });


    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id) {
            case R.id.btplay:
                if (mp.isPlaying()) {
                   btplay.setText(">");
                    mp.pause();
                } else {
                    mp.start();
                    btplay.setText("II");
                }
                break;

            case R.id.btff:
                mp.seekTo(mp.getCurrentPosition()+5000);
                break;

            case R.id.btfb:
                mp.seekTo(mp.getCurrentPosition()-5000);
                break;

            case R.id.btnxt:
                mp.stop();
                mp.release();
                position= (position+1)%songsList.size();
                u = Uri.parse(songsList.get(position).toString());
                mp = MediaPlayer.create(getApplicationContext(), u);
                sb.setMax(mp.getDuration());
                mp.start();
                break;

            case R.id.btprev:
                mp.stop();
                mp.release();
                if(position-1<0){
                    position= songsList.size()-1;
                }
                else {
                    position= position-1;
                }
                u = Uri.parse(songsList.get(position).toString());
                mp = MediaPlayer.create(getApplicationContext(), u);
                mp.start();
                sb.setMax(mp.getDuration());
                break;
        }
    }
}
