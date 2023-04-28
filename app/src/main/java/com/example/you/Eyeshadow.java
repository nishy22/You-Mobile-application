package com.example.you;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.you.Adapter.EyeshadowAdapter;
import com.example.you.Model.EyeshadowModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Eyeshadow extends Fragment {
    FirebaseDatabase database;
    public RecyclerView mRecycleView;
    public EyeshadowAdapter mAdapter;
    public RecyclerView.LayoutManager mManager;
    View view;
    ArrayList<EyeshadowModel> list = new ArrayList<>();
    public Eyeshadow() {
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
        view = inflater.inflate(R.layout.fragment_eyeshadow, container, false);
        return view ;
    }
    private void loadEyeshadowFromFirebase(){
        DatabaseReference myRef = database.getReference("Eyeshadow");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@org.checkerframework.checker.nullness.qual.NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                     EyeshadowModel eyeshadowModel = snapshot.getValue(EyeshadowModel.class);
                    list.add(eyeshadowModel);
                }
                mAdapter.notifyDataSetChanged();
                // Do something with the userList ArrayList
            }

            @Override
            public void onCancelled(@org.checkerframework.checker.nullness.qual.NonNull DatabaseError databaseError) {
                // Handle error

            }


        });

    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //https://console.firebase.google.com/project/youfinalyear/database/youfinalyear-default-rtdb/data/~2F
        database = FirebaseDatabase.getInstance();
        mRecycleView = view.findViewById(R.id.recycler_eyeshadow);
        mRecycleView.setHasFixedSize(true);
        mManager = new LinearLayoutManager(getActivity());
        mAdapter = new EyeshadowAdapter(list);
        mRecycleView.setLayoutManager(mManager);
        mRecycleView.setAdapter(mAdapter);
        loadEyeshadowFromFirebase();
    }
}