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
import com.example.you.R;
import com.example.you.utils.Helper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BestSellAdapter extends RecyclerView.Adapter<BestSellAdapter.BestSellerViewHolder> {

    private Context context;
    private List<BestSellModel> bestSellModelList;
    private List<BestSellModel> filteredBestSellModelList;
    int selectedList = 0;
    public static class BestSellerViewHolder extends RecyclerView.ViewHolder {
        public ImageView mimage;
        public TextView shade;
        public TextView price;
        public AppCompatImageButton wishButton;
        public AppCompatImageButton cartbutton;

        public BestSellerViewHolder(View itemView){
            super(itemView);
            mimage=itemView.findViewById(R.id.imageView);
            shade = itemView.findViewById(R.id.textShade);
            price = itemView.findViewById(R.id.textPrice);
            wishButton = itemView.findViewById(R.id.imageButtonWish);
            cartbutton = itemView.findViewById(R.id.imageButtonCart);

        }
    }



    public BestSellAdapter(ArrayList<BestSellModel> list, int slected) {
        bestSellModelList = list;
        selectedList = slected;
        this.filteredBestSellModelList = bestSellModelList;
    }

    @NonNull
    @Override
    public BestSellerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_foundation_view, parent, false);
        return new BestSellAdapter.BestSellerViewHolder(v);
        //return new FoundationViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_foundation_view,parent,false));
    }
    @Override
    public void onBindViewHolder(@NonNull BestSellAdapter.BestSellerViewHolder holder, int position) {
        BestSellAdapter adapter = this;
        int index = position;
        if(selectedList == 1){
            holder.wishButton.setVisibility(View.INVISIBLE);
            holder.cartbutton.setImageResource(R.drawable.ic_delete_foreground);

        }
        BestSellModel bestSellModel = filteredBestSellModelList.get(index);
        holder.shade.setText(bestSellModel.getShade());
        holder.price.setText("$" + bestSellModel.getPrice());
        Picasso.get().load(bestSellModel.getImage()).into(holder.mimage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        Helper helper = new Helper();
        holder.wishButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ArrayList<BestSellModel> favProducts;
                BestSellModel item= new BestSellModel();
                item.setImage(bestSellModel.getImage());
                item.setKey(bestSellModel.getKey());
                item.setPrice(bestSellModel.getPrice());
                item.setShade(bestSellModel.getShade());
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
                if(selectedList == 0) {

                    ArrayList<CartModel> cartProducts;
                    CartModel item = new CartModel();
                    item.setImage(bestSellModel.getImage());
                    item.setKey(bestSellModel.getKey());
                    item.setPrice(bestSellModel.getPrice());
                    item.setShade(bestSellModel.getShade());
                    item.setQuantity(1);
                    item.setTotalPrice(1 * Float.parseFloat(bestSellModel.getPrice()));
                    helper.loadCartData(holder.wishButton.getContext().getSharedPreferences("shared preferences", 0));
                    cartProducts = helper.getCartItems();
                    cartProducts.add(item);
                    new Helper().saveCartData(holder.cartbutton.getContext().getSharedPreferences("shared preferences", 0), cartProducts);
                    Toast.makeText(holder.wishButton.getContext(), "Added to Cart!", Toast.LENGTH_SHORT).show();
                }
                else{
                    ArrayList<BestSellModel> listP;
                    helper.loadFavouriteData(holder.cartbutton.getContext().getSharedPreferences("shared preferences", 0));
                    listP = helper.getFavProducts();
                    listP.remove(index);
                    helper.saveFavData(holder.cartbutton.getContext().getSharedPreferences("shared preferences", 0),listP);
                    bestSellModelList = listP;
                    adapter.notifyDataSetChanged();
                    Toast.makeText(holder.wishButton.getContext(), "Item Removed!", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return bestSellModelList.size();
    }


}
