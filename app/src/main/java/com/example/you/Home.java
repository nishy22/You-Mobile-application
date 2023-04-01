package com.example.you;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.you.Adapter.SectionPageAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;



public class Home extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false) ;

        ImageSlider imageSlider = view.findViewById(R.id.imageslider) ;
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.im1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.im2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.im3, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels,ScaleTypes.FIT);







        // Inflate the layout for this fragment
        return  view;
    }

    // call onActivity create method



}