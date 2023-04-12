package com.example.you.listener;

import com.example.you.Model.FoundationModel;

import java.util.List;

public interface IFoundationLoadListener {

    void  onFoundationLoadSuccess(List<FoundationModel> foundationModelList);
    void onFoundationLoadFailed(String message);
}
