package com.example.ruslan.contactsdb_project;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.ruslan.contactsdb_project.dots.MaterialIndicator;


public class AddActivity extends AppCompatActivity /*implements Fragment1.OnStatusFieldsFragment1Listener, Fragment2.OnStatusFieldsFragment2Listener*/ {
    // private PagerAdapter mPagerAdapter;
    private CustomViewPager customViewPager;
    TextView tvTitle;
    private MaterialIndicator indicator;
    boolean status1 = true, status2 = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        /*FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();*/


        tvTitle = (TextView) findViewById(R.id.tvTitle);

        customViewPager = (CustomViewPager) findViewById(R.id.custom_view_pager);
        indicator = (MaterialIndicator) findViewById(R.id.materialIndicator);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        customViewPager.setAdapter(pagerAdapter);
        indicator.setViewPager(customViewPager);


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
                        break;
                    case 1:
                        tvTitle.setText(R.string.step_2);
                        if (status1 == false && status2 == false) {
                            customViewPager.setDirection(CustomViewPager.SwipeDirection.all);
                        } else customViewPager.setDirection(CustomViewPager.SwipeDirection.left);
                        break;
                    case 2:
                        tvTitle.setText(R.string.done);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public void setStatus(boolean value) {
        status1 = Fragment1.isEmptyFieldsFragment1;
        status2 = Fragment2.isEmptyFieldsFragment2;
    }


  /*  @Override
    public void onStatusFieldsFragment1() {


    }

    @Override
    public void onStatusFieldsFragment2() {

    }*/
}
