package com.example.autotank;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerMsgAdapter extends FragmentStateAdapter {


    public ViewPagerMsgAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Log.d("ViewPagerAdapter", "Creating fragment at position: " + position);
        switch (position) {
            case 0:
                return new ReadingsFragment();
            case 1:
                //return new BFragment();
            default:
                return new ReadingsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;  //no of tabs
    }

}
