package com.course.mobiletest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyDietListAdapter extends ArrayAdapter {
    public MyDietListAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int pos = position;
        String imgid;
        String imguri;
        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.diet_list, parent, false);
        }
        TextView foodname = convertView.findViewById(R.id.tv_foodname);
        TextView amount = convertView.findViewById(R.id.tv_amount);
        TextView calorie = convertView.findViewById(R.id.tv_calorie);
        TextView review = convertView.findViewById(R.id.tv_review);
        TextView time = convertView.findViewById(R.id.tv_time);
        TextView date = convertView.findViewById(R.id.tv_date);
        ImageView image = convertView.findViewById(R.id.imageView);

        Diet_Info list_view_item = (Diet_Info) getItem(position);

        foodname.setText(list_view_item.getFoodname());
        amount.setText(list_view_item.getAmount());
        calorie.setText(String.valueOf(list_view_item.getCalorie()));
        review.setText(list_view_item.getReview());
        time.setText(list_view_item.getTime());
        date.setText(list_view_item.getDate());

        imgid=list_view_item.getImageID();
        imguri=list_view_item.getImageURI();
        try {

            String imgpath = "/data/user/0/com.course.mobiletest/cache/" + imgid;
            Bitmap bm= BitmapFactory.decodeFile(imgpath);
            image.setImageBitmap(bm);
        }catch(Exception e){

        }


        return convertView;
    }
}


