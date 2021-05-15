package com.example.foodrescueapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.foodrescueapp.model.Food;
import com.example.foodrescueapp.model.User;
import com.example.foodrescueapp.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + Util.TABLE_NAME_USER + " ("
                + Util.USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Util.USERNAME + " TEXT, "
                + Util.PASSWORD + " TEXT, "
                + Util.EMAIL + " TEXT, "
                + Util.PHONE + " TEXT, "
                + Util.ADDRESS + " TEXT)";
        String CREATE_FOOD_TABLE = "CREATE TABLE " + Util.TABLE_NAME_FOOD + " ("
                + Util.FOOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Util.PICTURE + " BLOB, "
                + Util.TITLE + " TEXT, "
                + Util.DESCRIPTION + " TEXT, "
                + Util.DATE + " TEXT, "
                + Util.TIME + " TEXT, "
                + Util.QUANTITY + " TEXT, "
                + Util.LOCATION + " TEXT)";
        String CREATE_LIST_TABLE = "CREATE TABLE " + Util.TABLE_NAME_MY_LIST + " ("
                + Util.FOOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Util.PICTURE + " BLOB, "
                + Util.TITLE + " TEXT, "
                + Util.DESCRIPTION + " TEXT, "
                + Util.DATE + " TEXT, "
                + Util.TIME + " TEXT, "
                + Util.QUANTITY + " TEXT, "
                + Util.LOCATION + " TEXT)";
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_FOOD_TABLE);
        db.execSQL(CREATE_LIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_USER_TABLE = " DROP TABLE IF EXISTS " + Util.TABLE_NAME_USER;
        String DROP_FOOD_TABLE = " DROP TABLE IF EXISTS " + Util.TABLE_NAME_FOOD;
        String DROP_LIST_TABLE = " DROP TABLE IF EXISTS " + Util.TABLE_NAME_MY_LIST;
        db.execSQL(DROP_USER_TABLE, new String[]{Util.TABLE_NAME_USER});
        db.execSQL(DROP_FOOD_TABLE, new String[]{Util.TABLE_NAME_FOOD});
        db.execSQL(DROP_LIST_TABLE, new String[]{Util.TABLE_NAME_MY_LIST});
        onCreate(db);
    }

    public long insertUser(User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.USERNAME, user.getUsername());
        contentValues.put(Util.PASSWORD, user.getPassword());
        contentValues.put(Util.EMAIL, user.getEmail());
        contentValues.put(Util.PHONE, user.getPhone());
        contentValues.put(Util.ADDRESS, user.getAddress());
        long newRowId = db.insert(Util.TABLE_NAME_USER, null, contentValues);
        db.close();
        return newRowId;
    }

    public long insertFood(Food food, int position){
        long newRowId;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.PICTURE, food.getFoodImage());
        contentValues.put(Util.TITLE, food.getFoodTitle());
        contentValues.put(Util.DESCRIPTION, food.getFoodDescription());
        contentValues.put(Util.DATE, food.getFoodDate());
        contentValues.put(Util.TIME, food.getFoodTime());
        contentValues.put(Util.QUANTITY, food.getFoodQuantity());
        contentValues.put(Util.LOCATION, food.getFoodLocation());
        if (position == 0) {
            newRowId = db.insert(Util.TABLE_NAME_FOOD, null, contentValues);
        }
        else {
            newRowId = db.insert(Util.TABLE_NAME_MY_LIST, null, contentValues);
        }
        db.close();
        return newRowId;
    }

    public boolean fetchUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_NAME_USER, new String[]{Util.USER_ID}, Util.USERNAME + "=? and " + Util.PASSWORD + "=?",
                new String[] {username, password}, null, null, null);
        int numberOfRows = cursor.getCount();
        db.close();
        if (numberOfRows > 0)
            return  true;
        else
            return false;
    }

    public List<User> fetchAllUsers(){
        List<User> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = " Select * FROM " + Util.TABLE_NAME_USER;
        Cursor cursor = db.rawQuery(selectAll, null);
        if (cursor.moveToFirst()){
            do {
                User user = new User();
                user.setId(cursor.getInt(0));
                user.setUsername(cursor.getString(1));
                user.setPassword(cursor.getString(2));
                user.setEmail(cursor.getString(3));
                user.setPhone(cursor.getString(4));
                user.setAddress(cursor.getString(5));
                userList.add(user);
            }while (cursor.moveToNext());
        }
        return userList;
    }

    public Food fetchFood(int position){
        Food food = new Food();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = " Select * FROM " + Util.TABLE_NAME_FOOD;
        Cursor cursor = db.rawQuery(selectAll, null);
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getInt(0) == position) {
                    food.setFoodTitle(cursor.getString(2));
                    food.setFoodTime(cursor.getString(5));
                    food.setFoodQuantity(cursor.getString(6));
                    food.setFoodLocation(cursor.getString(7));
                }
            }while (cursor.moveToNext());
        }
        return food;
    }

    public List<Food> fetchAllFoods(){
        List<Food> foodList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = " Select * FROM " + Util.TABLE_NAME_FOOD;
        Cursor cursor = db.rawQuery(selectAll, null);
        if (cursor.moveToFirst()){
            do {
                Food food = new Food();
                food.setFoodId(cursor.getInt(0));
                food.setFoodImage(cursor.getBlob(1));
                food.setFoodTitle(cursor.getString(2));
                food.setFoodDescription(cursor.getString(3));
                food.setFoodDate(cursor.getString(4));
                food.setFoodTime(cursor.getString(5));
                food.setFoodQuantity(cursor.getString(6));
                food.setFoodLocation(cursor.getString(7));
                foodList.add(food);
            }while (cursor.moveToNext());
        }
        return foodList;
    }

    public List<Food> fetchMyListFoods(){
        List<Food> foodList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = " Select * FROM " + Util.TABLE_NAME_MY_LIST;
        Cursor cursor = db.rawQuery(selectAll, null);
        if (cursor.moveToFirst()){
            do {
                Food food = new Food();
                food.setFoodId(cursor.getInt(0));
                food.setFoodImage(cursor.getBlob(1));
                food.setFoodTitle(cursor.getString(2));
                food.setFoodDescription(cursor.getString(3));
                food.setFoodDate(cursor.getString(4));
                food.setFoodTime(cursor.getString(5));
                food.setFoodQuantity(cursor.getString(6));
                food.setFoodLocation(cursor.getString(7));
                foodList.add(food);
            }while (cursor.moveToNext());
        }
        return foodList;
    }
}
