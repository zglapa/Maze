package com.example.maze;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class LevelChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_level_choice);
    }

    public void buttonClicked(View view){
        Intent intent = new Intent(this, GameActivity.class);
        Button levelButton = (Button)view;
        int levelButtonValue = Integer.parseInt(levelButton.getText().toString());
        intent.putExtra("LEVEL", levelButtonValue);
        startActivity(intent);
    }

}