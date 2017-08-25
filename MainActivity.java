package com.example.arkac.mplayer;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView l;
    String[] items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        l=(ListView)findViewById(R.id.play_list);

        final ArrayList<File> songslist = findsongs(Environment.getExternalStorageDirectory());

        for(int i=0;i<songslist.size();i++) {

            items[i]= songslist.get(i).getName().toString().replace(".mp3","");
        }

        ArrayAdapter<String> adp = new ArrayAdapter<String>(getApplicationContext(),R.layout.customlayout, R.id.textView,items);
        l.setAdapter(adp);
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                startActivity(new Intent(getApplicationContext(),player.class).putExtra("pos",position).putExtra("songs",songslist));

            }
        });
    }



    public ArrayList<File> findsongs(File root) {

        ArrayList<File> al= new ArrayList<File>();
        File[] files = root.listFiles();
        for (File singleFile : files) {
            if (singleFile.isDirectory() && !singleFile.isHidden()) {
                al.addAll(findsongs(singleFile));
            }
            else {
                if(singleFile.getName().endsWith(".mp3")){
                    al.add(singleFile);
                }
            }
        }
        return al;
    }
}
