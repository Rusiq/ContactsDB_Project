package com.example.ruslan.contactsdb_project;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.ruslan.contactsdb_project.dots.MaterialIndicator;


public class AddActivity extends AppCompatActivity {
   // private PagerAdapter mPagerAdapter;
    private CustomViewPager customViewPager;
    TextView tvTitle;
    private MaterialIndicator indicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        tvTitle = (TextView) findViewById(R.id.tvTitle);

        customViewPager = (CustomViewPager) findViewById(R.id.custom_view_pager);
        indicator = (MaterialIndicator) findViewById(R.id.materialIndicator);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        customViewPager.setAdapter(pagerAdapter);
        indicator.setViewPager(customViewPager);

        customViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position) {
                    case 0:
                        tvTitle.setText(R.string.step_1);
                        break;
                    case 1:
                        tvTitle.setText(R.string.step_2);
                        break;
                    case 2:
                        tvTitle.setText(R.string.done);
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

}
