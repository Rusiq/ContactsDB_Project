package com.example.ruslan.contactsdb_project;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class FragmentSecondStep extends Fragment {

    public interface OnFragmentSecondStepInteractionListener {
        public void onFragmentSecondStepEditTextFilled(String job, Contact.MaritalStatus maritalStatus, Contact.Gender gender, String email);
    }

    EditText etJob, etEmail;
    ImageView btnNext;
    RadioButton chkMarried, chkSingle, chkFemale, chkMale;
    boolean isEmptyFieldsFragment2, oldStatus2;
    RadioGroup radioGender, radioStatus;
    TextInputLayout tilJob;

    private OnFragmentSecondStepInteractionListener listenerSecondStep;

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
        radioStatus = (RadioGroup) rootView.findViewById(R.id.radioStatus);
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

                    Log.d("Empty status", "sending to Activity new status fields2");
                }

                Log.d("Empty fields fragment 2", String.valueOf(isEmptyFieldsFragment2));

            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        try {
            listenerSecondStep = (OnFragmentSecondStepInteractionListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnFragmentSecondStepInteractionListener");
        }
        super.onAttach(context);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {

            listenerSecondStep.onFragmentSecondStepEditTextFilled(getJob(), getMaritalStatus(), getGender(), getEmail());
      //      Log.i("Fragment", "call activity: " + getJob() + getMaritalStatus() + getGender() + getEmail());
        }
    }


    public Contact.Gender getGender(){
        return (radioGender.getCheckedRadioButtonId() == R.id.chkFemale) ? Contact.Gender.female : Contact.Gender.male;
    }

    public Contact.MaritalStatus getMaritalStatus(){
        return (radioStatus.getCheckedRadioButtonId() == R.id.chkMarried) ? Contact.MaritalStatus.married : Contact.MaritalStatus.single;
    }

    public String getJob() {
        return etJob.getText().toString().trim();
    }

    public String getEmail() {
        return etEmail.getText().toString().trim();
    }

}
