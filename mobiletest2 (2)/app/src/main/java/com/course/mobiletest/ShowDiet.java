package com.course.mobiletest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShowDiet extends AppCompatActivity {

    private DietDBManager helper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_diet);
        helper = new DietDBManager(this, "Diets.db",null,1);
        helper.CreateTable();
        updateListView();
    }

//    public void mOnClick_AddKid(View view){
//        EditText vName = findViewById(R.id.editNewKidName);
//        TextView vBirth = findViewById(R.id.editNewKidBirth);
//        String name = vName.getText().toString();
//        String birth = vBirth.getText().toString();
//        helper.InsertKid(name, birth);
//        updateListView();
//    }

    public void updateListView(){
        MyDietListAdapter adapter = new MyDietListAdapter(this, R.layout.diet_list,  helper.SelectAllDiets());
        ListView listView = findViewById(R.id.listview);
        listView.setAdapter(adapter);
    }
}
