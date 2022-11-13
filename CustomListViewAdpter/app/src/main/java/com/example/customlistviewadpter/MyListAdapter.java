package com.example.customlistviewadpter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyListAdapter extends ArrayAdapter<String> {

    private final Activity context;

    private final String[] mainTitle;
    private final String[] subTitle;
    private final int[] imageArray;


    public MyListAdapter(Activity context, String[] mainTitle, String[] subTitle, int[] imageArray) {
        super(context, R.layout.customlistitem, mainTitle);
        this.context = context;
        this.mainTitle = mainTitle;
        this.subTitle = subTitle;
        this.imageArray = imageArray;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.customlistitem, null, true);

        TextView titleText = rowView.findViewById(R.id.textView1);
        TextView subTitleText = rowView.findViewById(R.id.textView2);
        ImageView imageView = rowView.findViewById(R.id.imageview);



        titleText.setText(mainTitle[position]);
        subTitleText.setText(subTitle[position]);
        imageView.setImageResource(imageArray[position]);


        return rowView;
    }
}

