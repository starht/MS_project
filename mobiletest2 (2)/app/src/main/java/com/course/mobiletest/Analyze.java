package com.course.mobiletest;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Analyze extends AppCompatActivity {

    Button b1,b2;
    String [] selectionArgs;
    EditText et1,et2;
    String date,yesterday=new String();

    SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyze);

        b1=findViewById(R.id.btn_show);
        b2=findViewById(R.id.btn_home);
        et1=findViewById(R.id.et_weekdate);
        et2=findViewById(R.id.et_showkcal);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int total_kcal=0;

                Calendar calendar= Calendar.getInstance();
                calendar.add(Calendar.DAY_OF_MONTH, -1);
                yesterday= format1.format(calendar.getTime());
                for(int i=0;i<7;i++) {
                    int kcal=0;
                    date=format1.format(calendar.getTime());
                    kcal=getKcal_Day(date);
                    et2.append(date+" : "+kcal+"kcal\n");
                    total_kcal+=kcal;
                    if(i==7) {
                        continue;
                    }
                    calendar.add(Calendar.DAY_OF_MONTH, -1);
                }

                et1.setText(date+"~"+yesterday);
                et2.append("총 열량:"+total_kcal+"kcal");
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    public int getKcal_Day(String day){
        int kcal, kcal_day=0;
        String [] columns= new String[]{"date","kcal"};
        selectionArgs=new String[]{day};
        Cursor c = getContentResolver().query(MyContentProvider.CONTENT_URI,columns," date = ? ", selectionArgs,null,null);
        if(c!=null) {
            Log.v("test","조건문");
            while(c.moveToNext()) {
                Log.v("test","반복문");;
                kcal = Integer.parseInt(c.getString(1));
                kcal_day+=kcal;
            }
        }
        return kcal_day;
    }
}