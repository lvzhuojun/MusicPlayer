package com.example.musicplayer.ui.main;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.musicplayer.R;
import com.example.musicplayer.adapters.SingerAdapter;
import com.example.musicplayer.utils.MusicLoader;
import java.util.List;

public class SingerFragment extends Fragment {

    private RecyclerView recyclerView;
    private SingerAdapter singerAdapter;
    private List<String> singerList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_singer, container, false);
        recyclerView = rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        singerList = MusicLoader.loadSingers(getContext());
        singerAdapter = new SingerAdapter(singerList);
        recyclerView.setAdapter(singerAdapter);
        return rootView;
    }
}
