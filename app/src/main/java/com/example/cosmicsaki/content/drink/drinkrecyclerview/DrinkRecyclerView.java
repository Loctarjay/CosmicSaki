package com.example.cosmicsaki.content.drink.drinkrecyclerview;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cosmicsaki.R;
import com.example.cosmicsaki.content.drink.EditDrinkRecipe;
import com.example.cosmicsaki.content.drink.drinksmodel.Drink;
import com.example.cosmicsaki.content.drink.drinkstorage.DrinkStorage;
import com.example.cosmicsaki.content.drink.drinkviewholder.DrinkViewHolder;

import java.util.ArrayList;

public class DrinkRecyclerView  extends RecyclerView.Adapter<DrinkViewHolder> {

    private ArrayList<Drink> list;
    public static final String title = "title";
    public static final String compo = "components";
    public static final String instruct = "instructions";
    public static final String pos = "position";

    public DrinkRecyclerView() {
        this.list = DrinkStorage.getList();

    }

    @NonNull
    @Override
    public DrinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout ll = (LinearLayout) LayoutInflater.
                from(parent.getContext()).inflate(R.layout.drinks_custom_row, parent, false);
        return new DrinkViewHolder(ll);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkViewHolder holder, final int position) {
        holder.setData(position); // bind data to one row in the view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent textToChange = new Intent(v.getContext(), EditDrinkRecipe.class);
                textToChange.putExtra(title, DrinkStorage.getDrink(position).getTitle());
                textToChange.putExtra(compo, DrinkStorage.getDrink(position).getComponent());
                textToChange.putExtra(instruct, DrinkStorage.getDrink(position).getInstructions());
                textToChange.putExtra(pos, position);
                v.getContext().startActivity(textToChange);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
