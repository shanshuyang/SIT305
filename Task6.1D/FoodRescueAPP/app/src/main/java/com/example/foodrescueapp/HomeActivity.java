package com.example.foodrescueapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodrescueapp.data.DatabaseHelper;
import com.example.foodrescueapp.model.Food;
import com.example.foodrescueapp.model.User;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    Spinner spinner;
    TextView titleTextView;
    RecyclerView recyclerView;
    FoodRecyclerViewAdapter foodRecyclerViewAdapter, listRecyclerViewAdapter;
    AccountRecyclerViewAdapter accountRecyclerViewAdapter;
    List<Food> foodList = new ArrayList<>();
    List<User> accountList = new ArrayList<>();
    List<Food> myList = new ArrayList<>();
    int[] imageIntList = {};
    String[] titleList = {}, descriptionList = {}, dateList = {}, timeList = {}, quantityList = {}, locationList = {};
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        spinner = findViewById(R.id.spinner);
        titleTextView = findViewById(R.id.titleTextView);
        db = new DatabaseHelper(this);
        initializeFood();
        recyclerView = findViewById(R.id.recyclerView);
        foodList = db.fetchAllFoods();
        myList = db.fetchMyListFoods();
        accountList = db.fetchAllUsers();
        foodRecyclerViewAdapter = new FoodRecyclerViewAdapter(foodList, this);
        listRecyclerViewAdapter = new FoodRecyclerViewAdapter(myList, this);
        accountRecyclerViewAdapter = new AccountRecyclerViewAdapter(accountList, this);
        recyclerView.setAdapter(foodRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        foodRecyclerViewAdapter.setOnItemClickListener(new FoodRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, FoodRecyclerViewAdapter.ViewName viewName, int position) {
                if (v.getId() == R.id.shareImageButton) {
                    Food food = db.fetchFood(position + 1); //position start from 0, database id start form 1
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_TEXT, "Title: " + food.getFoodTitle() + "\n"
                            + "Time: " + food.getFoodTime() + "\n"
                            + "Quantity: " + food.getFoodQuantity() + "\n"
                            + "Location: " + food.getFoodLocation());
                    intent.setType("text/plain");
                    Intent.createChooser(intent,"Share via");
                    startActivity(intent);
                }
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        recyclerView.setAdapter(foodRecyclerViewAdapter);
                        break;
                    case 1:
                        recyclerView.setAdapter(accountRecyclerViewAdapter);
                        break;
                    case 2:
                        recyclerView.setAdapter(listRecyclerViewAdapter);
                        break;
                    default:
                        break;
                }
                titleTextView.setText(spinner.getItemAtPosition(position).toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }



    public void initializeFood(){
        foodList = db.fetchAllFoods();
        if (foodList.size() == 0) {
            List<byte[]> imageList = new ArrayList<>();
            imageIntList = new int[]{R.drawable.pork, R.drawable.beef, R.drawable.carrot, R.drawable.pumpkin, R.drawable.drumsticks, R.drawable.radish};
            for (int i = 0; i < imageIntList.length; i++) {
                Drawable drawable = getResources().getDrawable(imageIntList[i]);
                imageList.add(transformToByte(drawable));
            }
            titleList = new String[]{"pork", "beef", "carrot", "pumpkin", "drumsticks", "radish"};
            descriptionList = new String[]{"fresh and good", "fresh and good", "fresh and good", "fresh and good", "fresh and good", "fresh and good"};
            dateList = new String[]{"2021/5/6", "2021/5/5", "2021/5/6", "2021/5/7", "2021/5/7", "2021/5/8"};
            timeList = new String[]{"2021/5/9", "2021/5/8", "2021/5/9", "2021/5/9", "2021/5/10", "2021/5/12"};
            quantityList = new String[]{"2kg", "1.5kg", "3kg", "4kg", "0.5kg", "1kg"};
            locationList = new String[]{"5 Xinghan Road", "456 Chengxin Road", "124 Renmin North Road", "24 Hongxing Road", "3 Qianfeng Road", "75 Fuxing Road"};
            for (int i = 0; i < titleList.length; i++) {
                Food food = new Food(imageList.get(i), titleList[i], descriptionList[i], dateList[i], timeList[i], quantityList[i], locationList[i]);
                db.insertFood(food, 0);
            }
            Toast.makeText(HomeActivity.this, "This is first time initialized", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(HomeActivity.this, "The food has been initialized", Toast.LENGTH_LONG).show();
        }
    }

    private byte[] transformToByte(Drawable drawable) {
        if(drawable == null) {
            return null;
        }
        BitmapDrawable bd = (BitmapDrawable) drawable;
        Bitmap bitmap = bd.getBitmap();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
        return os.toByteArray();
    }

    public void clickAddFood(View view) {
        Intent intent = new Intent(HomeActivity.this, AddFoodActivity.class);
        startActivity(intent);
    }

}