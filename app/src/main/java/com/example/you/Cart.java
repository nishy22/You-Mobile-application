package com.example.you;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.you.Adapter.CartAdapter;
import com.example.you.Model.CartModel;
import com.example.you.utils.Helper;
import com.stripe.android.PaymentConfiguration;
import com.stripe.android.paymentsheet.PaymentSheet;
import com.stripe.android.paymentsheet.PaymentSheetResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cart extends Fragment {
    public RecyclerView mRecycleView;
    public CartAdapter mAdapter;
    public RecyclerView.LayoutManager mManager;

    Button button;
    String SECRET_KEY="sk_test_51N1qZKLGC7XokTdwk0VxtzgUfh9wD02AtWy5l6ReikjLs4jm30CzoG5p3SbRmN4kF46XObJV2jM5vETpPelpykII00VMuX4a7u";
    String PUBLISH_KEY="pk_test_51N1qZKLGC7XokTdwUTr5elGZomIzUFsPCGJ29nHQ75pgblmE63I6EexvVNUkMHeZyNgJEI9g4q1bZ9GWkXonyzhR00ITafxhFX";
    PaymentSheet paymentSheet;
    String customerID;
    String EphericalKey;
    String ClientSecret;

    View view;
    Helper helper;
    ArrayList<CartModel> list = new ArrayList<>();


    private Cart cart;



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
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here

        }
        button = view.findViewById(R.id.btnCart);
        cart = this;
        helper = new Helper();
        helper.loadCartData(this.requireContext().getSharedPreferences("shared preferences", 0));
        list = helper.getCartItems();
        mRecycleView = view.findViewById(R.id.recycler_cart);
        mRecycleView.setHasFixedSize(true);
        mManager = new LinearLayoutManager(getActivity());
        mAdapter = new CartAdapter(list);
        mRecycleView.setLayoutManager(mManager);
        mRecycleView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        PaymentConfiguration.init(requireContext(), PUBLISH_KEY);
        paymentSheet = new PaymentSheet(this,paymentSheetResult -> {

            onPaymentResult(paymentSheetResult);

        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentFlow();

            }
        });
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://api.stripe.com/v1/customers",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                        JSONObject object = new JSONObject(response);
                            customerID = object.getString("id");
                            Toast.makeText(getContext(), customerID, Toast.LENGTH_SHORT).show();
                            getEphericalKey(customerID);
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + SECRET_KEY);
                return  headers;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(requireActivity());
        requestQueue.add(stringRequest);


        if (list.isEmpty()) {
            button.setEnabled(false);
        }
    }

    private void onPaymentResult(PaymentSheetResult paymentSheetResult) {

        if(paymentSheetResult instanceof PaymentSheetResult.Completed){
            Toast.makeText(getContext(), "Payment Success",Toast.LENGTH_SHORT).show();

        }
    }

    private void getEphericalKey(String customerID) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://api.stripe.com/v1/ephemeral_keys",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject object = new JSONObject(response);
                            EphericalKey= object.getString("id");
                            Toast.makeText(getContext(), EphericalKey, Toast.LENGTH_SHORT).show();


                            getClientSecret(customerID,EphericalKey);
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + SECRET_KEY);
                headers.put("Stripe-Version","2022-11-15");
                return headers;
            }


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("customer",customerID);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(requireActivity());
        requestQueue.add(stringRequest);




    }

    private void getClientSecret(String customerID, String ephericalKey) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://api.stripe.com/v1/payment_intents",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject object = new JSONObject(response);
                            ClientSecret= object.getString("client_secret");

                            Toast.makeText(getContext(),ClientSecret, Toast.LENGTH_SHORT).show();

                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + SECRET_KEY);
                return headers;
            }



            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("customer",customerID);
                params.put("amount","1"+"00");
                params.put("currency","eur");
                params.put("automatic_payment_methods[enabled]","true");

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(requireActivity());
        requestQueue.add(stringRequest);


    }

    private void PaymentFlow() {

        paymentSheet.presentWithPaymentIntent(
                ClientSecret, new PaymentSheet.Configuration("YOU Company",
                        new PaymentSheet.CustomerConfiguration(
                                customerID,
                                EphericalKey
                        ))
        );
    }
}