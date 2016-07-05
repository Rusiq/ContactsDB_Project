package com.example.ruslan.contactsdb_project;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.badoualy.stepperindicator.StepperIndicator;
import com.matthewtamlin.sliding_intro_screen_library.buttons.IntroButton;
import com.matthewtamlin.sliding_intro_screen_library.core.IntroActivity;
import com.matthewtamlin.sliding_intro_screen_library.indicators.DotIndicator;

import java.util.ArrayList;
import java.util.Collection;


public class AddActivity extends AppCompatActivity {

    public static final String DISPLAY_ONCE_PREFS = "display_only_once_spfile";
    public static final String DISPLAY_ONCE_KEY = "display_only_once_spkey";

    private PagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    TextView tvTitle;
   // private DotIndicator dotIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        tvTitle = (TextView) findViewById(R.id.tvTitle);

        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(3);

      //  dotIndicator = (DotIndicator) findViewById(R.id.dotIndicator);


        StepperIndicator indicator = (StepperIndicator) findViewById(R.id.stepper_indicator);
        indicator.setViewPager(mViewPager, true);


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

   /* @Override
    protected Collection<Fragment> generatePages(Bundle savedInstanceState) {
        ArrayList<Fragment> pages = new ArrayList<>();

        //pages.add();


        return pages;
    }

    @Override
    protected IntroButton.Behaviour generateFinalButtonBehaviour() {
        final SharedPreferences sp = getSharedPreferences(DISPLAY_ONCE_PREFS, MODE_PRIVATE);
        final SharedPreferences.Editor pendingEdits = sp.edit().putBoolean(DISPLAY_ONCE_KEY, true);

       // return new IntroButton.ProgressToNextActivity(, pendingEdits);
        return null;
    }*/
}
