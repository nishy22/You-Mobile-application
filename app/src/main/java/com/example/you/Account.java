package com.example.you;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;


public class Account extends Fragment {


   FirebaseAuth auth;
   Button button,button1;
   TextView textView;
   //FirebaseUser user;

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
        button = view.findViewById(R.id.loginAccount);
        button1 = view.findViewById(R.id.logout);
        textView = view.findViewById(R.id.user_details);

        /*user = auth.getCurrentUser();
        if (user == null){
           Log_in log_in = new Log_in();
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,log_in).commit();
        }

        else {
            textView.setText(user.getEmail());
        }*/


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log_in log_in = new Log_in();
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,log_in).commit();
            }
        });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Log_in log_in = new Log_in();
               requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,log_in).commit();

            }
        });
        requireActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Your code to handle back button press
            }
        });




        return  view;
    }
}