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

public class Fragment1 extends Fragment {

    EditText etFirstName, etLastName, etPhone, etAddress;
    boolean isEmptyFieldsFragment1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment1, container, false);

        etFirstName = (EditText) rootView.findViewById(R.id.etFirstName);
        etLastName = (EditText) rootView.findViewById(R.id.etLastName);
        etPhone = (EditText) rootView.findViewById(R.id.etPhone);
        etAddress = (EditText) rootView.findViewById(R.id.etAddress);
        isEmptyFieldsFragment1 = true;

        etFirstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (etFirstName.getText().length() > 1 && etPhone.getText().length() == 11) {
                    isEmptyFieldsFragment1 = false;
                } else isEmptyFieldsFragment1 = true;
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
                if (etFirstName.getText().length() > 1 && etPhone.getText().length() == 11) {
                    isEmptyFieldsFragment1 = false;
                } else isEmptyFieldsFragment1 = true;
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
