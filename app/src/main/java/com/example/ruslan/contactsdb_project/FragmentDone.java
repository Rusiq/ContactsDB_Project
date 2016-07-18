package com.example.ruslan.contactsdb_project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruslan.contactsdb_project.data.DBHandler;

public class FragmentDone extends Fragment {

    TextView tvFirstName, tvLastName, tvPhone, tvAddress, tvJob, tvStatus, tvGender, tvEmail;
    LinearLayout llLastName, llAddress, llStatus, llGender, llEmail;
    ImageView btnSave;
    private Contact mContact;



    public static FragmentDone getInstance() {
        FragmentDone fragmentDone = new FragmentDone();

        return fragmentDone;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_done, container, false);

        final DBHandler db = new DBHandler(getActivity());
        mContact = ((AddActivity) getActivity()).getContact();

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

        btnSave = (ImageView) rootView.findViewById(R.id.btnSave);


        try {
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    db.addContact(new Contact(mContact.getFirstName(), mContact.getLastName(),
                            mContact.getPhoneNumber(), mContact.getAddress(), mContact.getJob(),
                            mContact.getMaritalStatus(), mContact.getGender(), mContact.getEmail()));
                    Toast toast = Toast.makeText(getActivity(), "New contact is added", Toast.LENGTH_SHORT);
                    toast.show();

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rootView;
    }


    public void setContact(Contact contact) {

        tvFirstName.setText(contact.getFirstName());
        tvJob.setText(contact.getJob());
        tvPhone.setText(contact.getPhoneNumber());
        tvStatus.setText(contact.getMaritalStatus().toString());
        tvGender.setText(contact.getGender().toString());

        if (contact.getLastName().equals("")) llLastName.setVisibility(View.GONE);
        else {
            tvLastName.setText(contact.getLastName());
            tvLastName.setVisibility(View.VISIBLE);
        }

        if (contact.getAddress().equals("")) llAddress.setVisibility(View.GONE);
        else {
            tvAddress.setText(contact.getAddress());
            tvAddress.setVisibility(View.VISIBLE);
        }

        if (contact.getEmail().equals("")) llEmail.setVisibility(View.GONE);
        else {
            tvEmail.setText(contact.getEmail());
            llEmail.setVisibility(View.VISIBLE);
        }

    }

}