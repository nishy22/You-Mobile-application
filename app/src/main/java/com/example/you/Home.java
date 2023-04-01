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

    private TabLayout tabLayout;
    private ViewPager viewPager;


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

         tabLayout = tabLayout.findViewById(R.id.tabLayout);
         tabLayout.addTab(tabLayout.newTab().setText("BEST SELL"));
         tabLayout.addTab(tabLayout.newTab().setText("new"));
         tabLayout.addTab(tabLayout.newTab().setText("face"));
         tabLayout.addTab(tabLayout.newTab().setText("lips"));
         tabLayout.addTab(tabLayout.newTab().setText("eyes"));
         tabLayout.setTabGravity(tabLayout.GRAVITY_CENTER);


         viewPager = viewPager.findViewById(R.id.viewPager);
         SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
         viewPager.setAdapter(adapter);
         tabLayout.setOnTabSelectedListener(this);





        // Inflate the layout for this fragment
        return  view;
    }



    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    // call onActivity create method



}