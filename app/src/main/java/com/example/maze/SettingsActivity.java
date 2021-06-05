package com.example.maze;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_settings);
        findViewById(R.id.ballType1).setBackgroundTintMode(PorterDuff.Mode.MULTIPLY);
        findViewById(R.id.ballType2).setBackgroundTintMode(PorterDuff.Mode.MULTIPLY);
        findViewById(R.id.ballType3).setBackgroundTintMode(PorterDuff.Mode.MULTIPLY);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        switch(sharedPreferences.getInt("BALL_TYPE",1)){
            case 2:
                findViewById(R.id.ballType2).setBackgroundTintMode(null);
                break;
            case 3:
                findViewById(R.id.ballType3).setBackgroundTintMode(null);
                break;
            default:
                findViewById(R.id.ballType1).setBackgroundTintMode(null);
        }
    }

    public void type1BallClicked(View view){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("BALL_TYPE", 1);
        editor.apply();
        findViewById(R.id.ballType1).setBackgroundTintMode(null);
        findViewById(R.id.ballType2).setBackgroundTintMode(PorterDuff.Mode.MULTIPLY);
        findViewById(R.id.ballType3).setBackgroundTintMode(PorterDuff.Mode.MULTIPLY);
    }
    public void type2BallClicked(View view){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("BALL_TYPE", 2);
        editor.apply();
        findViewById(R.id.ballType1).setBackgroundTintMode(PorterDuff.Mode.MULTIPLY);
        findViewById(R.id.ballType2).setBackgroundTintMode(null);
        findViewById(R.id.ballType3).setBackgroundTintMode(PorterDuff.Mode.MULTIPLY);
    }
    public void type3BallClicked(View view){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("BALL_TYPE", 3);
        editor.apply();
        findViewById(R.id.ballType1).setBackgroundTintMode(PorterDuff.Mode.MULTIPLY);
        findViewById(R.id.ballType2).setBackgroundTintMode(PorterDuff.Mode.MULTIPLY);
        findViewById(R.id.ballType3).setBackgroundTintMode(null);

    }
}