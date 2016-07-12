package com.example.ruslan.contactsdb_project.dots;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ruslan.contactsdb_project.CustomViewPager;
import com.example.ruslan.contactsdb_project.R;


/**
 * Created by Ann Rodina LMC on 06.07.16, Mana App Studio Ltd.
 */
public class MaterialIndicator extends RelativeLayout implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private TextView btnBack;
    private ImageView btnSave;

    private CirclePageIndicator indicator;
    private ImageView btnNext;
    private CustomViewPager viewPager;


    public MaterialIndicator(Context context) {
        super(context);
        init();
    }

    public MaterialIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MaterialIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        LayoutInflater inflater = (LayoutInflater)
                getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.material_indicator, this);
        btnBack = (TextView) findViewById(R.id.btnBack);
        indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        indicator.setFillColor(Color.BLACK);
        indicator.setStrokeColor(Color.BLACK);
        indicator.setStrokeWidth(0);
        indicator.setRadius(12);
        indicator.setPageColor(Color.WHITE);
        btnNext = (ImageView) findViewById(R.id.btnNext);
        btnSave = (ImageView) findViewById(R.id.btnSave);
        btnBack.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }

    public void setViewPager(CustomViewPager viewPager) {
        indicator.setViewPager(viewPager);
        this.viewPager = viewPager;
        viewPager.addOnPageChangeListener(this);
    }


    @Override
    public void onClick(View v) {
        if (viewPager == null)
            return;
        int page = viewPager.getCurrentItem();
        switch (v.getId()) {
            case R.id.btnNext:
                viewPager.setCurrentItem(page + 1);
                break;
            case R.id.btnBack:
                viewPager.setCurrentItem(page - 1);
                break;
            case R.id.btnSave:
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        viewPager.setPagingEnabled(position != 1);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
