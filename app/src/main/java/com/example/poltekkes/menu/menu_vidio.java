package com.example.poltekkes.menu;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.poltekkes.R;
import com.github.squti.guru.Guru;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class menu_vidio extends AppCompatActivity {

    private TextView txtSumber;
    private YouTubePlayerView youtubePlayerView;
    String link,jenis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_vidio);
        initView();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getLifecycle().addObserver(youtubePlayerView);
        jenis = Guru.getString("jenis", "false");
        link = Guru.getString("link", "false");
        txtSumber.setText(link);
        youtubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = jenis;
                youTubePlayer.loadVideo(videoId, 0);
            }
        });

    }

    private void initView() {
        txtSumber = findViewById(R.id.txt_sumber);
        youtubePlayerView = findViewById(R.id.youtube_player_view);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}