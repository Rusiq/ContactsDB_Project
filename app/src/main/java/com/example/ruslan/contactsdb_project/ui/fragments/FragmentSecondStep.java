package com.example.ruslan.contactsdb_project.ui.fragments;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.ruslan.contactsdb_project.R;
import com.example.ruslan.contactsdb_project.data.Contact;
import com.example.ruslan.contactsdb_project.ui.activities.AddActivity;


public class FragmentSecondStep extends Fragment {

    EditText etJob, etEmail;
    ImageView btnNext;
    RadioButton chkMarried, chkSingle, chkFemale, chkMale;
    boolean isEmptyFieldsFragment2, oldStatus2;
    RadioGroup radioGender, radioMaritalStatus;
    TextInputLayout tilJob;


    public static  FragmentSecondStep getInstance(){
        FragmentSecondStep fragmentSecondStep = new FragmentSecondStep();
        return fragmentSecondStep;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_second_step, container, false);

        etJob = (EditText) rootView.findViewById(R.id.etJob);
        etEmail = (EditText) rootView.findViewById(R.id.etEmail);
        chkMarried = (RadioButton) rootView.findViewById(R.id.chkMarried);
        chkSingle = (RadioButton) rootView.findViewById(R.id.chkSingle);
        chkFemale = (RadioButton) rootView.findViewById(R.id.chkFemale);
        chkMale = (RadioButton) rootView.findViewById(R.id.chkMale);
        radioGender = (RadioGroup) rootView.findViewById(R.id.radioGender);
        radioGender.check(R.id.chkMale);
        radioMaritalStatus = (RadioGroup) rootView.findViewById(R.id.radioMaritalStatus);
        radioMaritalStatus.check(R.id.chkSingle);
        tilJob = (TextInputLayout) rootView.findViewById(R.id.tilJob);
        btnNext = (ImageView) rootView.findViewById(R.id.btnNext);

        isEmptyFieldsFragment2 = true;
        oldStatus2 = true;

        tilJob.setErrorEnabled(true);
        tilJob.setError("Required field");


        etJob.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (etJob.getText().toString().trim().equals("")) {
                    isEmptyFieldsFragment2 = true;
                    tilJob.setErrorEnabled(true);
                    tilJob.setError("Required field");
                } else {
                    isEmptyFieldsFragment2 = false;
                    tilJob.setErrorEnabled(false);
                }

                if (oldStatus2 != isEmptyFieldsFragment2) {
                    oldStatus2 = isEmptyFieldsFragment2;
                    ((AddActivity) getActivity()).setStatus2(isEmptyFieldsFragment2);

           //         Log.d("Empty status", "sending to Activity new status fields2");
                }

           //     Log.d("Empty fields fragment 2", String.valueOf(isEmptyFieldsFragment2));

            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        return rootView;
    }


    public Contact.Gender getGender(){
        return (radioGender.getCheckedRadioButtonId() == R.id.chkFemale) ? Contact.Gender.female : Contact.Gender.male;
    }

    public Contact.MaritalStatus getMaritalStatus(){
        return (radioMaritalStatus.getCheckedRadioButtonId() == R.id.chkMarried) ? Contact.MaritalStatus.married : Contact.MaritalStatus.single;
    }

    public String getJob() {
        return etJob.getText().toString().trim();
    }

    public String getEmail() {
        return etEmail.getText().toString().trim();
    }

}
