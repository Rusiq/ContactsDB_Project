package com.example.ruslan.contactsdb_project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class Fragment2 extends Fragment {



    EditText etJob, etEmail;
    RadioButton chkMarried, chkSingle, chkFemale, chkMale;
    static boolean isEmptyFieldsFragment2;
    RadioGroup radioGender;
    boolean oldStatus2;




  /*  public interface OnStatusFieldsFragment2Listener {
        public void onStatusFieldsFragment2(isEmptyFieldsFragment2);
    }

    OnStatusFieldsFragment2Listener mListener;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnStatusFieldsFragment2Listener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnStatusFieldsFragment2Listener");
        }
    }*/



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment2, container, false);

        etJob = (EditText) rootView.findViewById(R.id.etJob);
        etEmail = (EditText) rootView.findViewById(R.id.etEmail);
        chkMarried = (RadioButton) rootView.findViewById(R.id.chkMarried);
        chkSingle = (RadioButton) rootView.findViewById(R.id.chkSingle);
        chkFemale = (RadioButton) rootView.findViewById(R.id.chkFemale);
        chkMale = (RadioButton) rootView.findViewById(R.id.chkMale);
        radioGender = (RadioGroup) rootView.findViewById(R.id.radioGender);
        isEmptyFieldsFragment2 = true;
        oldStatus2 = true;

        etJob.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (etJob.getText().toString().trim().equals("")) {
                    isEmptyFieldsFragment2 = true;
                } else isEmptyFieldsFragment2 = false;

                if (oldStatus2 != isEmptyFieldsFragment2) {
                    oldStatus2 = isEmptyFieldsFragment2;
                    ((AddActivity) getActivity()).setStatus(isEmptyFieldsFragment2);
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

    public boolean getStatusEmptyFieldsFragment2() {
        return this.isEmptyFieldsFragment2;
    }








}
