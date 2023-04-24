package com.example.you.utils;

import android.content.SharedPreferences;

import com.example.you.Model.BestSellModel;
import com.example.you.Model.CartModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Helper {

    private ArrayList<BestSellModel> favProducts;
    private ArrayList<CartModel> cartItems;
    public ArrayList<BestSellModel> getFavProducts() {
        return favProducts;
    }
    public ArrayList<CartModel> getCartItems() {
        return cartItems;
    }

    public void saveFavData(SharedPreferences sharedPreferences, ArrayList<BestSellModel> favList) {
        // method for saving the data in array list.
        // creating a variable for storing data in
        // shared preferences.
        //SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        favProducts = favList;
        // creating a variable for editor to
        // store data in shared preferences.
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // creating a new variable for gson.
        Gson gson = new Gson();

        // getting data from gson and storing it in a string.
        String json = gson.toJson(favProducts);

        // below line is to save data in shared
        // prefs in the form of string.
        editor.putString("fav", json);

        // below line is to apply changes
        // and save data in shared prefs.
        editor.apply();
    }

    public void loadFavouriteData(SharedPreferences sharedPreferences) {
        // method to load arraylist from shared prefs
        // initializing our shared prefs with name as
        // shared preferences.
        //SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);

        // creating a variable for gson.
        Gson gson = new Gson();

        // below line is to get to string present from our
        // shared prefs if not present setting it as null.
        String json = sharedPreferences.getString("fav", null);

        // below line is to get the type of our array list.
        Type type = new TypeToken<ArrayList<BestSellModel>>() {}.getType();

        // in below line we are getting data from gson
        // and saving it to our array list
        favProducts = gson.fromJson(json, type);

        // checking below if the array list is empty or not
        if (favProducts == null) {
            // if the array list is empty
            // creating a new array list.
            favProducts = new ArrayList<>();
        }
    }


    public void saveCartData(SharedPreferences sharedPreferences, ArrayList<CartModel> cartList_) {
        // method for saving the data in array list.
        // creating a variable for storing data in
        // shared preferences.
        //SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        cartItems = cartList_;
        // creating a variable for editor to
        // store data in shared preferences.
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // creating a new variable for gson.
        Gson gson = new Gson();

        // getting data from gson and storing it in a string.
        String json = gson.toJson(cartItems);

        // below line is to save data in shared
        // prefs in the form of string.
        editor.putString("cart", json);

        // below line is to apply changes
        // and save data in shared prefs.
        editor.apply();
    }

    public void loadCartData(SharedPreferences sharedPreferences) {
        // method to load arraylist from shared prefs
        // initializing our shared prefs with name as
        // shared preferences.
        //SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);

        // creating a variable for gson.
        Gson gson = new Gson();

        // below line is to get to string present from our
        // shared prefs if not present setting it as null.
        String json = sharedPreferences.getString("cart", null);

        // below line is to get the type of our array list.
        Type type = new TypeToken<ArrayList<CartModel>>() {}.getType();

        // in below line we are getting data from gson
        // and saving it to our array list
        cartItems = gson.fromJson(json, type);

        // checking below if the array list is empty or not
        if (cartItems == null) {
            // if the array list is empty
            // creating a new array list.
            cartItems = new ArrayList<>();
        }
    }

}
