package com.example.arkac.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
{

    private String[] titles = {"AMBULANCE",
            "LOCATION",
            "SIREN"};

    private String[] details = {"call 102",
            "send your current location", "sound to alert nearby people"};

    private int[] images = { R.drawable.ambupic,
            R.drawable.famly,
            R.drawable.siren};

    class ViewHolder extends RecyclerView.ViewHolder{

        public int currentItem;
        public ImageView itemImage;
        public TextView itemTitle;
        public TextView itemDetail;
        private final Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            context= itemView.getContext();
            itemImage = (ImageView)itemView.findViewById(R.id.item_image);
            itemTitle = (TextView)itemView.findViewById(R.id.item_title);
            itemDetail = (TextView)itemView.findViewById(R.id.item_detail);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    final Intent intent;
                    int position = getAdapterPosition();

                    //Snackbar.make(v, "Click detected on item " + position, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    switch (position)
                    {
                        case 0:
                            intent = new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse("tel:102"));

                            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                return;
                            }
                            break;
                        case 1:
                            Log.i("Send SMS", "");
                            intent = new Intent(Intent.ACTION_VIEW);

                            intent.setData(Uri.parse("smsto:"));
                            intent.setType("vnd.android-dir/mms-sms");
                            intent.putExtra("address" , new String ("102"));
                            intent.putExtra("sms_body"  , "Dipto Sir I am sick pls help me");

                            break;
                        default:
                            intent =  new Intent(context, Siren.class);
                            break;
                    }
                    try {
                        context.startActivity(intent);
                        Log.i("Finished sending SMS...", "");
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(context,
                                "SMS faild, please try again later.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.itemTitle.setText(titles[i]);
        viewHolder.itemDetail.setText(details[i]);
        viewHolder.itemImage.setImageResource(images[i]);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}
