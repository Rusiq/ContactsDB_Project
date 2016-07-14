package com.example.ruslan.contactsdb_project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FragmentDone extends Fragment {

    TextView tvFirstName;
    TextView tvLastName;
    TextView tvPhone;
    TextView tvAddress;
    TextView tvJob;
    TextView tvStatus;
    TextView tvGender;
    TextView tvEmail;
    LinearLayout llLastName, llAddress, llStatus, llGender, llEmail;
    Contact contact;


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

        contact = ((AddActivity) getActivity()).getContact();

        return rootView;
    }


    public void setContact(Contact contact) {

        contact = this.contact;
        tvFirstName.setText(contact.getFirstName());
        tvJob.setText(contact.getJob());
        tvPhone.setText(contact.getPhoneNumber());

        if (contact.getLastName() == null) llLastName.setVisibility(View.GONE);
        else tvLastName.setText(contact.getLastName());

        if (contact.getAddress() == null) llAddress.setVisibility(View.GONE);
        else tvAddress.setText(contact.getAddress());

        if (contact.getMaritalStatus().toString() == null) llStatus.setVisibility(View.GONE);
        else tvStatus.setText(contact.getMaritalStatus().toString());

        if (contact.getGender().toString() == null) llGender.setVisibility(View.GONE);
        else tvGender.setText(contact.getGender().toString());

        if (contact.getEmail() == null) llEmail.setVisibility(View.GONE);
        else tvEmail.setText(contact.getEmail());

        Log.d("Info", contact.getFirstName());
    }
}