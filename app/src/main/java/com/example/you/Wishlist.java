package com.example.you;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.you.Adapter.BestSellAdapter;
import com.example.you.Model.BestSellModel;
import com.example.you.utils.Helper;

import java.util.ArrayList;


public class Wishlist extends Fragment {
    public RecyclerView mRecycleView;
    public BestSellAdapter mAdapter;
    public RecyclerView.LayoutManager mManager;

    View view;
    Helper helper;
    ArrayList<BestSellModel> list = new ArrayList<>();




    public Wishlist() {
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
        return inflater.inflate(R.layout.fragment_wishlist, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        helper = new Helper();
        helper.loadFavouriteData(this.getContext().getSharedPreferences("shared preferences", 0));
        list = helper.getFavProducts();

        mRecycleView = view.findViewById(R.id.recycler_wishlist);
        mRecycleView.setHasFixedSize(true);
        mManager = new LinearLayoutManager(getActivity());
        mAdapter = new BestSellAdapter(list, 1);
        mRecycleView.setLayoutManager(mManager);
        mRecycleView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}