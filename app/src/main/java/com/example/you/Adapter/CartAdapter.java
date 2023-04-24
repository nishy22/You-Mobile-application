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

import com.example.you.Model.CartModel;
import com.example.you.R;
import com.example.you.utils.Helper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class CartAdapter extends RecyclerView.Adapter<NewArriveAdapter.NewArriveViewHolder> {

    private Context context;
    private List<CartModel> list_;
    private List<CartModel> list;



    public static class NewArriveViewHolder extends RecyclerView.ViewHolder {


        public ImageView mimage;
        public TextView shade;
        public TextView price;

        public AppCompatImageButton wishButton;
        public AppCompatImageButton cartbutton;
        public NewArriveViewHolder(View itemView) {
            super(itemView);
            mimage = itemView.findViewById(R.id.imageView);
            shade = itemView.findViewById(R.id.textShade);
            price = itemView.findViewById(R.id.textPrice);

        }
    }

    public CartAdapter (ArrayList<CartModel> list) {
        this.list = list;
        this.list_ = list;

    }

    @NonNull
    @Override
    public NewArriveAdapter.NewArriveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_foundation_view, parent, false);
        return new NewArriveAdapter.NewArriveViewHolder(v);
        //return new FoundationViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_foundation_view,parent,false));
    }
    @Override
    public void onBindViewHolder(@NonNull NewArriveAdapter.NewArriveViewHolder holder, int position) {

        CartAdapter adapter = this;
        int index = position;

        holder.wishButton.setVisibility(View.INVISIBLE);
        holder.cartbutton.setImageResource(R.drawable.ic_delete_foreground);

        CartModel model = list.get(position);
        holder.shade.setText(model.getShade());
        holder.price.setText(model.getQuantity()+" * "+"$"+model.getPrice());
        Picasso.get().load(model.getImage()).into(holder.mimage);
        Helper helper = new Helper();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.cartbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                ArrayList<CartModel> listP;
                helper.loadCartData(holder.cartbutton.getContext().getSharedPreferences("shared preferences", 0));
                listP = helper.getCartItems();
                listP.remove(index);
                helper.saveCartData(holder.cartbutton.getContext().getSharedPreferences("shared preferences", 0),listP);
                list = listP;
                list_ = listP;
                adapter.notifyDataSetChanged();
                Toast.makeText(holder.wishButton.getContext(), "Item Removed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
