package com.example.musicplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button playButton;
    SeekBar playLine, volume;
    TextView startText, endText;
    MediaPlayer song;
    ImageView imageView;
    Animation animation;
    int songTotalTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = findViewById(R.id.play);
        startText = findViewById(R.id.TextStart);
        endText = findViewById(R.id.TextEnd);
        imageView = findViewById(R.id.img);
        animation = AnimationUtils.loadAnimation(this, R.anim.rotation);

        song = MediaPlayer.create(this, R.raw.song);
        song.setLooping(true);
        song.seekTo(0);
        song.setVolume(0.5f, 0.5f);
        songTotalTime = song.getDuration();

        playLine = findViewById(R.id.PlayLine);
        playLine.setMax(songTotalTime);
        playLine.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b) {
                    song.seekTo(i);
                    playLine.setProgress(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        volume = findViewById(R.id.volume);
        volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b) {
                    // 计算音量百分比
                    float volumeValue = i / 100.0f;
                    // 设置音量
                    song.setVolume(volumeValue, volumeValue);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (song != null) {
                    try {
                        Message message = new Message();
                        message.what = song.getCurrentPosition();
                        handler.sendMessage(message);
                        Thread.sleep(1000);
                    } catch (InterruptedException ignored) {}
                }
            }
        }).start();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            int seekBarPosition = message.what;

            // 更新歌曲进度条
            playLine.setProgress(seekBarPosition);

            // 更新标签
            String currentTime = createTimeText(seekBarPosition);
            startText.setText(currentTime);

            String remainingTime = createTimeText(songTotalTime - seekBarPosition);
            endText.setText("-" + remainingTime);
        }
    };

    public String createTimeText(int time) {
        String timeText;
        int min = time / 1000 / 60;
        int sec = time / 1000 % 60;
        timeText = min + ":";
        if (sec < 10) timeText += "0";
        timeText += sec;
        return timeText;
    }

    public void PlayButton(View view) {
        if (!song.isPlaying()) {
            song.start();
            imageView.startAnimation(animation);
            playButton.setBackgroundResource(R.drawable.baseline_pause_circle_24);
        } else {
            song.pause();
            imageView.clearAnimation();
            playButton.setBackgroundResource(R.drawable.baseline_play_circle_24);
        }
    }
}
