package com.example.you;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.you.Adapter.EyesSecAdapter;
import com.google.android.material.tabs.TabLayout;


public class EyeOverall extends Fragment implements TabLayout.OnTabSelectedListener {

    public EyeOverall() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private TabLayout tabeye;
    private ViewPager vieweye;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_eye_overall, container, false);

       tabeye = view.findViewById(R.id.tabEye);


        tabeye.addTab(tabeye.newTab().setText("Eyes"));
        tabeye.addTab(tabeye.newTab().setText("Eyeshadow"));
        tabeye.addTab(tabeye.newTab().setText("Eye Liner"));
        tabeye.addTab(tabeye.newTab().setText("Lashes"));
        tabeye.setTabGravity(TabLayout.GRAVITY_CENTER);

        vieweye = view.findViewById(R.id.viewEye);
        EyesSecAdapter adapter = new EyesSecAdapter(getChildFragmentManager(),tabeye.getTabCount());
        tabeye.setOnTabSelectedListener(this);











       return view;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        vieweye.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}