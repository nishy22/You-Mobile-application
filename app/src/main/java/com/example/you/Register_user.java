package com.example.you;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.checkerframework.checker.nullness.qual.NonNull;


public class Register_user extends Fragment {


    public Register_user() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    TextInputEditText editTextEmail, editTextPassword;
    Button buttonReg;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView textView;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Log_in log_in = new Log_in();
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,log_in).commit();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_register_user, container, false);

       editTextEmail = view.findViewById(R.id.signup_email);
       editTextPassword = view.findViewById(R.id.signup_password);
       buttonReg = view.findViewById(R.id.signup_button);
       mAuth = FirebaseAuth.getInstance();
       progressBar = view.findViewById(R.id.progessbar);
       textView = view.findViewById(R.id.loginRedirectText);

       textView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if (getActivity() != null ) {
                       Log_in log_in = new Log_in();
                       requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, log_in).commit();
                   }
               }

    });

       buttonReg.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               progressBar.setVisibility(v.VISIBLE);
               String email, password;
               email = String.valueOf(editTextEmail.getText());
               password = String.valueOf(editTextPassword.getText());

               if(TextUtils.isEmpty(email)){
                   Toast.makeText(getActivity(),"Enter your Email",Toast.LENGTH_SHORT).show();
                   return;
               }
               if(TextUtils.isEmpty(password)){
                   Toast.makeText(getActivity(),"Enter your Password",Toast.LENGTH_SHORT).show();
                   return;
               }
               mAuth.createUserWithEmailAndPassword(email, password)
                       .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {
                               progressBar.setVisibility(View.GONE);
                               if (task.isSuccessful()) {
                                   Toast.makeText(getActivity(), "Account Created.",
                                           Toast.LENGTH_SHORT).show();


                                       Log_in log_in = new Log_in();
                                       requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, log_in).commit();




                               } else {
                                   // If sign in fails, display a message to the user.
                                   Toast.makeText(getActivity(), "Authentication failed.",
                                           Toast.LENGTH_SHORT).show();

                               }
                           }
                       });

           }
       });









       return view;
    }
}