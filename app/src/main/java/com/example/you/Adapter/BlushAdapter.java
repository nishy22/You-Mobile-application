package com.example.you.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.you.Model.BestSellModel;
import com.example.you.Model.BlushModel;
import com.example.you.Model.CartModel;
import com.example.you.R;
import com.example.you.utils.Helper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BlushAdapter extends RecyclerView.Adapter<BlushAdapter.BlushViewHolder> {
    private Context context;
    private List<BlushModel> blushModelList;
    private List<BlushModel> filteredBlushModelList;

    public  static  class BlushViewHolder extends RecyclerView.ViewHolder{
        public ImageView mimage;
        public TextView shade;
        public TextView price;
        public AppCompatImageButton wishButton;
        public AppCompatImageButton cartbutton;
        public BlushViewHolder(View itemView){
            super(itemView);
            mimage=itemView.findViewById(R.id.imageView);
            shade = itemView.findViewById(R.id.textShade);
            price = itemView.findViewById(R.id.textPrice);
            wishButton = itemView.findViewById(R.id.imageButtonWish);
            cartbutton = itemView.findViewById(R.id.imageButtonCart);




        }
    }

    public BlushAdapter(ArrayList<BlushModel>list){
        blushModelList = list;
        this.filteredBlushModelList = blushModelList;
    }

    @NonNull
    @Override
    public BlushAdapter.BlushViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_foundation_view, parent, false);
        return new BlushAdapter.BlushViewHolder(v);
        //return new FoundationViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_foundation_view,parent,false));
    }



    @Override
    public void onBindViewHolder(@NonNull BlushViewHolder holder, int position) {

        BlushModel blushModel=filteredBlushModelList.get(position);
        holder.shade.setText(blushModel.getShade());
        holder.price.setText("$"+blushModel.getPrice());
        Helper helper = new Helper();
        Picasso.get().load(blushModel.getImage()).into(holder.mimage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }

        });
        holder.wishButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ArrayList<BestSellModel> favProducts;
                BestSellModel item= new BestSellModel();
                item.setImage(blushModel.getImage());
                item.setKey(blushModel.getKey());
                item.setPrice(blushModel.getPrice());
                item.setShade(blushModel.getShade());
                helper.loadFavouriteData(holder.wishButton.getContext().getSharedPreferences("shared preferences", 0));
                favProducts = helper.getFavProducts();
                favProducts.add(item);
                helper.saveFavData(holder.wishButton.getContext().getSharedPreferences("shared preferences", 0), favProducts);
                Toast.makeText(holder.wishButton.getContext(), "Added to Wishlist!", Toast.LENGTH_SHORT).show();
            }
        });
        holder.cartbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                ArrayList<CartModel> cartProducts;
                CartModel item= new CartModel();
                item.setImage(blushModel.getImage());
                item.setKey(blushModel.getKey());
                item.setPrice(blushModel.getPrice());
                item.setShade(blushModel.getShade());
                item.setQuantity(1);
                item.setTotalPrice(1 * Float.parseFloat(blushModel.getPrice()));
                helper.loadCartData(holder.wishButton.getContext().getSharedPreferences("shared preferences", 0));
                cartProducts = helper.getCartItems();
                cartProducts.add(item);
                new Helper().saveCartData(holder.cartbutton.getContext().getSharedPreferences("shared preferences", 0), cartProducts);
                Toast.makeText(holder.wishButton.getContext(), "Added to Cart!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public int getItemCount(){
        return blushModelList.size();
    }

}
