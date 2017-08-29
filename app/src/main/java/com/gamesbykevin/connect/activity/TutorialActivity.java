package com.gamesbykevin.connect.activity;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gamesbykevin.connect.R;
import com.gamesbykevin.connect.fragment.TutorialPageFragment;

import java.util.ArrayList;
import java.util.List;

public class TutorialActivity extends FragmentActivity {

    //our pager object that allows horizontal swiping
    private ViewPager customPager;

    //container for the page dots
    private LinearLayout listPageContainer;

    //array of our images representing the page dots
    private ImageView[] listPageImages;

    /**
     * The total number of pages
     */
    public static final int PAGES = 6;

    /**
     * Spacing between each pager dot
     */
    public static final int PAGE_DOT_PADDING = 5;

    //the current page index
    public static int CURRENT_PAGE = 0;

    //temp value to check if we tried to scroll out of bounds
    private static int TMP_CURRENT_PAGE = 0;

    //our list of pages for the pager
    private List<TutorialPageFragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        //get our pages container
        listPageContainer = (LinearLayout)findViewById(R.id.listPageContainer);

        //get our view pager
        customPager = (ViewPager) findViewById(R.id.customPager);

        //create new array list
        fragments = new ArrayList<>();

        //create and assign our adapter
        getCustomPager().setAdapter(new TutorialPagerAdapter(getFragmentManager()));

        //cache all pages to prevent memory leak
        getCustomPager().setOffscreenPageLimit(PAGES);

        //setup the page dots on the bottom
        setupPagerIndicatorDots();

        //add view pager listener
        addPagerListener();

        //default the first page as selected
        listPageImages[0].setImageResource(R.drawable.tab_indicator_selected);
    }

    @Override
    public void onStart() {

        //call parent
        super.onStart();
    }

    @Override
    public void onResume() {

        //call parent
        super.onResume();

        //make sure the current page is displayed
        getCustomPager().setCurrentItem(CURRENT_PAGE);
    }

    @Override
    protected void onDestroy() {

        //call parent
        super.onDestroy();

        customPager = null;
        fragments = null;
        listPageContainer = null;
        listPageImages = null;
    }

    private ViewPager getCustomPager() {
        return this.customPager;
    }

    private List<TutorialPageFragment> getFragments() {
        return this.fragments;
    }

    private void addPagerListener() {

        //add listener so we update our list page images
        getCustomPager().addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //do we need to do anything here
            }

            @Override
            public void onPageSelected(int position) {

                //store the current page
                CURRENT_PAGE = position;

                //when a page has changed, make each icon the default
                for (int i = 0; i < listPageImages.length; i++) {
                    listPageImages[i].setImageResource(R.drawable.tab_indicator_default);
                }

                //then use the selected icon for our current page
                listPageImages[CURRENT_PAGE].setImageResource(R.drawable.tab_indicator_selected);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {

                    case ViewPager.SCROLL_STATE_DRAGGING:

                        //store the current page
                        TMP_CURRENT_PAGE = CURRENT_PAGE;
                        break;

                    case ViewPager.SCROLL_STATE_IDLE:

                        //if we tried to scroll and the page didn't change, we are out of bounds
                        if (TMP_CURRENT_PAGE == CURRENT_PAGE) {

                            //scroll to the other side
                            if (CURRENT_PAGE == 0) {
                                getCustomPager().setCurrentItem(PAGES - 1, true);
                            } else if (CURRENT_PAGE == PAGES - 1) {
                                getCustomPager().setCurrentItem(0, true);
                            }
                        }
                        break;

                    case ViewPager.SCROLL_STATE_SETTLING:
                        //if settling on a new page change this value to something invalid
                        TMP_CURRENT_PAGE = -1;
                        break;
                }
            }
        });
    }

    /**
     * Setup the UI for the pager dots
     */
    private void setupPagerIndicatorDots() {

        //the array size will match the number of pages we have
        listPageImages = new ImageView[PAGES];

        //create our page dots
        for (int i = 0; i < listPageImages.length; i++) {

            //create new page dot image
            listPageImages[i] = new ImageView(this);

            //create our layout parameters
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            //provide space between each page icon
            params.setMargins(PAGE_DOT_PADDING, 0, PAGE_DOT_PADDING, 0);

            //update with the specified layout
            listPageImages[i].setLayoutParams(params);

            //default the icon
            listPageImages[i].setImageResource(R.drawable.tab_indicator_default);
            //listPageImages[i].setAlpha(0.4f);

            //pass index through to navigate page(s) directly
            final int index = i;

            //what do we do if the user clicks on the page icon
            listPageImages[i].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    //on click, directly move to the page
                    getCustomPager().setCurrentItem(index);
                    //view.setAlpha(0.2f);
                }
            });

            //add the page dot to the page container
            listPageContainer.addView(listPageImages[i]);

            //make sure the ui is displayed
            listPageContainer.bringToFront();
        }
    }

    /**
     * A simple pager adapter for all our pages
     */
    private class TutorialPagerAdapter extends FragmentStatePagerAdapter {

        public TutorialPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {

            //check to see if we already have the fragment
            for (int i = 0; i < getFragments().size(); i++) {
                if (getFragments().get(i).getPageNumber() == position)
                    return getFragments().get(position);
            }

            //create it since the fragment does not exist
            TutorialPageFragment fragment = TutorialPageFragment.create(position);

            //add to array list
            getFragments().add(fragment);

            //return result
            return fragment;
        }

        @Override
        public int getCount() {
            return PAGES;
        }
    }

    @Override
    public void onBackPressed() {

        //call parent
        super.onBackPressed();
    }

}