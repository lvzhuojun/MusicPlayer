package com.example.musicplayer.ui.main;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.musicplayer.R;
import com.example.musicplayer.adapters.AlbumAdapter;
import com.example.musicplayer.utils.MusicLoader;
import java.util.List;

public class AlbumFragment extends Fragment {

    private RecyclerView recyclerView;
    private AlbumAdapter albumAdapter;
    private List<String> albumList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_album, container, false);
        recyclerView = rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        albumList = MusicLoader.loadAlbums(getContext());
        albumAdapter = new AlbumAdapter(albumList);
        recyclerView.setAdapter(albumAdapter);
        return rootView;
    }
}
