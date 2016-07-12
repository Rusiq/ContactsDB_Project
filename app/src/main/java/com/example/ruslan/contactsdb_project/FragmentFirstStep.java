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

public class FragmentFirstStep extends Fragment {


    EditText etFirstName, etLastName, etPhone, etAddress;
    TextInputLayout tilFirstName, tilPhone;
    boolean isEmptyFieldsFragment1;
    boolean oldStatus1;
    private CustomViewPager customViewPager;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_first_step, container, false);

        etFirstName = (EditText) rootView.findViewById(R.id.etFirstName);
        etLastName = (EditText) rootView.findViewById(R.id.etLastName);
        etPhone = (EditText) rootView.findViewById(R.id.etPhone);
        etAddress = (EditText) rootView.findViewById(R.id.etAddress);
        tilFirstName = (TextInputLayout) rootView.findViewById(R.id.tilFristName);
        tilPhone = (TextInputLayout) rootView.findViewById(R.id.tilPhone);

        isEmptyFieldsFragment1 = true;
        oldStatus1 = true;


        tilFirstName.setErrorEnabled(true);
        tilFirstName.setError("Required field");

        tilPhone.setErrorEnabled(true);
        tilPhone.setError("Required field");



      /*  etFirstName.setOnFocusChangeListener(new View.OnFocusChangeListener(){

            @Override
            public void onFocusChange(View view, boolean b) {

                if (etFirstName.getText().toString().trim().length() < 2) {
                    tilFirstName.setErrorEnabled(true);
                    tilFirstName.setError("Required field");
                } else {
                    tilFirstName.setErrorEnabled(false);
                    tilFirstName.setError(null);
                }
            }
        });*/

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
                } else {
                    isEmptyFieldsFragment1 = true;
                }


                if (oldStatus1 != isEmptyFieldsFragment1) {
                    oldStatus1 = isEmptyFieldsFragment1;
                    ((AddActivity) getActivity()).setStatus1(isEmptyFieldsFragment1);
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
                    ((AddActivity) getActivity()).setStatus1(isEmptyFieldsFragment1);
                    Log.d("Empty status", "sending to Activity new status fields");
                }

                Log.d("Empty fields fragment 1", String.valueOf(isEmptyFieldsFragment1));
                Log.d("STATUS1", String.valueOf(((AddActivity) getActivity()).status1));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        return rootView;
    }

}
