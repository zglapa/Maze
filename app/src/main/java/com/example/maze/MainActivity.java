package com.example.maze;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchGameActivity(View view){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void launchSettingsActivity(View view){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

}