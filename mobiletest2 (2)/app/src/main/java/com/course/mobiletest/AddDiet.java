package com.course.mobiletest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

public class AddDiet extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener{
    Button b1_ok, b2_image;
    TextView text1,text2;
    EditText et_date,et_foodname,et_amount,et_calorie,et_review,et_time;
    ImageView imageView;
    MapView mapView;
    Uri imageUri;
    int imageID=0;
    final int GET_GALLERY_IMAGE = 300;

    MapView mapview;
    Marker marker;
    public static String Address = "서울특별시 중구 필동로1길 30";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diet);

        Intent intent =getIntent();
        String date=intent.getStringExtra("date");
        et_date=findViewById(R.id.et_date);
        et_date.setText(date);
        et_foodname = findViewById(R.id.et_food);
        et_amount = findViewById(R.id.et_amount);
        et_review = findViewById(R.id.et_review);
        et_time = findViewById(R.id.et_time);
        et_calorie = findViewById(R.id.et_calorie);

        imageView = findViewById(R.id.addimageView);
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync((OnMapReadyCallback) this);

        b1_ok=(Button) findViewById(R.id.btn_ok);
        b1_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues addValues=new ContentValues();
                addValues.put(MyContentProvider.FOODNAME,((EditText)findViewById(R.id.et_food)).getText().toString());
                addValues.put(MyContentProvider.AMOUNT,((EditText)findViewById(R.id.et_amount)).getText().toString());
                addValues.put(MyContentProvider.REVIEW,((EditText)findViewById(R.id.et_review)).getText().toString());
                addValues.put(MyContentProvider.DATE, et_date.getText().toString());
                addValues.put(MyContentProvider.TIME,((EditText)findViewById(R.id.et_time)).getText().toString());
                addValues.put(MyContentProvider.KCAL,((EditText)findViewById(R.id.et_calorie)).getText().toString());
                addValues.put(MyContentProvider.IMAGE_ID, String.valueOf(imageID));
                addValues.put(MyContentProvider.IMAGE_URI, imageUri.toString());
                addValues.put(MyContentProvider.ADDRESS, getAddress());

                getContentResolver().insert(MyContentProvider.CONTENT_URI,addValues);
                Toast.makeText(getBaseContext(),"Record Added",Toast.LENGTH_LONG).show();
                finish();
            }
        });

        b2_image = findViewById(R.id.selectImageBtn);
        b2_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,0);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                Glide.with(getApplicationContext()).load(data.getData()).override(300, 300).into(imageView);
                imageID++;
                imageUri = data.getData();

                ContentResolver resolver=getContentResolver();
                try{
                    InputStream instream=resolver.openInputStream(imageUri);
                    Bitmap imgBitmap=BitmapFactory.decodeStream(instream);
                    instream.close();
                    saveBitmap(imgBitmap);
                }catch (Exception e){

                }
            }
        }
    }
    public void saveBitmap(Bitmap bitmap){
        File tempFile=new File(getCacheDir(),String.valueOf(imageID));
        try{
            tempFile.createNewFile();
            FileOutputStream out=new FileOutputStream(tempFile);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,out);
            out.close();
        }catch (Exception e){

        }

    }



    public static String getAddress() {
        return Address;
    }

    private void getAddress(LatLng position) {
        Geocoder geoCoder = new Geocoder(this, Locale.KOREA);
        List<Address> address = null;
        try {
            address = geoCoder.getFromLocation(position.latitude, position.longitude, 1);
            if (address != null && address.size() > 0) {
                Address = address.get(0).getAddressLine(0).toString();
                System.out.println(Address);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void onMapReady(GoogleMap googleMap) {

        MapsInitializer.initialize(this);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(37.55802, 126.99857), 14);

        googleMap.animateCamera(cameraUpdate);

        marker = googleMap.addMarker(new MarkerOptions().position(new LatLng(37.55802, 126.99857))
                .title("동국대학교"));


        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            public void onMapClick(LatLng point) {
                marker.remove();
                System.out.println("포인트"+point.latitude+"" + point.longitude);
                marker = googleMap.addMarker(new MarkerOptions().position(new LatLng(point.latitude, point.longitude)).title("위치")
                );
                System.out.println(point.latitude + "" + point.longitude);
                getAddress(point);
            }
        });
    }

    public boolean onMarkerClick(@NonNull Marker marker) {
        String markerId = marker.getId();
        LatLng location = marker.getPosition();
        System.out.println("Marker ID : "
                + markerId + "(" + location.latitude + " " + location.longitude + ")");
        return false;
    }
}