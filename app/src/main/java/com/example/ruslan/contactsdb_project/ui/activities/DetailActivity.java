package com.example.ruslan.contactsdb_project.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.ruslan.contactsdb_project.R;

public class DetailActivity extends AppCompatActivity {

    TextView firstNameTextView, lastNameTextView, phoneTextView, addressTextView, jobTextView,
            maritalStatusTextView, genderTextView, emailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        firstNameTextView = (TextView) findViewById(R.id.firstNameTextView);
        lastNameTextView = (TextView) findViewById(R.id.lastNameTextView);
        phoneTextView = (TextView) findViewById(R.id.phoneTextView);
        addressTextView = (TextView) findViewById(R.id.addressTextView);
        jobTextView = (TextView) findViewById(R.id.jobTextView);
        maritalStatusTextView = (TextView) findViewById(R.id.maritalStatusTextView);
        genderTextView = (TextView) findViewById(R.id.genderTextView);
        emailTextView = (TextView) findViewById(R.id.emailTextView);

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
            case R.id.itemDelete:

                break;

            case R.id.itemEdit:

                break;

            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}
