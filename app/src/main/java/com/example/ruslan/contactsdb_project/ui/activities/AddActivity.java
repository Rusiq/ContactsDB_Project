package com.example.ruslan.contactsdb_project.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ruslan.contactsdb_project.R;
import com.example.ruslan.contactsdb_project.adapters.SectionsPagerAdapter;
import com.example.ruslan.contactsdb_project.data.Contact;
import com.example.ruslan.contactsdb_project.data.DBHandler;
import com.example.ruslan.contactsdb_project.dots.MaterialIndicator;
import com.example.ruslan.contactsdb_project.ui.CustomViewPager;
import com.example.ruslan.contactsdb_project.ui.fragments.FragmentDone;
import com.example.ruslan.contactsdb_project.ui.fragments.FragmentFirstStep;
import com.example.ruslan.contactsdb_project.ui.fragments.FragmentSecondStep;


public class AddActivity extends AppCompatActivity implements MaterialIndicator.ButtonListener{

    private CustomViewPager customViewPager;
    TextView tvTitle;
    ImageView btnNext, btnSave;
    private MaterialIndicator indicator;

    boolean status1 = true, status2 = true;
     private Contact mContact;
    Fragment fragCurrent;
    SectionsPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitle.setText(R.string.step_1);
        mContact = new Contact();

        customViewPager = (CustomViewPager) findViewById(R.id.custom_view_pager);
        indicator = (MaterialIndicator) findViewById(R.id.materialIndicator);

        pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        customViewPager.setAdapter(pagerAdapter);

        indicator.setViewPager(customViewPager);

        indicator.setButtonListener(this);
        btnNext = (ImageView) findViewById(R.id.btnNext);
        btnSave = (ImageView) findViewById(R.id.btnSave);

        fragCurrent = (Fragment) customViewPager.getAdapter().instantiateItem(customViewPager, customViewPager.getCurrentItem());

        customViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tvTitle.setText(R.string.step_1);
                        customViewPager.setDirection(CustomViewPager.SwipeDirection.all);
                        btnNext.setVisibility(View.VISIBLE);
                        Log.d("page", "------------- PAGE " + (position + 1) + "-------------");
                        logContactInfo();

                        break;

                    case 1:
                        tvTitle.setText(R.string.step_2);
                        btnSave.setVisibility(View.INVISIBLE);
                        if (!status1 && !status2) {
                            btnNext.setVisibility(View.VISIBLE);
                            customViewPager.setDirection(CustomViewPager.SwipeDirection.all);
                        } else {
                            btnNext.setVisibility(View.INVISIBLE);
                            customViewPager.setDirection(CustomViewPager.SwipeDirection.left);
                        }
                       /* if (fragCurrent != null && fragCurrent instanceof FragmentFirstStep) {
                            Log.d("onPageSelected", "First name " + ((FragmentFirstStep) fragCurrent).getFirstName());
                        }*/
                        Log.d("page", "------------- PAGE " + (position + 1) + "-------------");
                        logContactInfo();

                        break;

                    case 2:
                        tvTitle.setText(R.string.done);
                        btnNext.setVisibility(View.INVISIBLE);
                        btnSave.setVisibility(View.VISIBLE);

                        //       if (page != 3) {

                        Log.d("getData", "getDateFromFirstStep + getDateFromSecond()");
                        getDateFromFirstStep();
                        getDateFromSecond();
                        fragCurrent = (Fragment) customViewPager.getAdapter().instantiateItem(customViewPager, customViewPager.getCurrentItem());
                        if (fragCurrent instanceof FragmentDone) {
                            ((FragmentDone) fragCurrent).setContact(mContact);
                        }
                        //        }
                        Log.d("page", "------------- PAGE " + (position + 1) + "-------------");
                        logContactInfo();


                        break;
                }
                fragCurrent = (Fragment) customViewPager.getAdapter().instantiateItem(customViewPager, customViewPager.getCurrentItem());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public Contact getContact() {
        return mContact;
    }


    private void getDateFromFirstStep() {
        FragmentFirstStep fragCurrent = (FragmentFirstStep) customViewPager.getAdapter().instantiateItem(customViewPager, 0);
        if (fragCurrent != null) {
            mContact.setFirstName(fragCurrent.getFirstName());
            mContact.setLastName(fragCurrent.getLastName());
            mContact.setPhoneNumber(fragCurrent.getPhone());
            mContact.setAddress(fragCurrent.getAddress());
        }
    }


    private void getDateFromSecond() {
        FragmentSecondStep fragCurrent = (FragmentSecondStep) customViewPager.getAdapter().instantiateItem(customViewPager, 1);
        if (fragCurrent != null) {
            mContact.setJob(fragCurrent.getJob());
            mContact.setGender(fragCurrent.getGender());
            mContact.setMaritalStatus(fragCurrent.getMaritalStatus());
            mContact.setEmail(fragCurrent.getEmail());
        }
    }

    public void setStatus1(boolean value) {
        status1 = value;

    }

    public void setStatus2(boolean value) {
        status2 = value;
        if (!status1 && !status2) {
            btnNext.setVisibility(View.VISIBLE);
            customViewPager.setDirection(CustomViewPager.SwipeDirection.all);
        } else {
            btnNext.setVisibility(View.INVISIBLE);
            customViewPager.setDirection(CustomViewPager.SwipeDirection.left);
        }

    }


    public void logContactInfo() {

        Log.d("ContactInfo", " - FirstName: " + mContact.getFirstName());
        Log.d("ContactInfo", " - LastName: " + mContact.getLastName());
        Log.d("ContactInfo", " - Phone: " + mContact.getPhoneNumber());
        Log.d("ContactInfo", " - Address: " + mContact.getAddress());

        Log.d("ContactInfo", " - Job: " + mContact.getJob());
        Log.d("ContactInfo", " - Marital status: " + mContact.getMaritalStatus());
        Log.d("ContactInfo", " - Gender: " + mContact.getGender());
        Log.d("ContactInfo", " - Email: " + mContact.getEmail());

    }


    @Override
    public void onButtonDoneClick() {
        DBHandler dbHandler = new DBHandler(this);
        long id = dbHandler.addContact(mContact);
        Intent intent = new Intent();
        intent.putExtra("id", id);
        setResult(AddActivity.RESULT_OK, intent);
        finish();
    }
}
