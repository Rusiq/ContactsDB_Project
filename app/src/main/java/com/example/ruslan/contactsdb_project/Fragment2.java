package com.example.ruslan.contactsdb_project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;


public class Fragment2 extends Fragment {
    EditText etJob, etEmail;
    RadioButton chkMarried, chkSingle, chkFemale, chkMale;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment2, container, false);

        etJob = (EditText) rootView.findViewById(R.id.etJob);
        etEmail = (EditText) rootView.findViewById(R.id.etEmail);
        chkMarried = (RadioButton) rootView.findViewById(R.id.chkMarried);
        chkSingle = (RadioButton) rootView.findViewById(R.id.chkSingle);
        chkFemale = (RadioButton) rootView.findViewById(R.id.chkFemale);
        chkMale = (RadioButton) rootView.findViewById(R.id.chkMale);


        return rootView;
    }
}
