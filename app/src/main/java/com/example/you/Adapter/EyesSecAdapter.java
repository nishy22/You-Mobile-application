package com.example.you.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.you.Eyeliner;
import com.example.you.Eyes;
import com.example.you.Eyeshadow;
import com.example.you.Lashes;

public class EyesSecAdapter extends FragmentPagerAdapter {

    int eyenum;
    public EyesSecAdapter(@NonNull FragmentManager fm, int eyenum) {
        super(fm);
        this.eyenum = eyenum;
    }



    public EyesSecAdapter(@NonNull FragmentManager fm){
        super(fm);

    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Eyes eyes = new Eyes();
                return eyes;
            case 1:
                Eyeshadow eyeshadow = new Eyeshadow();
                return eyeshadow;
            case 2:
                Eyeliner eyeliner = new Eyeliner();
                return eyeliner;
            case 3:
                Lashes lashes = new Lashes();
                return lashes;
        }
        return null;
    }

    @Override
    public int getCount() {
       return eyenum;
    }
}
