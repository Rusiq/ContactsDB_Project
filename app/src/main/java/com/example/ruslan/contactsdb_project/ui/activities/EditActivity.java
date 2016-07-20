package com.example.ruslan.contactsdb_project.ui.activities;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.ruslan.contactsdb_project.R;

public class EditActivity extends AppCompatActivity {

    EditText editTextFirstName, editTextLastName, editTextPhone, editTextAddress,
            editTextJob, editTextEmail;
    TextInputLayout firstNameTextInputLayout, lastNameTextInputLayout, phoneTextInputLayout,
            addressTextInputLayout, jobTextInputLayout, emailTextInputLayout;
    RadioButton editChkMarried, editChkSingle, editChkFemale, editChkMale;
    RadioGroup editRadioMaritalStatus, editRadioGender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editTextFirstName = (EditText) findViewById(R.id.editTextFirstName);
        editTextLastName = (EditText) findViewById(R.id.editTextLastName);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        editTextAddress = (EditText) findViewById(R.id.editTextAddress);
        editTextJob = (EditText) findViewById(R.id.editTextJob);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        firstNameTextInputLayout = (TextInputLayout) findViewById(R.id.firstNameTextInputLayout);
        lastNameTextInputLayout = (TextInputLayout) findViewById(R.id.lastNameTextInputLayout);
        phoneTextInputLayout = (TextInputLayout) findViewById(R.id.phoneTextInputLayout);
        addressTextInputLayout = (TextInputLayout) findViewById(R.id.addressTextInputLayout);
        jobTextInputLayout = (TextInputLayout) findViewById(R.id.jobTextInputLayout);
        emailTextInputLayout = (TextInputLayout) findViewById(R.id.emailTextInputLayout);
        editChkMarried = (RadioButton) findViewById(R.id.editChkMarried);
        editChkSingle = (RadioButton) findViewById(R.id.editChkSingle);
        editChkFemale = (RadioButton) findViewById(R.id.editChkFemale);
        editChkMale = (RadioButton) findViewById(R.id.editChkMale);
        editRadioMaritalStatus = (RadioGroup) findViewById(R.id.editRadioMaritalStatus);
        editRadioGender = (RadioGroup) findViewById(R.id.editRadioGender);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemConfirmChanges:

                break;

            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}
