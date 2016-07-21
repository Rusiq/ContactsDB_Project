package com.example.ruslan.contactsdb_project.ui.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.ruslan.contactsdb_project.R;
import com.example.ruslan.contactsdb_project.data.Contact;

public class DetailActivity extends AppCompatActivity {

    TextView firstNameTextView, lastNameTextView, phoneTextView, addressTextView, jobTextView,
            maritalStatusTextView, genderTextView, emailTextView;
    Contact contact;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detail); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);


        firstNameTextView = (TextView) findViewById(R.id.firstNameTextView);
        lastNameTextView = (TextView) findViewById(R.id.lastNameTextView);
        phoneTextView = (TextView) findViewById(R.id.phoneTextView);
        addressTextView = (TextView) findViewById(R.id.addressTextView);
        jobTextView = (TextView) findViewById(R.id.jobTextView);
        maritalStatusTextView = (TextView) findViewById(R.id.maritalStatusTextView);
        genderTextView = (TextView) findViewById(R.id.genderTextView);
        emailTextView = (TextView) findViewById(R.id.emailTextView);

        Intent intent = getIntent();

        contact = intent.getParcelableExtra("contact");
        setContactDetail();
    }

    private final DialogFragment confirmDelete = new DialogFragment() {
        // Создание объекта AlertDialog и его возвращение
        @Override
        public Dialog onCreateDialog(Bundle bundle) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Are You Sure?");
            builder.setMessage("This will permanently delete the contact");

            builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int button) {
                            //TODO Реализация удаления контакта
                        }
                    }
            );
            builder.setNegativeButton("CANCEL", null);
            return builder.create(); // Вернуть AlertDialog
        }
    };


    private void deleteContact() {
        // FragmentManager используется для отображения confirmDelete
        confirmDelete.show(getSupportFragmentManager(), "confirm delete");
     //   DetailActivity.finish();
    }

    private void setContactDetail() {
        firstNameTextView.setText(contact.getFirstName());
        lastNameTextView.setText(contact.getLastName());
        phoneTextView.setText(contact.getPhoneNumber());
        addressTextView.setText(contact.getAddress());
        jobTextView.setText(contact.getJob());
        maritalStatusTextView.setText(contact.getMaritalStatus().toString());
        genderTextView.setText(contact.getGender().toString());
        emailTextView.setText(contact.getEmail());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemDelete:
                deleteContact();

                break;

            case R.id.itemEdit:

                break;

            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}
