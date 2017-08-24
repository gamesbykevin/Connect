package com.gamesbykevin.connect.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gamesbykevin.connect.R;
import com.gamesbykevin.connect.board.Board;
import com.gamesbykevin.connect.fragment.LevelSelectPageFragment;
import com.gamesbykevin.connect.game.Game;

/**
 * Created by Kevin on 8/23/2017.
 */
public class LevelSelectActivity extends FragmentActivity {

    /**
     * The number of pages to show in this demo.
     */
    private static final int NUM_PAGES = 10;

    /**
     * Our pager object that allows horizontal swiping
     */
    private ViewPager customPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    //container for the page dots
    private LinearLayout listPageContainer;

    //array of our images representing the page dots
    private ImageView[] listPageImages;

    public enum Level {

        Level01(3,3),
        Level02(5,5),
        Level03(7,7),
        Level04(9,9),
        Level05(11, 11),
        Level06(13,13),
        Level07(15,15),
        Level08(17,17),
        Level09(19,19),
        Level10(20,20);

        private int cols, rows;

        private Level(int cols, int rows) {
            this.cols = cols;
            this.rows = rows;
        }

        public int getCols() {
            return this.cols;
        }

        public int getRows() {
            return this.rows;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //call parent
        super.onCreate(savedInstanceState);

        //set the appropriate content view
        setContentView(R.layout.activity_level_select);

        //get our pages container
        listPageContainer = (LinearLayout)findViewById(R.id.listPageContainer);

        //get our view pager
        customPager = (ViewPager) findViewById(R.id.customPager);
        mPagerAdapter = new LevelSelectPagerAdapter(getFragmentManager());
        getCustomPager().setAdapter(mPagerAdapter);

        //setup the page dots on the bottom
        setupPagerIndicatorDots();

        //add listener so we update our list page images
        customPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //do we need to do anything here
            }

            @Override
            public void onPageSelected(int position) {

                //when a page has changed, make each icon the default
                for (int i = 0; i < listPageImages.length; i++) {
                    listPageImages[i].setImageResource(R.drawable.tab_indicator_default);
                }

                //then use the selected icon for our current page
                listPageImages[position].setImageResource(R.drawable.tab_indicator_selected);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //do we need to do anything here?
            }
        });

        //default the first page as selected
        listPageImages[0].setImageResource(R.drawable.tab_indicator_selected);
    }

    private ViewPager getCustomPager() {
        return this.customPager;
    }

    private void setupPagerIndicatorDots() {

        //the array size will match the number of pages we have
        listPageImages = new ImageView[NUM_PAGES];

        //create our page dots
        for (int i = 0; i < listPageImages.length; i++) {

            //create new page dot image
            listPageImages[i] = new ImageView(this);

            //create our layout parameters
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            //provide space between each page icon
            params.setMargins(5, 0, 5, 0);

            //update with the specified layout
            listPageImages[i].setLayoutParams(params);

            //default the icon
            listPageImages[i].setImageResource(R.drawable.tab_indicator_default);
            //ivArrayDotsPager[i].setAlpha(0.4f);

            //pass index through to navigate page(s)
            final int index = i;

            //what do we do if the user clicks on the page icon
            listPageImages[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            //do nothing
            case android.R.id.home:
                return true;

            //go back a page (if available)
            case R.id.action_previous:
                getCustomPager().setCurrentItem(getCustomPager().getCurrentItem() - 1);
                return true;

            //go to the next page (if available)
            case R.id.action_next:
                getCustomPager().setCurrentItem(getCustomPager().getCurrentItem() + 1);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A simple pager adapter for all our pages
     */
    private class LevelSelectPagerAdapter extends FragmentStatePagerAdapter {

        public LevelSelectPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return LevelSelectPageFragment.create(position);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    /**
     * We want to play this level
     * @param view
     */
    public void onClickPlay(View view) {

        Level level = Level.values()[getCustomPager().getCurrentItem()];

        //set board size
        Board.BOARD_COLS = level.getCols();
        Board.BOARD_ROWS = level.getRows();

        //set to loading phase
        Game.STEP = Game.Step.Loading;

        startActivity(new Intent(this, GameActivity.class));
    }
}