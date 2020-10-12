package com.example.finalproject.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.finalproject.CallBack.CallBack_SelectSupermarket;
import com.example.finalproject.R;
import com.example.finalproject.objects.Supermarket;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class Fragment_Map extends Fragment {
    protected View view;
    private MapView mMapView;
    private GoogleMap googleMap;
    private CallBack_SelectSupermarket callBack_selectSupermarket;
    private List<Supermarket> allSupermarkets;

    public void setCallBack_selectSupermarket(CallBack_SelectSupermarket callBack_selectSupermarket) {
        this.callBack_selectSupermarket = callBack_selectSupermarket;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Log.d("pttt", "onCreateView");
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_map, container, false);
        }
        mMapView = view.findViewById(R.id.map_mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume(); // needed to get the map to display immediately
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button
                if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                googleMap.setMyLocationEnabled(true);
                putSupermarketOnMap();
                // For dropping a marker at a point on the Map
                LatLng afeka = new LatLng(32.115033, 34.818040);
                googleMap.addMarker(new MarkerOptions().position(afeka).title("afeka!"));
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(afeka));
                googleMap.setMinZoomPreference(10);

                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(afeka).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });
        return view;
    }

    private void putSupermarketOnMap() {
        List<Supermarket> sp = this.callBack_selectSupermarket.getAllSupermarkets();
//        while (sp.size() == 0 ){
//            sp = this.callBack_map.getAllSupermarkets();
//        }
        allSupermarkets = this.callBack_selectSupermarket.getAllSupermarkets();
        for (int i = 0; i < sp.size(); i++) {
            LatLng latLng = new LatLng(sp.get(i).getLat(), sp.get(i).getLon());
            Log.d("supers", sp.get(i).toString());
            googleMap.addMarker(new MarkerOptions().position(latLng).title(sp.get(i).getSuperID() + ""))
            ;
        }
    }

    //    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        LatLng afeka = new LatLng(32.115033, 34.818040);
        this.googleMap.addMarker(new MarkerOptions().position(afeka).title("afeka!"));
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(afeka));
        this.googleMap.setMinZoomPreference(10);


    }


    public void zoomToSelected(int selectedSupermarketID) {
        Supermarket sm = allSupermarkets.get(selectedSupermarketID);
        LatLng supermarket = new LatLng(sm.getLat(), sm.getLon());

        CameraPosition cameraPosition = new CameraPosition.Builder().target(supermarket).zoom(12).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}
