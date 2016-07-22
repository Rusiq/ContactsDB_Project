package com.example.ruslan.contactsdb_project.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.ruslan.contactsdb_project.R;
import com.example.ruslan.contactsdb_project.data.Contact;
import com.example.ruslan.contactsdb_project.data.DBHandler;

public class EditActivity extends AppCompatActivity {

    EditText editTextFirstName, editTextLastName, editTextPhone, editTextAddress,
            editTextJob, editTextEmail;
    TextInputLayout firstNameTextInputLayout, lastNameTextInputLayout, phoneTextInputLayout,
            addressTextInputLayout, jobTextInputLayout, emailTextInputLayout;
    RadioButton editChkMarried, editChkSingle, editChkFemale, editChkMale;
    RadioGroup editRadioMaritalStatus, editRadioGender;
    Contact contact;
    //Boolean emptyFristName = true, emptyPhone = true, emptyJob = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_edit); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);

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

        Intent intent = getIntent();

        contact = intent.getParcelableExtra("contact");
        setContactDetail();


       /* editTextFirstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (editTextFirstName.getText().toString().trim().length() < 2) {
                    emptyFristName = true;
                } else emptyFristName = false;
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        editTextPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (editTextFirstName.getText().length() < 11) {
                    emptyPhone = true;
                } else emptyPhone = false;
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        editTextJob.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (editTextJob.getText().toString().trim().equals("")) {
                    emptyJob = true;
                } else emptyJob = false;
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });*/

    }

    private void setContactDetail() {
        editTextFirstName.setText(contact.getFirstName());
        editTextLastName.setText(contact.getLastName());
        editTextPhone.setText(contact.getPhoneNumber());
        editTextAddress.setText(contact.getAddress());
        editTextJob.setText(contact.getJob());
        editTextEmail.setText(contact.getEmail());

        editRadioMaritalStatus.check((contact.getMaritalStatus() == Contact.MaritalStatus.married) ? R.id.editChkMarried : R.id.editChkSingle);
        editRadioGender.check((contact.getGender() == Contact.Gender.female) ? R.id.editChkFemale : R.id.editChkMale);

    }

    private void getNewDetailsContact() {
        contact.setFirstName(getEditedFirstName());
        contact.setLastName(getEditedLastName());
        contact.setPhoneNumber(getEditedPhone());
        contact.setAddress(getEditedAddress());
        contact.setJob(getEditedJob());
        contact.setMaritalStatus(getEditedMaritalStatus());
        contact.setGender(getEditedGender());
        contact.setEmail(getEditedEmail());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
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
             //   if (!emptyFristName && !emptyPhone && !emptyJob) {
                    getNewDetailsContact();
                    DBHandler db = new DBHandler(this);
                    db.updateContact(contact);
                    Intent intent = new Intent();
                    intent.putExtra("contact", (Parcelable) contact);
                    setResult(DetailActivity.RESULT_OK, intent);
                    finish();
             //   } else Toast.makeText(this, "Fill required fields", Toast.LENGTH_SHORT).show();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    public Contact.Gender getEditedGender() {
        return (editRadioGender.getCheckedRadioButtonId() == R.id.editChkFemale) ? Contact.Gender.female : Contact.Gender.male;
    }

    public Contact.MaritalStatus getEditedMaritalStatus() {
        return (editRadioMaritalStatus.getCheckedRadioButtonId() == R.id.editChkMarried) ? Contact.MaritalStatus.married : Contact.MaritalStatus.single;
    }

    public String getEditedJob() {
        return editTextJob.getText().toString().trim();
    }

    public String getEditedEmail() {
        return editTextEmail.getText().toString().trim();
    }


    public String getEditedFirstName() {
        return editTextFirstName.getText().toString().trim();
    }

    public String getEditedLastName() {
        return editTextLastName.getText().toString().trim();
    }

    public String getEditedAddress() {
        return editTextAddress.getText().toString().trim();
    }

    public String getEditedPhone() {
        return editTextPhone.getText().toString().trim();
    }
}
