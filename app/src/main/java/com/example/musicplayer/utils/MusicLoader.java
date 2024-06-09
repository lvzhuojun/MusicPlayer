package com.example.musicplayer.utils;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MusicLoader {

    public static List<Music> loadMusic(Context context) {
        List<Music> musicList = new ArrayList<>();
        // 添加一些假的音乐数据
        musicList.add(new Music("Song 1", "Artist 1", "Album 1"));
        musicList.add(new Music("Song 2", "Artist 2", "Album 2"));
        musicList.add(new Music("Song 3", "Artist 3", "Album 3"));

        // 以下代码可以保留用于从设备中加载实际的音乐文件
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.ALBUM},
                null,
                null,
                MediaStore.Audio.Media.DEFAULT_SORT_ORDER
        );

        if (cursor != null) {
            int titleIndex = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int artistIndex = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int albumIndex = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);

            while (cursor.moveToNext()) {
                String title = cursor.getString(titleIndex);
                String artist = cursor.getString(artistIndex);
                String album = cursor.getString(albumIndex);

                Music music = new Music(title, artist, album);
                musicList.add(music);
            }
            cursor.close();
        }

        return musicList;
    }

    public static List<String> loadAlbums(Context context) {
        Set<String> albumSet = new HashSet<>();
        // 添加一些假的专辑数据
        albumSet.add("Album 1");
        albumSet.add("Album 2");
        albumSet.add("Album 3");

        // 以下代码可以保留用于从设备中加载实际的专辑
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Audio.Media.ALBUM},
                null,
                null,
                MediaStore.Audio.Media.DEFAULT_SORT_ORDER
        );

        if (cursor != null) {
            int albumIndex = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);

            while (cursor.moveToNext()) {
                String album = cursor.getString(albumIndex);
                albumSet.add(album);
            }
            cursor.close();
        }

        return new ArrayList<>(albumSet);
    }

    public static List<String> loadSingers(Context context) {
        Set<String> singerSet = new HashSet<>();
        // 添加一些假的歌手数据
        singerSet.add("Artist 1");
        singerSet.add("Artist 2");
        singerSet.add("Artist 3");

        // 以下代码可以保留用于从设备中加载实际的歌手
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Audio.Media.ARTIST},
                null,
                null,
                MediaStore.Audio.Media.DEFAULT_SORT_ORDER
        );

        if (cursor != null) {
            int artistIndex = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);

            while (cursor.moveToNext()) {
                String artist = cursor.getString(artistIndex);
                singerSet.add(artist);
            }
            cursor.close();
        }

        return new ArrayList<>(singerSet);
    }
}
