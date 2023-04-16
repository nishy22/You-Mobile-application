package com.example.you.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.you.Model.NewArriveModel;
import com.example.you.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewArriveAdapter extends RecyclerView.Adapter<NewArriveAdapter.NewArriveViewHolder>  {

    private Context context;
    private List<NewArriveModel> newArriveModelList;
    private List<NewArriveModel> filterednewArriveModelList;



    public static class NewArriveViewHolder extends RecyclerView.ViewHolder {


       public ImageView mimage;
       public TextView shade;
       public TextView price;

       public NewArriveViewHolder(View itemView) {
           super(itemView);
           mimage = itemView.findViewById(R.id.imageView);
           shade = itemView.findViewById(R.id.textShade);
           price = itemView.findViewById(R.id.textPrice);


       }
   }

   public NewArriveAdapter (ArrayList<NewArriveModel> list) {
        newArriveModelList = list;
        this.filterednewArriveModelList = newArriveModelList;
    }

    @NonNull
    @Override
    public NewArriveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_foundation_view, parent, false);
        return new NewArriveAdapter.NewArriveViewHolder(v);
        //return new FoundationViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_foundation_view,parent,false));
    }
    @Override
    public void onBindViewHolder(@NonNull NewArriveAdapter.NewArriveViewHolder holder, int position) {

        NewArriveModel newArriveModel=filterednewArriveModelList.get(position);
        holder.shade.setText(newArriveModel.getShade());
        holder.price.setText("$"+newArriveModel.getPrice());
        Picasso.get().load(newArriveModel.getImage()).into(holder.mimage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return newArriveModelList.size();
    }

}



