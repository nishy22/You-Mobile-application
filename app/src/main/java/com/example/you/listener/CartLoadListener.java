package com.example.you.listener;

import com.example.you.Model.CartModel;

import java.util.List;

public interface CartLoadListener {

    void  onCartLoadSuccess(List<CartModel> cartModelList);
    void onCartLoadFailed(String message);
}
