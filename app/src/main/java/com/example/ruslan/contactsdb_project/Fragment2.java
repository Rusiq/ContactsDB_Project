package com.example.ruslan.contactsdb_project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.LinearLayout;


public class Fragment2 extends Fragment {
    EditText etJob, etEmail;
    CheckedTextView chkMarried, chkSingle, chkFemale, chkMale;
    LinearLayout llNext2;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment2, container, false);

        etJob = (EditText) rootView.findViewById(R.id.etJob);
        etEmail = (EditText) rootView.findViewById(R.id.etEmail);
        chkMarried = (CheckedTextView) rootView.findViewById(R.id.chkMarried);
        chkSingle = (CheckedTextView) rootView.findViewById(R.id.chkSingle);
        chkFemale = (CheckedTextView) rootView.findViewById(R.id.chkFemale);
        chkMale = (CheckedTextView) rootView.findViewById(R.id.chkMale);
        llNext2 = (LinearLayout) rootView.findViewById(R.id.llNext2);


        return rootView;
    }
}
