package com.example.musicplayer;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.LifecycleService;

public class NotificationService extends LifecycleService {

    private static final int NOTIFICATION_ID = 1001;
    private static final String CHANNEL_ID = "MusicPlayerNotification";

    private NotificationCompat.Builder notificationBuilder;

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
        notificationBuilder = buildNotification();
        startForeground(NOTIFICATION_ID, notificationBuilder.build());
    }

    private NotificationCompat.Builder buildNotification() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification_icon)
                .setContentTitle("Music Player")
                .setContentText("Playing song title")
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(false);

        return notificationBuilder;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Music Player";
            String description = "Music Player Notification Channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        boolean isPlaying = intent.getBooleanExtra("isPlaying", false);
        updateNotification(isPlaying);
        return super.onStartCommand(intent, flags, startId);
    }

    private void updateNotification(boolean isPlaying) {
        if (isPlaying) {
            notificationBuilder.setContentText("Playing");
            notificationBuilder.setSmallIcon(R.drawable.ic_play);
        } else {
            notificationBuilder.setContentText("Paused");
            notificationBuilder.setSmallIcon(R.drawable.ic_pause);
        }

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());
    }
}
