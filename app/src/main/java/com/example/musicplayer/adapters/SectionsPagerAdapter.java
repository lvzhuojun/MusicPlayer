package com.example.musicplayer.adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.musicplayer.ui.main.SingleSongFragment;
import com.example.musicplayer.ui.main.AlbumFragment;
import com.example.musicplayer.ui.main.SingerFragment;
import com.example.musicplayer.ui.main.FolderFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new SingleSongFragment();
            case 1:
                return new AlbumFragment();
            case 2:
                return new SingerFragment();
            case 3:
                return new FolderFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "单曲";
            case 1:
                return "专辑";
            case 2:
                return "歌手";
            case 3:
                return "文件夹";
        }
        return null;
    }
}
