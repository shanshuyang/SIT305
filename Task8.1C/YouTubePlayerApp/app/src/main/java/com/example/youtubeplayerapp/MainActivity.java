package com.example.youtubeplayerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.youtube.player.YouTubeBaseActivity;

public class MainActivity extends YouTubeBaseActivity {
    EditText uRLEditText;
    String url;
    Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uRLEditText = findViewById(R.id.uRLEditText);
                url = uRLEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, VideoActivity.class);
                intent.putExtra("URL", url);
                startActivity(intent);
            }
        });
    }
}