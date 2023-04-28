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
import com.example.you.Model.ConcelarModel;
import com.example.you.R;
import com.example.you.utils.Helper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ConcelarAdapter  extends RecyclerView.Adapter<ConcelarAdapter.ConcelarViewHolder>{
    private Context context;
    private List<ConcelarModel> concelarModelList;
    private List<ConcelarModel> filteredConcelarModelList;


    public static class ConcelarViewHolder extends RecyclerView.ViewHolder {
        public ImageView mimage;
        public TextView shade;
        public TextView price;
        public AppCompatImageButton wishButton;
        public AppCompatImageButton cartbutton;

        public ConcelarViewHolder(View itemView){
            super(itemView);
            mimage=itemView.findViewById(R.id.imageView);
            shade = itemView.findViewById(R.id.textShade);
            price = itemView.findViewById(R.id.textPrice);
            wishButton = itemView.findViewById(R.id.imageButtonWish);
            cartbutton = itemView.findViewById(R.id.imageButtonCart);



        }
    }
    public ConcelarAdapter(ArrayList<ConcelarModel> list) {
        concelarModelList = list;
        this.filteredConcelarModelList = concelarModelList;
    }
    @NonNull
    @Override
    public ConcelarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_foundation_view, parent, false);
        return new ConcelarAdapter.ConcelarViewHolder(v);
        //return new FoundationViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_foundation_view,parent,false));
    }


    @Override
    public void onBindViewHolder(@NonNull ConcelarViewHolder holder, int position) {
        ConcelarModel concelarModel= filteredConcelarModelList.get(position);
        holder.shade.setText(concelarModel.getShade());
        holder.price.setText("$"+concelarModel.getPrice());
        Helper helper = new Helper();
        Picasso.get().load(concelarModel.getImage()).into(holder.mimage);
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
                item.setImage(concelarModel.getImage());
                item.setKey(concelarModel.getKey());
                item.setPrice(concelarModel.getPrice());
                item.setShade(concelarModel.getShade());
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
                item.setImage(concelarModel.getImage());
                item.setKey(concelarModel.getKey());
                item.setPrice(concelarModel.getPrice());
                item.setShade(concelarModel.getShade());
                item.setQuantity(1);
                item.setTotalPrice(1 * Float.parseFloat(concelarModel.getPrice()));
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
        return concelarModelList.size();
    }
}
