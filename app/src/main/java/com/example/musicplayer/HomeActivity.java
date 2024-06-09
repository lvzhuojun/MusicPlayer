package com.example.musicplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    private Button playButton;
    private SeekBar startSeekBar, volumeSeekBar;
    private TextView startText, endText;
    private MediaPlayer song;
    private int songTotalTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        playButton = findViewById(R.id.play);
        startText = findViewById(R.id.TextStart);
        endText = findViewById(R.id.TextEnd);

        // 初始化 MediaPlayer
        song = MediaPlayer.create(this, R.raw.song);
        song.setLooping(true);
        song.seekTo(0);
        song.setVolume(0.5f, 0.5f);
        songTotalTime = song.getDuration();

        // 初始化 SeekBar
        startSeekBar = findViewById(R.id.PlayLine);
        startSeekBar.setMax(songTotalTime);
        startSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    song.seekTo(progress);
                    startSeekBar.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        volumeSeekBar = findViewById(R.id.volume);
        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float volume = progress / 100f;
                song.setVolume(volume, volume);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // 更新 SeekBar 和 TextView
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (song != null) {
                    try {
                        Message message = new Message();
                        message.what = song.getCurrentPosition();
                        handler.sendMessage(message);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int currentPosition = msg.what;

            // 更新 SeekBar
            startSeekBar.setProgress(currentPosition);

            // 更新时间标签
            String elapsedTime = createTimeLabel(currentPosition);
            startText.setText(elapsedTime);

            String remainingTime = createTimeLabel(songTotalTime - currentPosition);
            endText.setText("- " + remainingTime);
        }
    };

    public String createTimeLabel(int time) {
        String timeLabel = "";
        int min = time / 1000 / 60;
        int sec = time / 1000 % 60;

        timeLabel = min + ":";
        if (sec < 10) timeLabel += "0";
        timeLabel += sec;

        return timeLabel;
    }

    public void PlayButton(View view) {
        if (!song.isPlaying()) {
            song.start();
            playButton.setBackgroundResource(R.drawable.baseline_pause_circle_24);
        } else {
            song.pause();
            playButton.setBackgroundResource(R.drawable.baseline_play_circle_24);
        }
    }

    public void openLocal(View view) {
        Intent intent = new Intent(this, LocalActivity.class);
        startActivity(intent);
    }

    public void openRecent(View view) {
        Intent intent = new Intent(this, RecentActivity.class);
        startActivity(intent);
    }

    public void openFavorite(View view) {
        Intent intent = new Intent(this, FavoriteActivity.class);
        startActivity(intent);
    }
}
