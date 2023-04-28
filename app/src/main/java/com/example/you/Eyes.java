package com.example.you;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.you.Adapter.EyeAdapter;
import com.example.you.Model.EyeModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Eyes extends Fragment {
    FirebaseDatabase database;
    public RecyclerView mRecycleView;
    public EyeAdapter mAdapter;
    public RecyclerView.LayoutManager mManager;
    View view;
    ArrayList<EyeModel> list = new ArrayList<>();
    public Eyes() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view= inflater.inflate(R.layout.fragment_eyes, container, false);
        return view;

    }
    private void loadEyeFromFirebase(){
        DatabaseReference myRef = database.getReference("Eye");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@org.checkerframework.checker.nullness.qual.NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    EyeModel eyeModel = snapshot.getValue(EyeModel.class);
                    list.add(eyeModel);
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
        mRecycleView = view.findViewById(R.id.recycler_eyes);
        mRecycleView.setHasFixedSize(true);
        mManager = new LinearLayoutManager(getActivity());
        mAdapter = new EyeAdapter(list);
        mRecycleView.setLayoutManager(mManager);
        mRecycleView.setAdapter(mAdapter);
        loadEyeFromFirebase();
    }

}