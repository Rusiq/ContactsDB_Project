package com.example.ruslan.contactsdb_project;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ruslan.contactsdb_project.dots.MaterialIndicator;


public class AddActivity extends AppCompatActivity {
    private CustomViewPager customViewPager;

    TextView tvTitle;
    ImageView btnNext, btnSave;
    private MaterialIndicator indicator;
    FragmentSecondStep frag2;
    FragmentFirstStep frag1;
    FragmentDone frag3;
    boolean status1 = true, status2 = true;
    private Contact mContact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitle.setText(R.string.step_1);
        mContact = new Contact();

        frag2 = (FragmentSecondStep) getSupportFragmentManager().findFragmentById(R.id.frag2);
        frag1 = (FragmentFirstStep) getSupportFragmentManager().findFragmentById(R.id.frag1);
        frag3 = (FragmentDone) getSupportFragmentManager().findFragmentById(R.id.frag3);

//        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frag3);
//        if (fragment != null &&  fragment instanceof FragmentDone){
//            frag3 = (FragmentDone)fragment;
//        }

        customViewPager = (CustomViewPager) findViewById(R.id.custom_view_pager);
        indicator = (MaterialIndicator) findViewById(R.id.materialIndicator);
        final PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        customViewPager.setAdapter(pagerAdapter);
        //customViewPager.setOffscreenPageLimit(0);
        indicator.setViewPager(customViewPager);

        btnNext = (ImageView) findViewById(R.id.btnNext);
        btnSave = (ImageView) findViewById(R.id.btnSave);

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

                       // frag2.etEmail.setText("test@mail");
                        Log.d("contact","First name " +  mContact.getFirstName());
                        break;

                    case 1:
                        Log.d("STATUS1", String.valueOf(status1));
                        tvTitle.setText(R.string.step_2);
                        btnSave.setVisibility(View.INVISIBLE);
                        if (!status1 && !status2) {
                            btnNext.setVisibility(View.VISIBLE);
                            customViewPager.setDirection(CustomViewPager.SwipeDirection.all);
                        } else {
                            btnNext.setVisibility(View.INVISIBLE);
                            customViewPager.setDirection(CustomViewPager.SwipeDirection.left);
                        }
                        Log.d("contact", "First name " + String.valueOf(mContact.getFirstName()));
                        break;

                    case 2:
                        tvTitle.setText(R.string.done);
                        btnNext.setVisibility(View.INVISIBLE);
                        btnSave.setVisibility(View.VISIBLE);
                        setFields();


                        Log.d("contact", "First name " + String.valueOf(mContact.getFirstName()));
                        Log.d("contact", mContact.getFirstName());
                      //  Log.d("Frag3 tvFirstName", String.valueOf(frag3.tvFirstName.toString()));
                        Log.d("Frag3 tvFirstName", frag3.tvFirstName.getText().toString());
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

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

    public void setFields() {
        mContact.setFirstName(frag1.getFirstName());
        mContact.setAddress(frag1.getAddress());
        mContact.setLastName(frag1.getLastName());
        mContact.setPhoneNumber(frag1.getPhone());
        mContact.setGender(frag2.getGender());
        mContact.setMaritalStatus(frag2.getMaritalStatus());
        mContact.setJob(frag2.getJob());
        mContact.setEmail(frag2.getEmail());

        Log.d("contact", mContact.getFirstName());
        frag3.setContact(mContact);

    }

    public Contact getContact() {
        return mContact;
    }
}
