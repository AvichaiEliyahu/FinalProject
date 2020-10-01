package com.example.finalproject.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.finalproject.CallBack.CallBack_SelectSupermarket;
import com.example.finalproject.R;
import com.example.finalproject.Supermarket;

import java.util.List;

public class Fragment_Super_List extends Fragment {
    protected View view;
    private List<Supermarket> allSupermarkets;
    private CallBack_SelectSupermarket callBack_selectSupermarket;
    private ListView list_LSTVIEW_supers;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Log.d("pttt","onCreateView");
        if(view==null){
            view = inflater.inflate(R.layout.fragment_super_list, container, false);
        }
        list_LSTVIEW_supers = view.findViewById(R.id.list_LSTVIEW_supers);
        
        return view;
    }

    public Fragment_Super_List setCallBack_selectSupermarket(CallBack_SelectSupermarket callBack_selectSupermarket) {
        this.callBack_selectSupermarket = callBack_selectSupermarket;
        return this;
    }
}
