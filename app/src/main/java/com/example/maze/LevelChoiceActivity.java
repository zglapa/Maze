package com.example.maze;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class LevelChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_level_choice);
        unlockLevels();
    }

    @Override
    protected void onResume() {
        super.onResume();
        unlockLevels();
    }

    private void unlockLevels(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        int topLevel = sharedPreferences.getInt("TOP_LEVEL", 1);

        TableLayout tableLayout = findViewById(R.id.tableOfLevels);
        TableRow row1 = (TableRow) tableLayout.getChildAt(0);
        TableRow row2 = (TableRow) tableLayout.getChildAt(1);
        for(int i = 1; i <= topLevel; ++i){
            if(i <= 6){
                row1.getChildAt(i-1).setEnabled(true);
                row1.getChildAt(i-1).setBackgroundTintMode(null);
            }else{
                row2.getChildAt(i-7).setEnabled(true);
                row2.getChildAt(i-7).setBackgroundTintMode(null);
            }

        }
    }

    public void buttonClicked(View view){
        Intent intent = new Intent(this, GameActivity.class);
        Button levelButton = (Button)view;
        int levelButtonValue = Integer.parseInt(levelButton.getText().toString());
        intent.putExtra("LEVEL", levelButtonValue);
        startActivity(intent);
    }

}