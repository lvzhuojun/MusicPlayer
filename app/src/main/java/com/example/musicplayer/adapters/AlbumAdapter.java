package com.example.musicplayer.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.musicplayer.R;
import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {

    private List<String> albumList;

    public AlbumAdapter(List<String> albumList) {
        this.albumList = albumList;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album, parent, false);
        return new AlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        String album = albumList.get(position);
        holder.albumName.setText(album);

        holder.itemView.setOnClickListener(v -> {
            // 跳转到专辑详情界面
            // Intent intent = new Intent(holder.itemView.getContext(), AlbumDetailActivity.class);
            // intent.putExtra("ALBUM_NAME", album);
            // holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public static class AlbumViewHolder extends RecyclerView.ViewHolder {
        public TextView albumName;

        public AlbumViewHolder(View itemView) {
            super(itemView);
            albumName = itemView.findViewById(R.id.album_name);
        }
    }
}
