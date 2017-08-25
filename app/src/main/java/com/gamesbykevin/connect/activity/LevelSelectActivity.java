package com.gamesbykevin.connect.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gamesbykevin.connect.R;
import com.gamesbykevin.connect.board.Board;
import com.gamesbykevin.connect.fragment.LevelSelectPageFragment;
import com.gamesbykevin.connect.game.Game;
import com.gamesbykevin.connect.opengl.OpenGLRenderer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 8/23/2017.
 */
public class LevelSelectActivity extends FragmentActivity {

    //our pager object that allows horizontal swiping
    private ViewPager customPager;

    //the pager adapter, which provides the pages to the view pager widget.
    private PagerAdapter customPagerAdapter;

    //container for the page dots
    private LinearLayout listPageContainer;

    //array of our images representing the page dots
    private ImageView[] listPageImages;

    public enum Level {

        Level01(3,3),
        Level02(5,5),
        Level03(7,7),
        Level04(9,9),
        Level05(11,11),
        Level06(13,13),
        Level07(15,15),
        Level08(17,17),
        Level09(19,19),
        Level10(21,21);

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

    //the current page index
    private static int CURRENT_PAGE = 0;

    //temp value to check if we tried to scroll out of bounds
    private static int TMP_CURRENT_PAGE = 0;

    //our list of pages for the pager
    private List<LevelSelectPageFragment> fragments;

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
        customPagerAdapter = new LevelSelectPagerAdapter(getFragmentManager());
        getCustomPager().setAdapter(customPagerAdapter);

        getCustomPager().setClipToPadding(false);
        getCustomPager().setPadding(0,0,0,0);
        getCustomPager().setOffscreenPageLimit(3);

        //setup the page dots on the bottom
        setupPagerIndicatorDots();

        //add listener so we update our list page images
        customPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

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
                                customPager.setCurrentItem(Level.values().length - 1);
                            } else if (CURRENT_PAGE == Level.values().length - 1){
                                customPager.setCurrentItem(0);
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

        //default the first page as selected
        listPageImages[0].setImageResource(R.drawable.tab_indicator_selected);

        //create new array list
        fragments = new ArrayList<>();

        //add all fragments
        for (int i = 0; i < Level.values().length; i++) {
            fragments.add(LevelSelectPageFragment.create(i));
        }
    }

    private ViewPager getCustomPager() {
        return this.customPager;
    }

    private void setupPagerIndicatorDots() {

        //the array size will match the number of pages we have
        listPageImages = new ImageView[Level.values().length];

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
    private class LevelSelectPagerAdapter extends FragmentStatePagerAdapter {

        public LevelSelectPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
            //return LevelSelectPageFragment.create(position);
        }

        @Override
        public int getCount() {
            return Level.values().length;
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

        //reset the camera perspective as well
        OpenGLRenderer.resetZoom();

        startActivity(new Intent(this, GameActivity.class));
    }


    @Override
    public void onBackPressed() {

        //always go back to main activity
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(new Intent(this, MainActivity.class));
    }

}