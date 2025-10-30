package com.example.extendedbottomnavigationview;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.extendedbottomnavigationview.ui.Fragment1;
import com.example.extendedbottomnavigationview.ui.Fragment2;
import com.example.extendedbottomnavigationview.ui.Fragment3;
import com.example.extendedbottomnavigationview.ui.Fragment4;
import com.example.extendedbottomnavigationview.ui.Fragment5;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return Fragment1.newInstance();
            case 1:
                return Fragment2.newInstance();
            case 2:
                return Fragment3.newInstance();
            case 3:
                return Fragment4.newInstance();
            case 4:
                return Fragment5.newInstance();
            default:
                throw new IllegalArgumentException("illegal position");

        }

    }

    @Override
    public int getCount() {
        return 5;
    }
}
