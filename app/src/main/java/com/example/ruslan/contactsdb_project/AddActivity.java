package com.example.ruslan.contactsdb_project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ruslan.contactsdb_project.adapters.SectionsPagerAdapter;
import com.example.ruslan.contactsdb_project.dots.MaterialIndicator;


public class AddActivity extends AppCompatActivity implements FragmentFirstStep.OnFragmentFirstStepInteractionListener, FragmentSecondStep.OnFragmentSecondStepInteractionListener {

    private CustomViewPager customViewPager;
    TextView tvTitle;
    ImageView btnNext, btnSave;
    private MaterialIndicator indicator;
    //    FragmentSecondStep frag2;
//    FragmentFirstStep frag1;
//    FragmentDone frag3;
    boolean status1 = true, status2 = true;
    private Contact mContact;
    Fragment fragCurrent;
    // FragmentDone fragmentDone;
    SectionsPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitle.setText(R.string.step_1);
        mContact = new Contact();

//        frag2 = (FragmentSecondStep) getSupportFragmentManager().findFragmentById(R.id.frag2);
//        frag1 = (FragmentFirstStep) getSupportFragmentManager().findFragmentById(R.id.frag1);
//        frag3 = (FragmentDone) getSupportFragmentManager().findFragmentById(R.id.frag3);

//        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frag3);
//        if (fragment != null &&  fragment instanceof FragmentDone){
//            frag3 = (FragmentDone)fragment;
//        }

        customViewPager = (CustomViewPager) findViewById(R.id.custom_view_pager);
        indicator = (MaterialIndicator) findViewById(R.id.materialIndicator);

        pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        customViewPager.setAdapter(pagerAdapter);

        // FragmentDone fragmentDone = (FragmentDone) getSupportFragmentManager().findFragmentByTag(FragmentDone.getInstance().getTag());


        //customViewPager.setOffscreenPageLimit(0);
        indicator.setViewPager(customViewPager);

        btnNext = (ImageView) findViewById(R.id.btnNext);
        btnSave = (ImageView) findViewById(R.id.btnSave);

        /*fragmentDone.getFragmentManager().beginTransaction().
                add(fragmentDone, "tagDoneFrag").
                commit();*/

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

                        getDateFromFirstStep();
                        getDateFromSecond();

                        fragCurrent = (Fragment) customViewPager.getAdapter().instantiateItem(customViewPager, customViewPager.getCurrentItem());
                        if (fragCurrent instanceof FragmentDone) {
                            ((FragmentDone) fragCurrent).setContact(mContact);
                        }

                        logContactInfo();
                        Log.d("page", "------------- PAGE " + (position + 1) + "-------------");
                        break;
                }
                fragCurrent = (Fragment) customViewPager.getAdapter().instantiateItem(customViewPager, customViewPager.getCurrentItem());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void getDateFromFirstStep() {
        FragmentFirstStep fragCurrent = (FragmentFirstStep) customViewPager.getAdapter().instantiateItem(customViewPager, 0);
        mContact.setFirstName(fragCurrent.getFirstName());
        mContact.setLastName(fragCurrent.getLastName());
        mContact.setPhoneNumber(fragCurrent.getPhone());
        mContact.setAddress(fragCurrent.getAddress());
    }


    private void getDateFromSecond() {
        FragmentSecondStep fragCurrent = (FragmentSecondStep) customViewPager.getAdapter().instantiateItem(customViewPager, 1);
        mContact.setJob(fragCurrent.getJob());
        mContact.setGender(fragCurrent.getGender());
        mContact.setMaritalStatus(fragCurrent.getMaritalStatus());
        mContact.setEmail(fragCurrent.getEmail());
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


    @Override
    public void onFragmentFirstStepEditTextFilled(String firstName, String lastName, String phone, String address) {
//        mContact.setFirstName(firstName);
//        mContact.setLastName(lastName);
//        mContact.setPhoneNumber(phone);
//        mContact.setAddress(address);
    }

    @Override
    public void onFragmentSecondStepEditTextFilled(String job, Contact.MaritalStatus maritalStatus, Contact.Gender gender, String email) {
//        mContact.setJob(job);
//        mContact.setMaritalStatus(maritalStatus);
//        mContact.setGender(gender);
//        mContact.setEmail(email);
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
}
