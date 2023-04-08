package com.example.you;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Account extends Fragment {


   FirebaseAuth auth;
   Button button,button1;
   TextView textView;
   FirebaseUser user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        auth = FirebaseAuth.getInstance();
        button = view.findViewById(R.id.logout);
        button1 = view.findViewById(R.id.login_from_account);
        textView = view.findViewById(R.id.user_details);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Log_in log_in = new Log_in();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,log_in).commit();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log_in log_in = new Log_in();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,log_in).commit();
            }
        });


        return  view;
    }
}