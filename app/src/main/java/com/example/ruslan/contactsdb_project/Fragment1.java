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

public class Fragment1 extends Fragment {


    EditText etFirstName, etLastName, etPhone, etAddress;
    TextInputLayout tilFirstName, tilPhone;
    static boolean isEmptyFieldsFragment1;
    boolean oldStatus1;



  /*  public interface OnStatusFieldsFragment1Listener {
        public void onStatusFieldsFragment1(isEmptyFieldsFragment1);
    }

    OnStatusFieldsFragment1Listener mListener;public


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnStatusFieldsFragment1Listener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnStatusFieldsFragment1Listener");
        }
    }*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment1, container, false);

        etFirstName = (EditText) rootView.findViewById(R.id.etFirstName);
        etLastName = (EditText) rootView.findViewById(R.id.etLastName);
        etPhone = (EditText) rootView.findViewById(R.id.etPhone);
        etAddress = (EditText) rootView.findViewById(R.id.etAddress);
        tilFirstName = (TextInputLayout) rootView.findViewById(R.id.tilFristName);
        tilPhone = (TextInputLayout) rootView.findViewById(R.id.tilPhone);

        isEmptyFieldsFragment1 = true;
        oldStatus1 = true;


        etFirstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (etFirstName.getText().toString().trim().length() < 2) {
                    tilFirstName.setErrorEnabled(true);
                    tilFirstName.setError("Required field");
                } else tilFirstName.setErrorEnabled(false);


                if (etFirstName.getText().toString().trim().length() > 1 && etPhone.getText().toString().trim().length() == 11) {
                    isEmptyFieldsFragment1 = false;
                } else isEmptyFieldsFragment1 = true;


                if (oldStatus1 != isEmptyFieldsFragment1) {
                    oldStatus1 = isEmptyFieldsFragment1;
                    ((AddActivity) getActivity()).setStatus(isEmptyFieldsFragment1);
                    Log.d("Empty status", "sending to Activity new status fields1");

                }
                Log.d("Empty fields fragment 1", String.valueOf(isEmptyFieldsFragment1));
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (etPhone.getText().length() < 11) {
                    tilPhone.setErrorEnabled(true);
                    tilPhone.setError("Required field");
                } else tilPhone.setErrorEnabled(false);

                if (etFirstName.getText().length() > 1 && etPhone.getText().length() == 11) {
                    isEmptyFieldsFragment1 = false;
                } else isEmptyFieldsFragment1 = true;

                if (oldStatus1 != isEmptyFieldsFragment1) {
                    oldStatus1 = isEmptyFieldsFragment1;
                    ((AddActivity) getActivity()).setStatus(isEmptyFieldsFragment1);
                    Log.d("Empty status", "sending to Activity new status fields");
                }

                Log.d("Empty fields fragment 1", String.valueOf(isEmptyFieldsFragment1));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        return rootView;
    }

    public boolean getStatusEmptyFieldsFragment1() {
        return this.isEmptyFieldsFragment1;
    }


}
