package com.example.ruslan.contactsdb_project;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by ruslan on 04.07.16.
 */
public class Fragment3 extends Fragment {

    TextView tvFirstName, tvLastName, tvPhone, tvAddress, tvJob, tvStatus, tvGender, tvEmail;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment3, container, false);

        tvFirstName = (TextView) rootView.findViewById(R.id.tvFirstName);
        tvLastName = (TextView) rootView.findViewById(R.id.tvLastName);
        tvPhone = (TextView) rootView.findViewById(R.id.tvPhone);
        tvAddress = (TextView) rootView.findViewById(R.id.tvAddress);
        tvJob = (TextView) rootView.findViewById(R.id.tvJob);
        tvStatus = (TextView) rootView.findViewById(R.id.tvStatus);
        tvGender = (TextView) rootView.findViewById(R.id.tvGender);
        tvEmail = (TextView) rootView.findViewById(R.id.tvEmail);



        return rootView;
    }
}
