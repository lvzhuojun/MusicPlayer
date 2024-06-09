// MusicAdapter.java
package com.example.musicplayer.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.musicplayer.MainActivity; // 你的播放界面类
import com.example.musicplayer.R;
import com.example.musicplayer.utils.Music;
import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder> {

    private List<Music> musicList;
    private Context context; // 添加一个上下文成员变量

    public MusicAdapter(List<Music> musicList) {
        this.musicList = musicList;
    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext(); // 初始化上下文对象
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music, parent, false);
        return new MusicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {
        Music music = musicList.get(position);
        holder.title.setText(music.getTitle());
        holder.artist.setText(music.getArtist());
        holder.album.setText(music.getAlbum());

        // 为列表项设置点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建一个意图跳转到播放界面
                Intent intent = new Intent(context, MainActivity.class); // 替换成你的播放界面类
                // 在这里添加传递歌曲信息的代码，例如：intent.putExtra("music_id", music.getId());
                context.startActivity(intent); // 启动播放界面
            }
        });
    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }

    public static class MusicViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView artist;
        public TextView album;

        public MusicViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            artist = itemView.findViewById(R.id.artist);
            album = itemView.findViewById(R.id.album);
        }
    }
}
