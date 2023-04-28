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
import com.example.you.Model.EyeshadowModel;
import com.example.you.R;
import com.example.you.utils.Helper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class EyeshadowAdapter extends RecyclerView.Adapter<EyeshadowAdapter.EyeshadowViewHolder> {
    private Context context;
    private List<EyeshadowModel> eyeshadowModelList;
    private List<EyeshadowModel> filteredEyeshadowModelList;

    public static class EyeshadowViewHolder extends RecyclerView.ViewHolder {
        public ImageView mimage;
        public TextView shade;
        public TextView price;
        public AppCompatImageButton wishButton;
        public AppCompatImageButton cartbutton;
        public EyeshadowViewHolder(View itemView) {
            super(itemView);
            mimage = itemView.findViewById(R.id.imageView);
            shade = itemView.findViewById(R.id.textShade);
            price = itemView.findViewById(R.id.textPrice);
            wishButton = itemView.findViewById(R.id.imageButtonWish);
            cartbutton = itemView.findViewById(R.id.imageButtonCart);


        }
    }
    public EyeshadowAdapter(ArrayList<EyeshadowModel> list) {
        eyeshadowModelList= list;
        this.filteredEyeshadowModelList = eyeshadowModelList;
    }



    @NonNull
    @Override
    public EyeshadowAdapter.EyeshadowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_foundation_view, parent, false);
        return new EyeshadowAdapter.EyeshadowViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EyeshadowAdapter.EyeshadowViewHolder holder, int position) {
        EyeshadowModel eyeshadowModel= filteredEyeshadowModelList.get(position);
        holder.shade.setText(eyeshadowModel.getShade());
        holder.price.setText("$"+eyeshadowModel.getPrice());
        Helper helper = new Helper();
        Picasso.get().load(eyeshadowModel.getImage()).into(holder.mimage);
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
                item.setImage(eyeshadowModel.getImage());
                item.setKey(eyeshadowModel.getKey());
                item.setPrice(eyeshadowModel.getPrice());
                item.setShade(eyeshadowModel.getShade());
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
                item.setImage(eyeshadowModel.getImage());
                item.setKey(eyeshadowModel.getKey());
                item.setPrice(eyeshadowModel.getPrice());
                item.setShade(eyeshadowModel.getShade());
                item.setQuantity(1);
                item.setTotalPrice(1 * Float.parseFloat(eyeshadowModel.getPrice()));
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

        return eyeshadowModelList.size();
    }
}
