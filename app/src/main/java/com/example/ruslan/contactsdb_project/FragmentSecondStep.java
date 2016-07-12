package com.example.ruslan.contactsdb_project;

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


    EditText etJob, etEmail;
    ImageView btnNext;
    RadioButton chkMarried, chkSingle, chkFemale, chkMale;
    boolean isEmptyFieldsFragment2;
    RadioGroup radioGender, radioStatus;
    boolean oldStatus2;
    TextInputLayout tilJob;
    private CustomViewPager customViewPager;
    FragmentFirstStep frag1;


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

        frag1 = (FragmentFirstStep) getFragmentManager().findFragmentById(R.id.frag1);
        isEmptyFieldsFragment2 = true;
        oldStatus2 = true;

        tilJob.setErrorEnabled(true);
        tilJob.setError("Required field");

        customViewPager = (CustomViewPager) getActivity().findViewById(R.id.custom_view_pager);


        etJob.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (etJob.getText().toString().trim().equals("")) {
                    isEmptyFieldsFragment2 = true;
                    customViewPager.setDirection(CustomViewPager.SwipeDirection.left);

                    tilJob.setErrorEnabled(true);
                    tilJob.setError("Required field");
                } else {
                    isEmptyFieldsFragment2 = false;
                    tilJob.setErrorEnabled(false);


                }



                if (oldStatus2 != isEmptyFieldsFragment2) {
                    oldStatus2 = isEmptyFieldsFragment2;
                    ((AddActivity) getActivity()).setStatus2(isEmptyFieldsFragment2);
                    if (!oldStatus2 && !frag1.isEmptyFieldsFragment1) {
                        customViewPager.setDirection(CustomViewPager.SwipeDirection.all);
                    } else {
                        customViewPager.setDirection(CustomViewPager.SwipeDirection.left);
                    }
                    Log.d("Empty status", "sending to Activity new status fields2");
                }

                if (!((AddActivity) getActivity()).status1 && !isEmptyFieldsFragment2) {
                    customViewPager.setDirection(CustomViewPager.SwipeDirection.all);

                }
                Log.d("Empty fields fragment 2", String.valueOf(isEmptyFieldsFragment2));
                Log.d("STATUS1", String.valueOf(((AddActivity) getActivity()).status1));

            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        return rootView;
    }

}
