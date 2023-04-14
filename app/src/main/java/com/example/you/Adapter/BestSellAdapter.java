package com.example.you.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.you.Model.BestSellModel;
import com.example.you.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BestSellAdapter extends RecyclerView.Adapter<BestSellAdapter.BestSellerViewHolder> {

    private Context context;
    private List<BestSellModel> bestSellModelList;
    private List<BestSellModel> filteredBestSellModelList;

    public static class BestSellerViewHolder extends RecyclerView.ViewHolder {
        public ImageView mimage;
        public TextView shade;
        public TextView price;
        public BestSellerViewHolder(View itemView){
            super(itemView);
            mimage=itemView.findViewById(R.id.imageView);
            shade = itemView.findViewById(R.id.textShade);
            price = itemView.findViewById(R.id.textPrice);



        }
    }

    public BestSellAdapter(ArrayList<BestSellModel> list) {
        bestSellModelList = list;
        this.filteredBestSellModelList = bestSellModelList;
    }

    @NonNull
    @Override
    public BestSellerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_foundation_view, parent, false);
        return new BestSellAdapter.BestSellerViewHolder(v);
        //return new FoundationViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_foundation_view,parent,false));
    }
    @Override
    public void onBindViewHolder(@NonNull BestSellAdapter.BestSellerViewHolder holder, int position) {

       BestSellModel bestSellModel=filteredBestSellModelList.get(position);
        holder.shade.setText(bestSellModel.getShade());
        holder.price.setText("$"+bestSellModel.getPrice());
        Picasso.get().load(bestSellModel.getImage()).into(holder.mimage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return bestSellModelList.size();
    }


}
