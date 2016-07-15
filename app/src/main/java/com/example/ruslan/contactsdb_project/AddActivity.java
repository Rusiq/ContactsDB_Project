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

        customViewPager.setCurrentItem(2);
        customViewPager.setCurrentItem(0);
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
//                        Log.d("onPageSelected", "page 1" + " - Job: " + mContact.getJob());
//                        Log.d("onPageSelected", "page 1" + " - FirstName: " + mContact.getFirstName());
//                        Log.d("onPageSelected", "page 1" + " - LasttName: " + mContact.getLastName());
//                        Log.d("onPageSelected", "page 1" + " - Phone: " + mContact.getPhoneNumber());
//                        Log.d("onPageSelected", "page 1" + " - Address: " + mContact.getAddress());
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
//                        Log.d("onPageSelected", "page 2" + " - Job: " + mContact.getJob());
//                        Log.d("onPageSelected", "page 2" + " - FirstName: " + mContact.getFirstName());
//                        Log.d("onPageSelected", "page 2" + " - LasttName: " + mContact.getLastName());
//                        Log.d("onPageSelected", "page 2" + " - Phone: " + mContact.getPhoneNumber());
//                        Log.d("onPageSelected", "page 2" + " - Address: " + mContact.getAddress());
                        break;

                    case 2:
                        tvTitle.setText(R.string.done);
                        btnNext.setVisibility(View.INVISIBLE);
                        btnSave.setVisibility(View.VISIBLE);
                        // setFields();
                    //    fragmentDone.setContact(mContact);
                        Log.d("onPageSelected", "page 3" + " - Job: " + mContact.getJob());
                        Log.d("onPageSelected", "page 3" + " - FirstName: " + mContact.getFirstName());
                        Log.d("onPageSelected", "page 3" + " - LasttName: " + mContact.getLastName());
                        Log.d("onPageSelected", "page 3" + " - Phone: " + mContact.getPhoneNumber());
                        Log.d("onPageSelected", "page 3" + " - Address: " + mContact.getAddress());
                        fragCurrent = (Fragment) customViewPager.getAdapter().instantiateItem(customViewPager, customViewPager.getCurrentItem());
                        if (fragCurrent instanceof FragmentDone){
                            ((FragmentDone) fragCurrent).setContact(mContact);
                        }
                        break;
                }
                fragCurrent = (Fragment) customViewPager.getAdapter().instantiateItem(customViewPager, customViewPager.getCurrentItem());
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


//        mContact.setFirstName(frag1.getFirstName());
//        mContact.setAddress(frag1.getAddress());
//        mContact.setLastName(frag1.getLastName());
//        mContact.setPhoneNumber(frag1.getPhone());
//        mContact.setGender(frag2.getGender());
//        mContact.setMaritalStatus(frag2.getMaritalStatus());
//        mContact.setJob(frag2.getJob());
//        mContact.setEmail(frag2.getEmail());

        Log.d("contact", "contact " + mContact.getFirstName());
        // frag3.setContact(mContact);

    }

    public Contact getContact() {
        return mContact;
    }

    @Override
    public void onFragmentFirstStepEditTextFilled(String firstName, String lastName, String phone, String address) {
        mContact.setFirstName(firstName);
        mContact.setLastName(lastName);
        mContact.setPhoneNumber(phone);
        mContact.setAddress(address);
    }

    @Override
    public void onFragmentSecondStepEditTextFilled(String job, Contact.MaritalStatus maritalStatus, Contact.Gender gender, String email) {
        mContact.setJob(job);
        mContact.setMaritalStatus(maritalStatus);
        mContact.setGender(gender);
        mContact.setEmail(email);
    }
}
