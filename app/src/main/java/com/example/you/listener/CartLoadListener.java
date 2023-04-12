package com.example.you.listener;

import com.example.you.Model.cartModel;

import java.util.List;

public interface CartLoadListener {

    void  onCartLoadSuccess(List<cartModel> cartModelList);
    void onCartLoadFailed(String message);
}
