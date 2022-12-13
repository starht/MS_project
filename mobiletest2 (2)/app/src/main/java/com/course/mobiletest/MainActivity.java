package com.course.mobiletest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom);

        // bottom nav 설정
        bottomNavigationView = findViewById(R.id.bottomNav);
        // 처음화면
        //getSupportFragmentManager().beginTransaction().add(R.id.main_frame, new First()).commit(); //FrameLayout에 fragment.xml 띄우기

        //바텀 네비게이션뷰 안의 아이템 설정
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.item_fragment1:
                        Intent goto_calendar = new Intent(MainActivity.this, Calendar.class);
                        startActivity(goto_calendar);
                        break;
                    case R.id.item_fragment2:
                        Intent goto_analy = new Intent(MainActivity.this, Analyze.class);
                        startActivity(goto_analy);
                        break;
                    case R.id.item_fragment3:
                        Intent goto_show = new Intent(MainActivity.this, ShowDiet.class);
                        startActivity(goto_show);
                        break;
                }
                return true;
            }
        });

    }

}