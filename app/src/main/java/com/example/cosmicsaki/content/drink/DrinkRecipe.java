package com.example.cosmicsaki.content.drink;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cosmicsaki.R;
import com.example.cosmicsaki.content.UserView;
import com.example.cosmicsaki.content.drink.drinkstorage.DrinkStorage;

public class DrinkRecipe extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button addDrink, back;
    private DrinkStorage drinkStorage;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        recyclerView = findViewById(R.id.drinkRecyclerView);
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        adapter = new DrinkRecyclerView();
//        recyclerView.setAdapter(adapter);
//        drinkStorage = new DrinkStorage();
        addDrink = findViewById(R.id.addDrinkRecipe);
        addDrink.setOnClickListener(this);
        back = findViewById(R.id.back3);
        back.setOnClickListener(this);

        if (UserView.currentUserAccess.equalsIgnoreCase("admin") ||
                UserView.currentUserAccess.equalsIgnoreCase("mod")){

            addDrink.setVisibility(View.VISIBLE);
        }
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        adapter.notifyDataSetChanged();
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addDrinkRecipe:
//                Intent drinkRecipe = new Intent(this, EditDrinkRecipe.class);
//                startActivity(drinkRecipe);
                break;
            case R.id.back3:
                finish();
                addDrink.setVisibility(View.INVISIBLE);
                break;
        }
    }
}
