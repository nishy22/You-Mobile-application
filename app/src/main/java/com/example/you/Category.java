package com.example.you;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

public class Category extends Fragment {




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


   MaterialButton button,button2,button3,button4,button5,button6;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view =  inflater.inflate(R.layout.fragment_category, container, false);
       button = view.findViewById(R.id.button_Id);
       button2 = view.findViewById(R.id.button_Id2);
       button3 = view.findViewById(R.id.button_Id3);
       button4 = view.findViewById(R.id.button_Id4);
       button5 = view.findViewById(R.id.button_Id5);
       button6 = view.findViewById(R.id.button_Id6);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BestSeller bestSeller = new BestSeller();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,bestSeller).commit();
            }
        });


       button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewArrive newArrive = new NewArrive();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,newArrive).commit();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               FaceOverall faceOverall = new FaceOverall();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,faceOverall).commit();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LipOverall lipOverall = new LipOverall();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,lipOverall).commit();
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EyeOverall eyeOverall = new EyeOverall();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,eyeOverall).commit();
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sale sale = new Sale();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,sale).commit();
            }
        });







       // Inflate the layout for this fragment
        return view;
    }



}