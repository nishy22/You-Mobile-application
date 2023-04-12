package com.example.you.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.you.Model.FoundationModel;
import com.example.you.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FoundationAdapter extends RecyclerView.Adapter<FoundationAdapter.FoundationViewHolder> {

    private Context context;
    private List<FoundationModel> foundationModelList;
    private List<FoundationModel> filteredFoundationModelList;

    public static class FoundationViewHolder extends RecyclerView.ViewHolder {
        public ImageView mimage;
        public TextView shade;
        public TextView price;
        public FoundationViewHolder(View itemView){
            super(itemView);
            mimage=itemView.findViewById(R.id.imageView);
            shade = itemView.findViewById(R.id.textShade);
            price = itemView.findViewById(R.id.textPrice);



        }
    }
    public FoundationAdapter(ArrayList<FoundationModel> list) {
        foundationModelList = list;
        this.filteredFoundationModelList = foundationModelList;
    }

//    public FoundationAdapter(Context context, List<FoundationModel> foundationModelList) {
//        this.context = context;
//        this.foundationModelList = foundationModelList;
//    }

    @NonNull
    @Override
    public FoundationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_foundation_view, parent, false);
        return new FoundationAdapter.FoundationViewHolder(v);
        //return new FoundationViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_foundation_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FoundationViewHolder holder, int position) {
//        Glide.with(context).load(foundationModelList.get(position).getImage()).into(holder.imageView);
//        holder.textprice.setText(new StringBuilder("$").append(foundationModelList.get(position).getPrice()));
//        holder.textshade.setText(new StringBuilder().append(foundationModelList.get(position).getShade()));
        FoundationModel foundationModel=filteredFoundationModelList.get(position);
        holder.shade.setText(foundationModel.getShade());
        holder.price.setText("$"+foundationModel.getPrice());
        Picasso.get().load(foundationModel.getImage()).into(holder.mimage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return foundationModelList.size();
    }

//    public class FoundationViewHolder  extends RecyclerView.ViewHolder{
//        @BindView(R.id.imageView)
//        ImageView imageView;
//        @BindView(R.id.textShade)
//        TextView textshade;
//        @BindView(R.id.textPrice)
//        TextView textprice;
//
//        private Unbinder unbinder;
//
//        public FoundationViewHolder(@NonNull View itemView) {
//            super(itemView);
//            unbinder = ButterKnife.bind(this,itemView);
//        }
//    }
}
