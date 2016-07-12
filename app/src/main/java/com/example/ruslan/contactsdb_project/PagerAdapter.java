package com.example.ruslan.contactsdb_project;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {



    public PagerAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentFirstStep();
            case 1:
                return new FragmentSecondStep();
            case 2:
                return new FragmentDone();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }


}
