package com.example.ruslan.contactsdb_project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by ruslan on 04.07.16.
 */
public class FragmentDone extends Fragment {

    TextView tvFirstName, tvLastName, tvPhone, tvAddress, tvJob, tvStatus, tvGender, tvEmail;
    LinearLayout llLastName, llAddress, llStatus, llGender, llEmail;
    FragmentFirstStep frag1;
    FragmentSecondStep frag2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_done, container, false);

        tvFirstName = (TextView) rootView.findViewById(R.id.tvFirstName);
        tvLastName = (TextView) rootView.findViewById(R.id.tvLastName);
        tvPhone = (TextView) rootView.findViewById(R.id.tvPhone);
        tvAddress = (TextView) rootView.findViewById(R.id.tvAddress);
        tvJob = (TextView) rootView.findViewById(R.id.tvJob);
        tvStatus = (TextView) rootView.findViewById(R.id.tvStatus);
        tvGender = (TextView) rootView.findViewById(R.id.tvGender);
        tvEmail = (TextView) rootView.findViewById(R.id.tvEmail);

        llLastName = (LinearLayout) rootView.findViewById(R.id.llLastName);
        llAddress = (LinearLayout) rootView.findViewById(R.id.llAddress);
        llStatus = (LinearLayout) rootView.findViewById(R.id.llStatus);
        llGender = (LinearLayout) rootView.findViewById(R.id.llGender);
        llEmail = (LinearLayout) rootView.findViewById(R.id.llEmail);

       /* frag1 = (FragmentFirstStep) getFragmentManager().findFragmentById(R.id.frag1);
        frag2 = (FragmentSecondStep) getFragmentManager().findFragmentById(R.id.frag2);*/
        String tagFragmentDone = getTag();
        ((AddActivity)getActivity()).setTabFragmentDone(tagFragmentDone);

        return rootView;
    }


    public void updateTextFromFirstStep (String contactFirstName, String contactLastName, String contactPhone, String contactAddress){

        tvFirstName.setText(contactFirstName);

        if (contactLastName == null){
            llLastName.setVisibility(View.GONE);
        } else tvLastName.setText(contactLastName);

        tvPhone.setText(contactPhone);

        if (contactAddress == null){
            llAddress.setVisibility(View.GONE);
        } else tvAddress.setText(contactAddress);
    }



    public void updateTextFromSecondStep (String contactJob, String contactMaritalStatus, String contactGender, String contactEmail){

        tvJob.setText(contactJob);

        if (contactMaritalStatus == null){
            llStatus.setVisibility(View.GONE);
        } else tvStatus.setText(contactMaritalStatus);

        if (contactGender == null){
            llGender.setVisibility(View.GONE);
        } else tvGender.setText(contactGender);

        if (contactEmail == null){
            llEmail.setVisibility(View.GONE);
        } else tvEmail.setText(contactEmail);
    }
}