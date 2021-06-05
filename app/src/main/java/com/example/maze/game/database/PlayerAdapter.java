package com.example.maze.game.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maze.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {

    private final Context context;
    private List<Player> players;
    public PlayerAdapter(Context context){
        this.context = context;
    }
    public void setPlayers(List<Player> players){
        this.players = players;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row, parent, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PlayerAdapter.PlayerViewHolder holder, int position) {
        holder.nameTextView.setText(this.players.get(position).name);
        long time = this.players.get(position).time;
        String timeString = String.format(Locale.ENGLISH, "%02d:%02d:%03d", TimeUnit.MILLISECONDS.toMinutes(time),
                TimeUnit.MILLISECONDS.toSeconds(time) % TimeUnit.MINUTES.toSeconds(1),
                TimeUnit.MILLISECONDS.toMillis(time) % TimeUnit.SECONDS.toMillis(1));
        holder.timeTextView.setText(timeString);
    }

    @Override
    public int getItemCount() {
        if(this.players == null)return 0;
        return this.players.size();
    }
    public static class PlayerViewHolder extends RecyclerView.ViewHolder{
        TextView nameTextView;
        TextView timeTextView;

        public PlayerViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            timeTextView = itemView.findViewById(R.id.timeTextView);
        }
    }
}
