package com.example.tourismapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TourismVerticalAdapter.OnPlaceClickListener {
    RecyclerView recyclerViewHorizontal;
    TourismHorizontalAdapter tourismHorizontalAdapter;
    List<TourismHorizontal> tourismHorizontalList = new ArrayList<>();
    int [] imageHorizontalList = {R.drawable.daming_palace_horizontal, R.drawable.dufu_cottage_horizontal, R.drawable.terracotta_army_horizontal,R.drawable.the_forbidden_city_horizontal, R.drawable.the_great_wall_horizontal};
    RecyclerView recyclerViewVertical;
    TourismVerticalAdapter tourismVerticalAdapter;
    List<TourismVertical> tourismVerticalList = new ArrayList<>();
    int [] imageVerticalList = {R.drawable.daming_palace_vertical,R.drawable.dufu_cottage_vertical,R.drawable.terracotta_army_vertical,R.drawable.the_forbidden_city_vertical,R.drawable.the_great_wall_vertical};
    String [] placeNameList = {"Daming Palace","Du Fu Cottage","Terracotta Army","The Forbidden City","The Great Wall"};
    String [] placeLocationList ={"Xi'an, China", "Chengdu, China", "Xi'an, China", "Beijing, China", "Beijing, China"};
    String [] placeEvaluateList ={"Daming Palace is the imperial palace of Tang Dynasty in ancient China, which has a long history and traditional culture.",
            "Du Fu was a famous poet in the Tang Dynasty in ancient China, and his thatched cottage was his residence in his later years.",
            "Terracotta warriors and horses were built during the Qin Dynasty in ancient China, which is a shocking miracle.",
            "The Forbidden City is the palace and administrative place of the emperors in the Ming and Qing Dynasties in ancient China.",
            "The Great Wall is one of the seven wonders of the world and a military fortification in ancient China."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewHorizontal = findViewById(R.id.recyclerViewHorizontal);
        tourismHorizontalAdapter = new TourismHorizontalAdapter(tourismHorizontalList, this);
        recyclerViewHorizontal.setAdapter(tourismHorizontalAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewHorizontal.setLayoutManager(layoutManager);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        for (int i = 0; i < imageHorizontalList.length; i++){
            TourismHorizontal tourismHorizontal = new TourismHorizontal(i, imageHorizontalList[i]);
            tourismHorizontalList.add(tourismHorizontal);
        }

        recyclerViewVertical = findViewById(R.id.recyclerViewVertical);
        tourismVerticalAdapter = new TourismVerticalAdapter(tourismVerticalList,this, this);
        recyclerViewVertical.setAdapter(tourismVerticalAdapter);
        recyclerViewVertical.setLayoutManager(new LinearLayoutManager(this));
        for (int i = 0; i < imageVerticalList.length; i++){
            TourismVertical tourismVertical = new TourismVertical(i, imageVerticalList[i], placeNameList[i], placeLocationList[i], placeEvaluateList[i]);
            tourismVerticalList.add(tourismVertical);
        }
    }

    @Override
    public void onItemClick(int position){
        Fragment fragment;
        switch (position){
            case 0:
                fragment = new Fragment0();
                break;
            case 1:
                fragment = new Fragment1();
                break;
            case 2:
                fragment = new Fragment2();
                break;
            case 3:
                fragment = new Fragment3();
                break;
            case 4:
                fragment = new Fragment4();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + position);
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragment).commit();
    }
}