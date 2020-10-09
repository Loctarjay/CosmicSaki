package com.example.cosmicsaki.content.drink.drinkviewholder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cosmicsaki.R;
import com.example.cosmicsaki.content.drink.drinkstorage.DrinkStorage;

public class DrinkViewHolder extends RecyclerView.ViewHolder {

    public TextView textView;

    public DrinkViewHolder (@NonNull View itemView) {
        super(itemView);
        LinearLayout linearLayout = (LinearLayout)itemView;
        textView = linearLayout.findViewById(R.id.customTitle);
    }

    public void setData(int position) {
        textView.setText(DrinkStorage.getDrink(position).getTitle());
    }
}
