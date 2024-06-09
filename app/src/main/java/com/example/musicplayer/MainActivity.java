package com.example.musicplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.annotation.SuppressLint;



import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button playBtton;
    SeekBar start,end;
    TextView startText,endText;
    MediaPlayer song;
    ImageView imageView;
    Animation animation;
    int SongTotalTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playBtton = findViewById(R.id.play);
        startText = findViewById(R.id.TextStart);
        endText = findViewById(R.id.TextEnd);
        imageView = findViewById(R.id.img);
        animation = AnimationUtils.loadAnimation(this,R.anim.rotation);

//        song
        song = MediaPlayer.create(this,R.raw.song);
        song.setLooping(true);
        song.seekTo(0);
        song.setVolume(0.5f,0.5f);
        SongTotalTime = song.getDuration();

        start = findViewById(R.id.PlayLine);
        start.setMax(SongTotalTime);
        start.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b){
                    song.seekTo(i);
                    start.setProgress(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        end = findViewById(R.id.volume);
        end.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b){
                    float volume = 1/100f;
                    song.setVolume(volume,volume);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(song != null){
                    try {
                        Message message = new Message();
                        message.what = song.getCurrentPosition();
                        handler.sendMessage(message);
                        Thread.sleep(1000);
                    }catch (InterruptedException ignored){

                    }
                }
            }
        }).start();

    }

    @SuppressLint("HandleerLeak")
    private Handler handler = new Handler(){
        @SuppressLint("SetText18n")
        @Override
        public void handleMessage(Message message){
            int SeekBarPosition = message.what;

//          update song seek bar
            start.setProgress(SeekBarPosition);

//          update labels
            String Time = createTimeText(SeekBarPosition);
            startText.setText(Time);

            String remainingTime = createTimeText(SongTotalTime - SeekBarPosition);
            endText.setText("-" + remainingTime);

        }
    };

    public String createTimeText(int time){
        String timeText;
        int min = time / 1000 / 60;
        int sec = time / 1000 % 60;
        timeText = min + ":";
        if (sec<10)timeText += "0";
        timeText += sec;
        return timeText;
    }

    public void PlayButton(View view){
        if (!song.isPlaying()){
            song.start();;
            imageView.startAnimation(animation);
            playBtton.setBackgroundResource(R.drawable.baseline_pause_circle_24);
        }else {
            song.pause();
            imageView.clearAnimation();
            playBtton.setBackgroundResource(R.drawable.baseline_play_circle_24);
        }
    }
}