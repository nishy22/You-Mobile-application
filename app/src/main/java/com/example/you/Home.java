package com.example.you;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.you.Adapter.SectionPageAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class Home extends Fragment implements TabLayout.OnTabSelectedListener  {

    public Home() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private TabLayout tabLayout;
    private ViewPager viewPager;
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

         tabLayout = view.findViewById(R.id.tabLayout);
         tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
         tabLayout.setTabGravity(TabLayout.GRAVITY_START);

         tabLayout.addTab(tabLayout.newTab().setText("Best Selling"));
         tabLayout.addTab(tabLayout.newTab().setText("New Arrivals"));
         tabLayout.addTab(tabLayout.newTab().setText("Face"));
         tabLayout.addTab(tabLayout.newTab().setText("Lips"));
         tabLayout.addTab(tabLayout.newTab().setText("Eyes"));
         tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);


         viewPager= view.findViewById(R.id.viewPager);
         SectionPageAdapter adapter = new SectionPageAdapter(getChildFragmentManager(),tabLayout.getTabCount());
         viewPager.setAdapter(adapter);
         tabLayout.setOnTabSelectedListener(this);
         // Inflate the layout for this fragment
        return  view;
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    // call onActivity create method



}