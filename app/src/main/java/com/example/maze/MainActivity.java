package com.example.maze;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
    }

    public void launchLevelChoiceActivity(View view){
        Intent intent = new Intent(this, LevelChoiceActivity.class);
        startActivity(intent);
    }

    public void launchSettingsActivity(View view){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void launchHallOfFameActivity(View view){
        Intent intent = new Intent(this, HallOfFameActivity.class);
        startActivity(intent);
    }


}