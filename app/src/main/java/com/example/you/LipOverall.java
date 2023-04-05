package com.example.you;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.you.Adapter.LipsSecAdapter;
import com.google.android.material.tabs.TabLayout;

public class LipOverall extends Fragment implements TabLayout.OnTabSelectedListener {

    public LipOverall() {
        // Required empty public constructor
    }

       @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private TabLayout tabLayoutlip;
    private ViewPager viewPagerlip;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_lip_overall, container, false);


       tabLayoutlip = view.findViewById(R.id.lipTab);

       tabLayoutlip.addTab(tabLayoutlip.newTab().setText("Lips"));
       tabLayoutlip.addTab(tabLayoutlip.newTab().setText("Lipsticks"));
       tabLayoutlip.addTab(tabLayoutlip.newTab().setText("Lip Liners"));
       tabLayoutlip.addTab(tabLayoutlip.newTab().setText("Lip Gloss"));
       tabLayoutlip.setTabGravity(TabLayout.GRAVITY_CENTER);


        viewPagerlip = view.findViewById(R.id.viewLip);
        LipsSecAdapter adapter = new LipsSecAdapter(getChildFragmentManager(),tabLayoutlip.getTabCount());
        viewPagerlip.setAdapter(adapter);
        tabLayoutlip.setOnTabSelectedListener(this);





        return  view;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        viewPagerlip.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}