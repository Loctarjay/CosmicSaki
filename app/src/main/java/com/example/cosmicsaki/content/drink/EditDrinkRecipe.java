package com.example.cosmicsaki.content.drink;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cosmicsaki.R;

public class EditDrinkRecipe extends AppCompatActivity implements View.OnClickListener {

    private EditText editTitle, editComponent, editInstructions;
    private Button save, cancel;
    private Bundle bundle;
    private Boolean editing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drink_add_new);

//        editTitle = findViewById(R.id.drinkTitle);
//        editComponent = findViewById(R.id.drinkComponents);
//        editInstructions = findViewById(R.id.drinkInstructions);
//        save = findViewById(R.id.saveChanges);
//        save.setOnClickListener(this);
//        cancel = findViewById(R.id.cancel);
//        cancel.setOnClickListener(this);
//        bundle = getIntent().getExtras();
//        if (bundle != null) {
//            editTitle.setText(bundle.getString(DrinkRecyclerView.title));
//            editComponent.setText(bundle.getString(DrinkRecyclerView.compo));
//            editInstructions.setText(bundle.getString(DrinkRecyclerView.instruct));
//            editing = true;
//        }


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case (R.id.saveChanges):
//                if (!editing) {
//                    DrinkStorage.getList().add(new Drink(
//                            editTitle.getText().toString(),
//                            editComponent.getText().toString(),
//                            editInstructions.getText().toString()));
//                    finish();
//                } else {
//                    DrinkStorage.getList().set(bundle.getInt(DrinkRecyclerView.pos),
//                            new Drink(editTitle.getText().toString(),
//                                    editComponent.getText().toString(),
//                                    editInstructions.getText().toString()));
//                    finish();
//                }
//                finish();
//                break;
//            case R.id.cancel:
//                finish();
//                break;
        }
    }
}
