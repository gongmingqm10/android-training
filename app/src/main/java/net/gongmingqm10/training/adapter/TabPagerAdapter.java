package net.gongmingqm10.training.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import net.gongmingqm10.training.R;
import net.gongmingqm10.training.TrainingApp;
import net.gongmingqm10.training.fragment.TabAFragment;
import net.gongmingqm10.training.fragment.TabBFragment;
import net.gongmingqm10.training.fragment.TabCFragment;

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    private final int[] PAGE_TITLES = {R.string.fragment_a_title, R.string.fragment_b_title, R.string.fragment_c_title};

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new TabAFragment();
                break;
            case 1:
                fragment = new TabBFragment();
                break;
            case 2:
                fragment = new TabCFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TrainingApp.getInstance().getString(PAGE_TITLES[position]);
    }
}
