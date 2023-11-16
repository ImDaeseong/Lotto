package com.im.daeseong.lottoplayer;

import androidx.annotation.Nullable;//import android.support.annotation.Nullable;
import androidx.fragment.app.Fragment;//import android.support.v4.app.Fragment;
import androidx.fragment.app.FragmentManager;//import android.support.v4.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;

public class LottoAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragments = new ArrayList<>();
    private final List<String> titles = new ArrayList<>();

    public LottoAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public void addFragment(Fragment fragment, String title) {
        fragments.add(fragment);
        titles.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
