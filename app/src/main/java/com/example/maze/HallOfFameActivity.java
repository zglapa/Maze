package com.example.maze;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.maze.game.database.AppDatabase;
import com.example.maze.game.database.Player;
import com.example.maze.game.database.PlayerAdapter;

import java.util.List;

public class HallOfFameActivity extends AppCompatActivity {
    PlayerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_of_fame);

        initRecylerView();
        initSpinner();

    }
    private void initSpinner(){
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.levels_array, R.layout.custom_spinner);
        arrayAdapter.setDropDownViewResource(R.layout.custom_spinner);
        spinner.setDropDownWidth(400);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loadScoreList(position + 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void initRecylerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        adapter =new PlayerAdapter(this);
        recyclerView.setAdapter(adapter);
    }
    private void loadScoreList(int level){
        AppDatabase db = AppDatabase.getInstance(this.getApplicationContext());
        List<Player> players = db.playerDao().getAllOnLevel(level);
        adapter.setPlayers(players);
    }

}