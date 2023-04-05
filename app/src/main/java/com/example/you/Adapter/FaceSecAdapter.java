package com.example.you.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.you.Blush;
import com.example.you.Bronzer;
import com.example.you.Concelar;
import com.example.you.Face;
import com.example.you.Foundation;
import com.example.you.Highlighter;
import com.example.you.Powder;
import com.example.you.Primer;

public class FaceSecAdapter extends FragmentPagerAdapter {
        int num;

    public FaceSecAdapter(@NonNull FragmentManager fm,int num) {
        super(fm);
        this.num = num;
    }

    public FaceSecAdapter(@NonNull FragmentManager fm){
        super(fm);

    }


    @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    Face face = new Face();
                    return face;
                case 1:
                    Foundation foundation = new Foundation();
                    return foundation;
                case 2:
                    Concelar concelar = new Concelar();
                    return concelar;
                case 3:
                    Bronzer bronzer = new Bronzer();
                    return bronzer;
                case 4:
                    Primer primer = new Primer();
                    return primer;
                case 5:
                    Blush blush = new Blush();
                    return blush;
                case 6:
                    Highlighter highlighter = new Highlighter();
                    return highlighter;
                case 7:
                    Powder powder = new Powder();
                    return  powder;
            }
            return null;
        }

        @Override
        public int getCount() {
            return num;
        }


    }

