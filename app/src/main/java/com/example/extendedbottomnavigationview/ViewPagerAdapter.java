package com.example.extendedbottomnavigationview;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.extendedbottomnavigationview.ui.AccountFragment;
import com.example.extendedbottomnavigationview.ui.HomeFragment;
import com.example.extendedbottomnavigationview.ui.NotificationFragment;
import com.example.extendedbottomnavigationview.ui.QrFragment;
import com.example.extendedbottomnavigationview.ui.TransactionFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return HomeFragment.newInstance();
            case 1:
                return TransactionFragment.newInstance();
            case 2:
                return QrFragment.newInstance();
            case 3:
                return NotificationFragment.newInstance();
            case 4:
                return AccountFragment.newInstance();
            default:
                throw new IllegalArgumentException("illegal position");

        }

    }

    @Override
    public int getCount() {
        return 5;
    }
}
