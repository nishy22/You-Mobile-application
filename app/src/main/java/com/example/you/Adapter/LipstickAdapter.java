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
import com.example.you.Model.LipstickModel;
import com.example.you.R;
import com.example.you.utils.Helper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class LipstickAdapter extends RecyclerView.Adapter<LipstickAdapter.LipstickViewHolder> {

    private Context context;
    private List<LipstickModel> lipstickModelList;
    private List<LipstickModel> filteredLipstickModelList;
    public static class LipstickViewHolder extends RecyclerView.ViewHolder {
        public ImageView mimage;
        public TextView shade;
        public TextView price;
        public AppCompatImageButton wishButton;
        public AppCompatImageButton cartbutton;

        public LipstickViewHolder(View itemView) {
            super(itemView);
            mimage = itemView.findViewById(R.id.imageView);
            shade = itemView.findViewById(R.id.textShade);
            price = itemView.findViewById(R.id.textPrice);
            wishButton = itemView.findViewById(R.id.imageButtonWish);
            cartbutton = itemView.findViewById(R.id.imageButtonCart);


        }
    }

    public LipstickAdapter(ArrayList<LipstickModel> list) {
        lipstickModelList = list;
        this.filteredLipstickModelList = lipstickModelList;
    }

    @NonNull
    @Override
    public LipstickAdapter.LipstickViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_foundation_view, parent, false);
        return new LipstickAdapter.LipstickViewHolder(v);
        //return new FoundationViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_foundation_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull LipstickAdapter.LipstickViewHolder holder, int position) {

        LipstickModel lipstickModel = filteredLipstickModelList.get(position);
        holder.shade.setText(lipstickModel.getShade());
        holder.price.setText("$" + lipstickModel.getPrice());
        Helper helper = new Helper();
        Picasso.get().load(lipstickModel.getImage()).into(holder.mimage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.wishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<BestSellModel> favProducts;
                BestSellModel item = new BestSellModel();
                item.setImage(lipstickModel.getImage());
                item.setKey(lipstickModel.getKey());
                item.setPrice(lipstickModel.getPrice());
                item.setShade(lipstickModel.getShade());
                helper.loadFavouriteData(holder.wishButton.getContext().getSharedPreferences("shared preferences", 0));
                favProducts = helper.getFavProducts();
                favProducts.add(item);
                helper.saveFavData(holder.wishButton.getContext().getSharedPreferences("shared preferences", 0), favProducts);
                Toast.makeText(holder.wishButton.getContext(), "Added to Wishlist!", Toast.LENGTH_SHORT).show();
            }
        });
        holder.cartbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<CartModel> cartProducts;
                CartModel item = new CartModel();
                item.setImage(lipstickModel.getImage());
                item.setKey(lipstickModel.getKey());
                item.setPrice(lipstickModel.getPrice());
                item.setShade(lipstickModel.getShade());
                item.setQuantity(1);
                item.setTotalPrice(1 * Float.parseFloat(lipstickModel.getPrice()));
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
        return lipstickModelList.size();
    }


}


