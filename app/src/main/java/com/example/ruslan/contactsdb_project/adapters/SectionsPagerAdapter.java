package com.example.ruslan.contactsdb_project.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.ruslan.contactsdb_project.ui.fragments.FragmentDone;
import com.example.ruslan.contactsdb_project.ui.fragments.FragmentFirstStep;
import com.example.ruslan.contactsdb_project.ui.fragments.FragmentSecondStep;

public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

    private  FragmentFirstStep sFragmentFirstStep;
    private  FragmentSecondStep sFragmentSecondStep;
    private  FragmentDone sFragmentDone;


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
                fragment = sFragmentFirstStep;
                break;
            case 1:
                if (sFragmentSecondStep == null)
                    sFragmentSecondStep = FragmentSecondStep.getInstance();
                fragment =sFragmentSecondStep;
                break;
            case 2:
                if (sFragmentDone == null)
                    sFragmentDone = FragmentDone.getInstance();
                fragment = sFragmentDone;

                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }
}