package com.example.you;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.you.Adapter.EyelinerAdapter;
import com.example.you.Model.EyelinerModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;


public class Eyeliner extends Fragment {
    FirebaseDatabase database;
    public RecyclerView mRecycleView;
    public EyelinerAdapter mAdapter;
    public RecyclerView.LayoutManager mManager;

    View view;
    ArrayList<EyelinerModel> list = new ArrayList<>();

    public Eyeliner() {
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
       view = inflater.inflate(R.layout.fragment_eyeliner, container, false);
       return view;
    }
    private void loadEyelinerFromFirebase() {

        DatabaseReference myRef = database.getReference("Eyelinear");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    EyelinerModel eyelinerModel = snapshot.getValue(EyelinerModel.class);
                    list.add(eyelinerModel);
                }
                mAdapter.notifyDataSetChanged();
                // Do something with the userList ArrayList
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error

            }


        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //https://console.firebase.google.com/project/youfinalyear/database/youfinalyear-default-rtdb/data/~2F
        database = FirebaseDatabase.getInstance();
        mRecycleView = view.findViewById(R.id.recycler_eyeliner);
        mRecycleView.setHasFixedSize(true);
        mManager = new LinearLayoutManager(getActivity());
        mAdapter = new EyelinerAdapter(list);
        mRecycleView.setLayoutManager(mManager);
        mRecycleView.setAdapter(mAdapter);
        loadEyelinerFromFirebase();
    }
}