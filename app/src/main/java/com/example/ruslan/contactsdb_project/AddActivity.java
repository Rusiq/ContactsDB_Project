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


    String TabFragmentDone;

    public void setTabFragmentDone(String t) {
        TabFragmentDone = t;
    }

    public String getTabFragmentDone(){
        return TabFragmentDone;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitle.setText(R.string.step_1);

        frag2 = (FragmentSecondStep) getSupportFragmentManager().findFragmentById(R.id.frag2);
        frag1 = (FragmentFirstStep) getSupportFragmentManager().findFragmentById(R.id.frag1);
        frag3 = (FragmentDone) getSupportFragmentManager().findFragmentById(R.id.frag3);


        customViewPager = (CustomViewPager) findViewById(R.id.custom_view_pager);
        indicator = (MaterialIndicator) findViewById(R.id.materialIndicator);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        customViewPager.setAdapter(pagerAdapter);
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
                        break;

                    case 1:
                        Log.d("STATUS1", String.valueOf(status1));
                        tvTitle.setText(R.string.step_2);
                        btnSave.setVisibility(View.INVISIBLE);
                        if (!status1 && !status2) {
                            btnNext.setVisibility(View.VISIBLE);
                        } else btnNext.setVisibility(View.INVISIBLE);

                        if (!status1 && !status2) {
                            customViewPager.setDirection(CustomViewPager.SwipeDirection.all);
                        } else customViewPager.setDirection(CustomViewPager.SwipeDirection.left);
                        break;

                    case 2:
                        tvTitle.setText(R.string.done);
                        btnNext.setVisibility(View.INVISIBLE);
                        btnSave.setVisibility(View.VISIBLE);
                        setFields();
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
    }

    public void setFields() {
        frag3.tvFirstName.setText(frag1.etFirstName.getText().toString());
        frag3.tvPhone.setText(frag1.etPhone.getText().toString());
        frag3.tvJob.setText(frag2.etJob.getText().toString());

        if (frag1.etLastName.getText().toString().trim().equals("")) {
            frag3.llLastName.setVisibility(View.GONE);
        } else frag3.tvLastName.setText(frag1.etLastName.getText().toString());

        if (frag1.etAddress.getText().toString().trim().equals("")) {
            frag3.llAddress.setVisibility(View.GONE);
        } else frag3.tvAddress.setText(frag1.etAddress.getText().toString());

        if (frag2.etEmail.getText().toString().trim().equals("")) {
            frag3.llLastName.setVisibility(View.GONE);
        } else frag3.tvEmail.setText(frag2.etEmail.getText().toString());

        switch (frag2.radioStatus.getCheckedRadioButtonId()){
            case R.id.chkMarried: frag3.tvStatus.setText("@string/married");
                break;
            case R.id.chkSingle: frag3.tvStatus.setText("@string/single");
                break;
            default: frag3.llStatus.setVisibility(View.GONE);
        }

        switch (frag2.radioGender.getCheckedRadioButtonId()){
            case R.id.chkFemale: frag3.tvGender.setText("@string/female");
                break;
            case R.id.chkMale: frag3.tvGender.setText("@string/male");
                break;
            default: frag3.llGender.setVisibility(View.GONE);
        }

    }
}
