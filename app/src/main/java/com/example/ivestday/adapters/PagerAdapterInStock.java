package com.example.ivestday.adapters;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PagerAdapterInStock extends FragmentPagerAdapter {

    private final List<Fragment> fragments;
    private final List<String> titleLists = new ArrayList<String>(Arrays.asList("Обзор", "Торговля"));

    public PagerAdapterInStock(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleLists.get(position);
    }
}

