package com.example.confession.adapters;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.confession.views.fragments.JoinedGroupFragment;
import com.example.confession.views.fragments.YourGroupFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter{
    private static final int NUM_PAGE = 2;
    private final String[] tabTitles = new String[]{"Your pages", "Joined pages"};
    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Log.e("Fragment positionnnnnnnnnnnnnnn: ", "Frag-"+position);
        switch (position){
            case 0:
                return YourGroupFragment.newInstance(0, "your-group");
            case 1:
                return JoinedGroupFragment.newInstance(1, "joined-group");
            default:
                return YourGroupFragment.newInstance(1, "your-group-default");
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGE;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }


}
