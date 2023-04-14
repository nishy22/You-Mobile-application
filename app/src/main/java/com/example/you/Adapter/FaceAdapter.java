package com.example.you.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.you.Model.FaceModel;
import com.example.you.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FaceAdapter extends RecyclerView.Adapter<FaceAdapter.FaceViewHolder> {

    private Context context;
    private List<FaceModel> faceModelList;
    private List<FaceModel> filteredFaceModelList;


    public static class FaceViewHolder extends RecyclerView.ViewHolder {
        public ImageView mimage;
        public TextView shade;
        public TextView price;
    public FaceViewHolder(View itemView) {
        super(itemView);
        mimage = itemView.findViewById(R.id.imageView);
        shade = itemView.findViewById(R.id.textShade);
        price = itemView.findViewById(R.id.textPrice);


        }
    }

    public FaceAdapter(ArrayList<FaceModel> list) {
        faceModelList= list;
        this.filteredFaceModelList = faceModelList;
    }
    @NonNull
    @Override
    public FaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_foundation_view, parent, false);
        return new FaceAdapter.FaceViewHolder(v);
        //return new FoundationViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_foundation_view,parent,false));
    }
    @Override
    public void onBindViewHolder(@NonNull FaceAdapter.FaceViewHolder holder, int position) {

        FaceModel faceModel=filteredFaceModelList.get(position);
        holder.shade.setText(faceModel.getShade());
        holder.price.setText("$"+faceModel.getPrice());
        Picasso.get().load(faceModel.getImage()).into(holder.mimage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return faceModelList.size();
    }

}
