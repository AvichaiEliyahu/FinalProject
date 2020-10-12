package com.example.finalproject.CallBack;

import com.example.finalproject.objects.Supermarket;

import java.util.List;

public interface CallBack_SelectSupermarket {
    public List<Supermarket> getAllSupermarkets();
    public void selectSupermarket();
    public void setSupermarketSelect(int id);

}
