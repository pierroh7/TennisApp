package com.example.mytennisapp;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //Récupération de la valeur passée en param
        Bundle b = getIntent().getExtras();
        location = b.getString("matchLocation");

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng matchPlace = null;

        //Add other Stadium locations + lat & lng
        switch(location){
            case "philadeplhie":
                matchPlace= new LatLng(48.852120, 2.286220);
                break;

            case "brooklyn":
                matchPlace= new LatLng(47.852120, 2.286220);
                break;
        }

        // Add a marker in Sydney and move the camera
        assert matchPlace != null;
        mMap.addMarker(new MarkerOptions().position(matchPlace).title("Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(matchPlace, 15F));
    }
}
