package com.course.mobiletest;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.course.mobiletest.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng dongguk = new LatLng(37.55827, 126.998425);

        // 마커에 대한 옵션 설정
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(dongguk);
        markerOptions.title("동국대학교");
        markerOptions.snippet("지금 있는 곳");
        mMap.addMarker(markerOptions);
        // 줌 기능 활성화
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        // 현재 위치로 이동
        mMap.moveCamera(CameraUpdateFactory.newLatLng(dongguk));
        //줌 레벨 설정
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
    }
}