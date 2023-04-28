package com.example.you;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.you.Adapter.CartAdapter;
import com.example.you.Model.CartModel;
import com.example.you.utils.Helper;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Cart extends Fragment {
    public RecyclerView mRecycleView;
    public CartAdapter mAdapter;
    public RecyclerView.LayoutManager mManager;

    View view;
    Helper helper;
    ArrayList<CartModel> list = new ArrayList<>();


    private Cart cart;
    Button button;

    public Cart() {
        // Required empty public constructor
    }





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view = inflater.inflate(R.layout.fragment_cart, container, false);
        button = view.findViewById(R.id.btnCart);
        cart = this;
        return view;

    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here

        }
        button = view.findViewById(R.id.btnCart);
        cart = this;
        helper = new Helper();
        helper.loadCartData(this.getContext().getSharedPreferences("shared preferences", 0));
        list = helper.getCartItems();

        mRecycleView = view.findViewById(R.id.recycler_cart);
        mRecycleView.setHasFixedSize(true);
        mManager = new LinearLayoutManager(getActivity());
        mAdapter = new CartAdapter(list);
        mRecycleView.setLayoutManager(mManager);
        mRecycleView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        if(list.isEmpty()){
            button.setEnabled(false);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText input = new EditText(getContext());
                new AlertDialog.Builder(getContext())
                        .setTitle("Phone Number")
                        .setMessage("Please Enter your Phone number:")
                        .setView(input)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                                String phoneNumber = input.getText().toString().trim();

                                // Validate the phone number format
                                if (phoneNumber.isEmpty() || !phoneNumber.matches("^\\+44\\d{10}$")) {
                                    Toast.makeText(getContext(), "Please enter a valid UK phone number.", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                // Create the Notify.lk API URL with the user ID, API key, sender ID, phone number, and message
                                String url = "https://app.notify.lk/api/v1/send?user_id=24813&api_key=rfQPjJZSAqHNLXRQEn1f&sender_id=NotifyDEMO&to=+447939813507&message=Your+order+has+been+placed.+Thank+you!";

                                // Send the HTTP request to the Notify.lk API
                                HttpURLConnection urlConnection = null;
                                try {
                                    URL apiEndpoint = new URL(url);
                                    urlConnection = (HttpURLConnection) apiEndpoint.openConnection();
                                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                                } catch (IOException e) {
                                    // Handle any errors

                                }

                                finally {
                                    if (urlConnection != null) {
                                        urlConnection.disconnect();
                                    }

                                    Intent i = new Intent(cart.getContext(), Payment.class);
                                    startActivity(i);
                                }

                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // Do nothing.
                            }
                        }).show();


            }
        });
    }

}