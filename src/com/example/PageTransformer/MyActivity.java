package com.example.PageTransformer;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.nineoldandroids.view.ViewHelper;

import java.util.ArrayList;

public class MyActivity extends FragmentActivity {
    ViewPager mViewPager;
    TabsAdapter mTabsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewPager = new ViewPager(this);
        mViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View view, float v) {
                final float invt = Math.abs(Math.abs(v) - 1);
                ViewHelper.setAlpha(view, invt);
                ViewHelper.setScaleY(view, invt);
                ViewHelper.setScaleX(view, invt);
                ViewHelper.setRotation(view, v * 90);
            }
        });
        mViewPager.setId(R.id.pager);
        setContentView(mViewPager);


        mTabsAdapter = new TabsAdapter(this, mViewPager);
        mTabsAdapter.addItem(
                CountingFragment.class, null);
        mTabsAdapter.addItem(
                ArrayListFragment.class, null);
        mTabsAdapter.addItem(
                CursorFragment.class, null);


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("tab", getActionBar().getSelectedNavigationIndex());
    }

    /**
     * This is a helper class that implements the management of tabs and all
     * details of connecting a ViewPager with associated TabHost.  It relies on a
     * trick.  Normally a tab host has a simple API for supplying a View or
     * Intent that each tab will show.  This is not sufficient for switching
     * between pages.  So instead we make the content part of the tab host
     * 0dp high (it is not shown) and the TabsAdapter supplies its own dummy
     * view to show as the tab content.  It listens to changes in tabs, and takes
     * care of switch to the correct paged in the ViewPager whenever the selected
     * tab changes.
     */
    public static class TabsAdapter extends FragmentPagerAdapter
            implements ViewPager.OnPageChangeListener {
        private final Context mContext;
        private final ViewPager mViewPager;
        private ArrayList<Class<?>> mItems = new ArrayList<Class<?>>();

        public TabsAdapter(FragmentActivity activity, ViewPager pager) {
            super(activity.getSupportFragmentManager());
            mContext = activity;
            mViewPager = pager;
            mViewPager.setAdapter(this);
            mViewPager.setOnPageChangeListener(this);

        }

        public void addItem(Class<?> clss, Bundle args) {
            mItems.add(clss);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mItems.size();
        }

        @Override
        public Fragment getItem(int position) {
            Class info = mItems.get(position);
            return Fragment.instantiate(mContext, info.getName());
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }

    }
}
