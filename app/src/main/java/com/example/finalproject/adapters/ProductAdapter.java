package com.example.finalproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.finalproject.R;
import com.example.finalproject.objects.Product;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {

    public ProductAdapter(@NonNull Context context, int resource, @NonNull List<Product> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Product product = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.product_list_item, parent, false);
        }
        TextView productItem_LBL_name = (TextView) convertView.findViewById(R.id.productItem_LBL_name);
        TextView productItem_LBL_price = (TextView) convertView.findViewById(R.id.productItem_LBL_price);
        TextView productItem_LBL_count = (TextView) convertView.findViewById(R.id.productItem_LBL_count);
        ImageView productItem_IMG_img = (ImageView) convertView.findViewById(R.id.productItem_IMG_img);
        // Populate the data into the template view using the data object
        productItem_LBL_name.setText(product.getProdName());
        productItem_LBL_price.setText("price: " + product.getPrice());
        productItem_LBL_count.setText(product.getAmount() == null ? "0" : product.getAmount() + "");
        Glide
                .with(convertView.getContext())
                .load(R.drawable.ic_shopping_bag_blue)
                .centerCrop()
                .into(productItem_IMG_img);

        return convertView;

    }
}
