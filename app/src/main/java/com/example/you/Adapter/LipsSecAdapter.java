package com.example.you.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.you.Lipgloss;
import com.example.you.Lipliners;
import com.example.you.Lips;
import com.example.you.Lipstick;

public class LipsSecAdapter  extends FragmentPagerAdapter {

    int lipnum;

    public LipsSecAdapter(@NonNull FragmentManager fm, int lipnum) {
        super(fm);
        this.lipnum = lipnum;
    }

    public LipsSecAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Lips lips = new Lips();
                return lips;
            case 1:
                Lipstick lipstick = new Lipstick();
                return lipstick;
            case 2:
                Lipliners lipliners = new Lipliners();
                return lipliners;
            case 3:
                Lipgloss lipgloss = new Lipgloss();
                return lipgloss;
        }
        return null;
    }

    @Override
    public int getCount() {

        return lipnum;
    }
}
