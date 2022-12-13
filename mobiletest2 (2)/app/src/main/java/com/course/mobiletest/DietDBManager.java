package com.course.mobiletest;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DietDBManager extends SQLiteOpenHelper {

    private static final String DIET_DB="Diets.db";
    private static final String DIET_TABLE="Diets";
    private static final String DIET_ID  = "diet_id";
    private static final String KEY_FOODNAME = "foodname";
    private static final String KEY_AMOUNT = "amount";
    private static final String KEY_REVIEW = "review";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";
    private static final String KEY_CALORIE = "kcal";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_ADDRESS = "address";
    private static DietDBManager dbManager=null;

    static final String CREATE_DB =" CREATE TABLE IF NOT EXISTS " + DIET_TABLE
            + " (id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + " date TEXT NOT NULL, foodname TEXT NOT NULL, amount TEXT NOT NULL, review TEXT, time TEXT, kcal INTEGER NOT NULL,imageid INTEGER NOT NULL, imageuri TEXT NOT NULL, address TEXT NOT NULL);";
    static final String DELETE_DB = " DROP TABLE " + DIET_TABLE + ";";
    private final Context context;

    public static DietDBManager getInstance(Context context){
        if(dbManager==null){
            dbManager=new DietDBManager(context,DIET_DB,null,1);
        }
        return dbManager;
    }
    public DietDBManager (Context context, String dbName, SQLiteDatabase.CursorFactory factory,int version){
        super(context,dbName,factory,version);
        this.context=context;
    }
    @Override
    public void onOpen(SQLiteDatabase db){
        super.onOpen(db);
    }
    @Override
    public void onCreate(SQLiteDatabase db){ db.execSQL(CREATE_DB); }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldV,int newV){

    }
    public long insert(ContentValues addValue){
        return getWritableDatabase().insert(DIET_TABLE,null,addValue);
    }
    public Cursor query(String[]columns,String selection,String[] selectionArgs, String groupBy, String having, String orderBy){
        return getReadableDatabase().query(DIET_TABLE,columns,selection,selectionArgs,groupBy,having,orderBy);
    }
    public int delete(String whereClause, String[]whereArgs){
        return getWritableDatabase().delete(DIET_TABLE,whereClause,whereArgs);
    }

    public void CreateTable(){
        String CREATE_DIET_TABLE =
                "create table if not exists " + DIET_TABLE + "(" +
                        DIET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        KEY_FOODNAME + " TEXT NOT NULL  , " +
                        KEY_AMOUNT + " TEXT NOT NULL  , " +
                        KEY_REVIEW + " TEXT , " +
                        KEY_DATE + " TEXT NOT NULL  , " +
                        KEY_TIME + " TEXT , " +
                        KEY_CALORIE + " INTEGER NOT NULL , " +
                        KEY_IMAGE + " TEXT NOT NULL , "+
                        KEY_ADDRESS + " TEXT NOT NULL );";

        getWritableDatabase().execSQL(CREATE_DIET_TABLE);
    }

    public void addDiet(String foodname, String amount, String review, String date, String time, Integer kcal){
        String INSERT_QUERY = "insert into " + DIET_TABLE +
                " (" + KEY_FOODNAME + " , " + KEY_AMOUNT + " , " + KEY_REVIEW + " , "
                + KEY_DATE + " , " +KEY_TIME + " , " + KEY_CALORIE + ")  values  ('"
                + foodname + "', '" + amount + "', '" + review + "', '" + date + "', '" + time + "', '" + kcal + "' ) ";
        getWritableDatabase().execSQL(INSERT_QUERY);

    }
    public ArrayList SelectAllDiets(){
        String SELECT_QUERY = "SELECT * FROM "+ DIET_TABLE ;
        ArrayList diets_info = new ArrayList<Diet_Info>();

        Cursor cur= getWritableDatabase().rawQuery(SELECT_QUERY, null);
        if(cur!=null && cur.moveToFirst()){
            do{
                diets_info.add(new Diet_Info(cur.getString(2), cur.getString(3),cur.getInt(6),
                        cur.getString(4), cur.getString(5),cur.getString(1),cur.getString(7),cur.getString(8),cur.getString(9)));
            }while(cur.moveToNext());
        }
        return diets_info;
    }
}



