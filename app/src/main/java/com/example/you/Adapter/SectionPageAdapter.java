package com.example.you.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.you.BestSeller;
import com.example.you.Eyes;
import com.example.you.Face;
import com.example.you.Lips;
import com.example.you.NewArrive;

public class SectionPageAdapter extends FragmentPagerAdapter {
    int numCount;


    public SectionPageAdapter(@NonNull FragmentManager fm, int numCount){
        super(fm);
        this.numCount = numCount;
    }

    public SectionPageAdapter(@NonNull FragmentManager fm)
    {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                BestSeller bestSeller = new BestSeller();
                return bestSeller;
            case 1:
                NewArrive newArrive = new NewArrive();
                return newArrive;


            case 2:
                Face face = new Face();
                return face;

            case 3:
                Lips lips = new Lips();
                return lips;

            case 4:
                Eyes eyes = new Eyes();
                return eyes;
        }
        return null;
    }

    @Override
    public int getCount() {
        return numCount;
    }
}
