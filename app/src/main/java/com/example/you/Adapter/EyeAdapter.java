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
import com.example.you.Model.CartModel;
import com.example.you.Model.EyeModel;
import com.example.you.R;
import com.example.you.utils.Helper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class EyeAdapter extends RecyclerView.Adapter<EyeAdapter.EyeViewHolder>{
    private Context context;
    private List<EyeModel> eyeModelList;
    private List<EyeModel> filteredEyeModelList;

    public static class EyeViewHolder extends RecyclerView.ViewHolder {
        public ImageView mimage;
        public TextView shade;
        public TextView price;
        public AppCompatImageButton wishButton;
        public AppCompatImageButton cartbutton;
        public EyeViewHolder(View itemView) {
            super(itemView);
            mimage = itemView.findViewById(R.id.imageView);
            shade = itemView.findViewById(R.id.textShade);
            price = itemView.findViewById(R.id.textPrice);
            wishButton = itemView.findViewById(R.id.imageButtonWish);
            cartbutton = itemView.findViewById(R.id.imageButtonCart);


        }
    }
    public EyeAdapter(ArrayList<EyeModel> list) {
        eyeModelList= list;
        this.filteredEyeModelList = eyeModelList;
    }



    @NonNull
    @Override
    public EyeAdapter.EyeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_foundation_view, parent, false);
        return new EyeAdapter.EyeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EyeAdapter.EyeViewHolder holder, int position) {
        EyeModel eyeModel= filteredEyeModelList.get(position);
        holder.shade.setText(eyeModel.getShade());
        holder.price.setText("$"+eyeModel.getPrice());
        Helper helper = new Helper();
        Picasso.get().load(eyeModel.getImage()).into(holder.mimage);
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
                item.setImage(eyeModel.getImage());
                item.setKey(eyeModel.getKey());
                item.setPrice(eyeModel.getPrice());
                item.setShade(eyeModel.getShade());
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
                item.setImage(eyeModel.getImage());
                item.setKey(eyeModel.getKey());
                item.setPrice(eyeModel.getPrice());
                item.setShade(eyeModel.getShade());
                item.setQuantity(1);
                item.setTotalPrice(1 * Float.parseFloat(eyeModel.getPrice()));
                helper.loadCartData(holder.wishButton.getContext().getSharedPreferences("shared preferences", 0));
                cartProducts = helper.getCartItems();
                cartProducts.add(item);
                new Helper().saveCartData(holder.cartbutton.getContext().getSharedPreferences("shared preferences", 0), cartProducts);
                Toast.makeText(holder.wishButton.getContext(), "Added to Cart!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public int getItemCount() {

        return eyeModelList.size();
    }

}
