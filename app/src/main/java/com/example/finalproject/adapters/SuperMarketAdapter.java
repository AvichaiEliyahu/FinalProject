package com.example.finalproject.adapters;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.finalproject.R;
import com.example.finalproject.objects.Supermarket;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class SuperMarketAdapter extends ArrayAdapter<Supermarket> {
    private Geocoder geocoder;

    public SuperMarketAdapter(@NonNull Context context, int resource, @NonNull List<Supermarket> objects) {
        super(context, resource, objects);
        geocoder = new Geocoder(context, Locale.getDefault());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Supermarket supermarket = getItem(position);
        List<Address> addresses;
        String address = "";
        try {
            addresses = geocoder.getFromLocation(supermarket.getLat(), supermarket.getLon(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            address = addresses.get(0).getAddressLine(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.super_list_item, parent, false);
        }
        // Lookup view for data population
        TextView superItem_LBL_title = (TextView) convertView.findViewById(R.id.superItem_LBL_title);
        TextView superItem_LBL_address = (TextView) convertView.findViewById(R.id.superItem_LBL_address);
        // Populate the data into the template view using the data object
        superItem_LBL_title.setText(supermarket.getSuperID());
        superItem_LBL_address.setText(address);
        // Return the completed view to render on screen
        return convertView;
//        return super.getView(position, convertView, parent);
    }
}
