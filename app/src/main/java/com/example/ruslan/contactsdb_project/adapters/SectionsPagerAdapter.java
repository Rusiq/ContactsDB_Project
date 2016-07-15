package com.example.ruslan.contactsdb_project.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.ruslan.contactsdb_project.FragmentDone;
import com.example.ruslan.contactsdb_project.FragmentFirstStep;
import com.example.ruslan.contactsdb_project.FragmentSecondStep;

/**
 * Created by ruslan on 15.07.16.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private static FragmentFirstStep sFragmentFirstStep;
    private static FragmentSecondStep sFragmentSecondStep;
    private static FragmentDone sFragmentDone;


    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        Fragment fragment = null;
        switch (position) {
            case 0:
                if (sFragmentFirstStep == null)
                    sFragmentFirstStep = FragmentFirstStep.getInstance();
                fragment = FragmentFirstStep.getInstance();
                break;
            case 1:
                if (sFragmentSecondStep == null)
                    sFragmentSecondStep = FragmentSecondStep.getInstance();
                fragment = FragmentSecondStep.getInstance();
                break;
            case 2:
                if (sFragmentDone == null)
                    sFragmentDone = FragmentDone.getInstance();
                fragment = FragmentDone.getInstance();

                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 3;
    }
}