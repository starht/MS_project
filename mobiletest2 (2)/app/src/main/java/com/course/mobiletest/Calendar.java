package com.course.mobiletest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;

public class Calendar extends AppCompatActivity {

    Button b1_add ,b2_show, b3_analy;
    CalendarView calView;
    String [] selectionArgs;
    String date;
    EditText et_date,et_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);

        java.util.Calendar today= java.util.Calendar.getInstance();
        SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd");
        date=format1.format(today.getTime());

        //LocalDate now = LocalDate.now();
        et_date =findViewById(R.id.et_date1);
        et_date.setText(date);
        et_list=findViewById(R.id.et_dietlist);
        b1_add=(Button)findViewById(R.id.bt_input);
        b2_show=(Button)findViewById(R.id.bt_showlist);
        b3_analy=(Button)findViewById(R.id.bt_analyze);
        b1_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Calendar.this,AddDiet.class);
                intent.putExtra("date",date);
                startActivity(intent);
            }
        });

        b2_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDietDate();
            }
        });

        calView=findViewById(R.id.calView);
        calView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                month++;
                String str_month,str_day;
                if(month<10){
                    str_month="0"+month;
                }
                else{
                    str_month=String.valueOf(month);
                }
                if(dayOfMonth<10){
                    str_day="0"+dayOfMonth;
                }
                else{
                    str_day=String.valueOf(dayOfMonth);
                }
                date= year+"-"+str_month+"-"+str_day;
                et_date.setText(date);
                et_list.setText("");
            }
        });

        b3_analy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Calendar.this,Analyze.class);
                startActivity(intent);
            }
        });
    }

    public void getDietDate(){
        String [] columns=new String[]{"date","foodname","amount","review","time","kcal"};
        selectionArgs=new String[]{et_date.getText().toString()};
        Cursor c= getContentResolver().query(MyContentProvider.CONTENT_URI,columns," date = ? ", selectionArgs,null,null);

        if (c!=null){

            while(c.moveToNext()){
                String foodname=c.getString(1);
                String amount=c.getString(2);
                String review=c.getString(3);
                String time=c.getString(4);
                int kcal=c.getInt(5);


                et_list.append("음식: "+foodname+" 양: "+amount+" 평가: "+review+" 시간: "+time+" 칼로리: "+String.valueOf(kcal)+"kcal\n");
            }
        }
        else{
            Toast.makeText(getBaseContext(),"작성한 식단이 없습니다.",Toast.LENGTH_LONG).show();
        }
        c.close();
    }

}