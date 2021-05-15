package com.example.foodrescueapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.foodrescueapp.data.DatabaseHelper;
import com.example.foodrescueapp.model.Food;

import java.io.ByteArrayOutputStream;

public class AddFoodActivity extends AppCompatActivity {
    EditText addTitle, addDescription, addTime, addQuantity, addLocation;
    String title, description, date, time, quantity, location;
    byte[] image;
    CalendarView calendarView;
    ImageButton imageButton;
    DatabaseHelper db;
    Drawable drawable;
    private static final int RESULT_LOAD_IMAGE = 1;
    private static final String[] PERMISSION_EXTERNAL_STORAGE = new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    private static final int REQUEST_EXTERNAL_STORAGE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        addTitle = findViewById(R.id.addTitle);
        addDescription = findViewById(R.id.addDescription);
        addTime = findViewById(R.id.addTime);
        addQuantity = findViewById(R.id.addQuantity);
        addLocation = findViewById(R.id.addLocation);
        imageButton = findViewById(R.id.imageButton);
        calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date = year + "/" + month + "/" + dayOfMonth;
            }
        });
    }

    public void clickAddSave(View view) {
        db = new DatabaseHelper(this);
        title = addTitle.getText().toString();
        description = addDescription.getText().toString();
        time = addTime.getText().toString();
        quantity = addQuantity.getText().toString();
        location = addLocation.getText().toString();
        image = transformToByte(drawable);
        Food food = new Food(image, title, description, date, time, quantity, location);
        db.insertFood(food, 0);
        db.insertFood(food, 1);
        Toast.makeText(AddFoodActivity.this, "Add food successfully!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(AddFoodActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    public void clickAddImage(View view) {
        confirmationWindow();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        verifyStoragePermissions(AddFoodActivity.this);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {

            //Get the returned data, here is the user-defined URI address of Android
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            //Get data view of selected photos
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();

            //Gets the path of the selected picture from the data view
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
            BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
            drawable = bitmapDrawable;
            imageButton.setBackground(drawable);
            //imageButton.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }

    private void verifyStoragePermissions(Activity activity) {
        int permissionWrite = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permissionWrite != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSION_EXTERNAL_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
    }

    public void confirmationWindow(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("ALLOW", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, RESULT_LOAD_IMAGE);
            }
        });
        builder.setNegativeButton("DENY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setMessage("Allow the app to access phone, media and files on your device?");
        builder.show();
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
}