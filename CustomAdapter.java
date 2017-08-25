package com.example.arkac.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class CustomAdapter extends BaseAdapter {

    String [] name;
    Context context;
    String [] catagory;
    String [] pho;
    private static LayoutInflater inflater=null;
    public CustomAdapter(MakeCall m, String[] Docname, String[] specilaist, String[] phone ) {

        name=Docname;
        context=m;
        pho = phone;
        catagory=specilaist;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int posi) {
        return posi;
    }

    @Override
    public long getItemId(int posi) {
        return posi;
    }

    public  class Holder{
        TextView tv,tv2;
        ImageButton b,b2;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.eachrow, null);
        holder.tv=(TextView) rowView.findViewById(R.id.name);
        holder.tv2=(TextView) rowView.findViewById(R.id.type);
        holder.b= (ImageButton) rowView.findViewById(R.id.imageButton);
        holder.b2= (ImageButton) rowView.findViewById(R.id.imageButton2);
        holder.tv.setText(name[position]);
        holder.tv2.setText(catagory[position]);
        holder.b.setTag(pho[position]);
        holder.b2.setTag(pho[position]);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(context, "You Clicked " + name[position]+catagory[position], Toast.LENGTH_LONG).show();
            }
        });
        return rowView;
    }


}