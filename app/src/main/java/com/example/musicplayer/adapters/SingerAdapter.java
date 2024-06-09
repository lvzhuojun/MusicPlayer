package com.example.musicplayer.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.musicplayer.R;
import java.util.List;

public class SingerAdapter extends RecyclerView.Adapter<SingerAdapter.SingerViewHolder> {

    private List<String> singerList;

    public SingerAdapter(List<String> singerList) {
        this.singerList = singerList;
    }

    @NonNull
    @Override
    public SingerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_singer, parent, false);
        return new SingerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SingerViewHolder holder, int position) {
        String singer = singerList.get(position);
        holder.singerName.setText(singer);

        holder.itemView.setOnClickListener(v -> {
            // 跳转到歌手详情界面
            // Intent intent = new Intent(holder.itemView.getContext(), SingerDetailActivity.class);
            // intent.putExtra("SINGER_NAME", singer);
            // holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return singerList.size();
    }

    public static class SingerViewHolder extends RecyclerView.ViewHolder {
        public TextView singerName;

        public SingerViewHolder(View itemView) {
            super(itemView);
            singerName = itemView.findViewById(R.id.singer_name);
        }
    }
}
