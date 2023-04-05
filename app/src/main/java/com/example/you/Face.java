package com.example.you;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.you.Adapter.FaceSecAdapter;
import com.google.android.material.tabs.TabLayout;


public class Face extends Fragment implements TabLayout.OnTabSelectedListener {


    public Face() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private TabLayout tabLayout2;
    private ViewPager viewPager2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_face, container, false);

        tabLayout2 = view.findViewById(R.id.tabLayout2);

        tabLayout2.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout2.setTabGravity(TabLayout.GRAVITY_START);

        tabLayout2.addTab(tabLayout2.newTab().setText("Foundation"));
        tabLayout2.addTab(tabLayout2.newTab().setText("Concealer"));
        tabLayout2.addTab(tabLayout2.newTab().setText("Bronzer"));
        tabLayout2.addTab(tabLayout2.newTab().setText("Primer"));
        tabLayout2.addTab(tabLayout2.newTab().setText("Blush"));
        tabLayout2.addTab(tabLayout2.newTab().setText("Highlighter"));
        tabLayout2.addTab(tabLayout2.newTab().setText("Powder"));
        tabLayout2.setTabGravity(TabLayout.GRAVITY_CENTER);


        viewPager2= view.findViewById(R.id.viewPager2);
        FaceSecAdapter adapter = new FaceSecAdapter(getChildFragmentManager(),tabLayout2.getTabCount());
        viewPager2.setAdapter(adapter);
        tabLayout2.setOnTabSelectedListener(this);







        return view;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager2.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}