// SingleSongFragment.java
package com.example.musicplayer.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.musicplayer.MainActivity; // 你的播放界面类
import com.example.musicplayer.R;
import com.example.musicplayer.adapters.MusicAdapter;
import com.example.musicplayer.utils.Music;
import com.example.musicplayer.utils.MusicLoader;
import java.util.List;

public class SingleSongFragment extends Fragment {

    private RecyclerView recyclerView;
    private MusicAdapter musicAdapter;
    private List<Music> musicList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_single_song, container, false);
        recyclerView = rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        musicList = MusicLoader.loadMusic(getContext());
        musicAdapter = new MusicAdapter(musicList);
        recyclerView.setAdapter(musicAdapter);
        return rootView;
    }
}
