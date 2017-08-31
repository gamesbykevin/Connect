package com.gamesbykevin.connect.activity;

import android.app.Activity;
import android.content.Intent;
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

import com.gamesbykevin.androidframeworkv2.util.UtilityHelper;
import com.gamesbykevin.connect.R;
import com.gamesbykevin.connect.board.Board;
import com.gamesbykevin.connect.fragment.LevelSelectPageFragment;
import com.gamesbykevin.connect.game.Game;
import com.gamesbykevin.connect.services.BaseGameActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 8/23/2017.
 */
public class LevelSelectActivity extends BaseGameActivity {

    //our pager object that allows horizontal swiping
    private ViewPager customPager;

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

        Level(int cols, int rows) {
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

    /**
     * The total number of pages/levels
     */
    public static final int PAGES = Level.values().length;

    /**
     * Spacing between each pager dot
     */
    public static final int PAGE_DOT_PADDING = 5;

    //the current page index
    public static int CURRENT_PAGE = 0;

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

        //create new array list
        fragments = new ArrayList<>();

        //create and assign our adapter
        getCustomPager().setAdapter(new LevelSelectPagerAdapter(getFragmentManager()));

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

        //play the main theme
        playTheme();
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

    private List<LevelSelectPageFragment> getFragments() {
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
    private class LevelSelectPagerAdapter extends FragmentStatePagerAdapter {

        public LevelSelectPagerAdapter(FragmentManager fragmentManager) {
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
            LevelSelectPageFragment fragment = LevelSelectPageFragment.create(position);

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

    /**
     * We want to play this level
     * @param view
     */
    public void onClickPlay(View view) {

        //the level is determined  by the current page
        Level level = Level.values()[getCustomPager().getCurrentItem()];

        //set board size
        Board.BOARD_COLS = level.getCols();
        Board.BOARD_ROWS = level.getRows();

        //set to loading phase
        Game.STEP = Game.Step.Loading;

        //start our game
        startActivity(new Intent(this, GameActivity.class));
    }

    public void onClickLeaderboard(View view) {

        final int resId;

        switch (OptionsActivity.OPTION_BOARD_SHAPE) {
            case Square:

                switch (CURRENT_PAGE) {

                    case 0:
                        resId = R.string.leaderboard_level_1_square;
                        break;

                    case 1:
                        resId = R.string.leaderboard_level_2_square;
                        break;

                    case 2:
                        resId = R.string.leaderboard_level_3_square;
                        break;

                    case 3:
                        resId = R.string.leaderboard_level_4_square;
                        break;

                    case 4:
                        resId = R.string.leaderboard_level_5_square;
                        break;

                    case 5:
                        resId = R.string.leaderboard_level_6_square;
                        break;

                    case 6:
                        resId = R.string.leaderboard_level_7_square;
                        break;

                    case 7:
                        resId = R.string.leaderboard_level_8_square;
                        break;

                    case 8:
                        resId = R.string.leaderboard_level_9_square;
                        break;

                    case 9:
                        resId = R.string.leaderboard_level_10_square;
                        break;

                    default:
                        throw new RuntimeException("Page not defined: " + CURRENT_PAGE);
                }
                break;

            case Hexagon:

                switch (CURRENT_PAGE) {

                    case 0:
                        resId = R.string.leaderboard_level_1_hexagon;
                        break;

                    case 1:
                        resId = R.string.leaderboard_level_2_hexagon;
                        break;

                    case 2:
                        resId = R.string.leaderboard_level_3_hexagon;
                        break;

                    case 3:
                        resId = R.string.leaderboard_level_4_hexagon;
                        break;

                    case 4:
                        resId = R.string.leaderboard_level_5_hexagon;
                        break;

                    case 5:
                        resId = R.string.leaderboard_level_6_hexagon;
                        break;

                    case 6:
                        resId = R.string.leaderboard_level_7_hexagon;
                        break;

                    case 7:
                        resId = R.string.leaderboard_level_8_hexagon;
                        break;

                    case 8:
                        resId = R.string.leaderboard_level_9_hexagon;
                        break;

                    case 9:
                        resId = R.string.leaderboard_level_10_hexagon;
                        break;

                    default:
                        throw new RuntimeException("Page not defined: " + CURRENT_PAGE);
                }
                break;

            case Diamond:

                switch (CURRENT_PAGE) {

                    case 0:
                        resId = R.string.leaderboard_level_1_diamond;
                        break;

                    case 1:
                        resId = R.string.leaderboard_level_2_diamond;
                        break;

                    case 2:
                        resId = R.string.leaderboard_level_3_diamond;
                        break;

                    case 3:
                        resId = R.string.leaderboard_level_4_diamond;
                        break;

                    case 4:
                        resId = R.string.leaderboard_level_5_diamond;
                        break;

                    case 5:
                        resId = R.string.leaderboard_level_6_diamond;
                        break;

                    case 6:
                        resId = R.string.leaderboard_level_7_diamond;
                        break;

                    case 7:
                        resId = R.string.leaderboard_level_8_diamond;
                        break;

                    case 8:
                        resId = R.string.leaderboard_level_9_diamond;
                        break;

                    case 9:
                        resId = R.string.leaderboard_level_10_diamond;
                        break;

                    default:
                        throw new RuntimeException("Page not defined: " + CURRENT_PAGE);
                }
                break;

            default:
                throw new RuntimeException("Shape not defined: " + OptionsActivity.OPTION_BOARD_SHAPE);
        }

        //save the leaderboard id
        LEADERBOARD_ID = getString(resId);

        //display the leaderboard
        displayLeaderboardUI(LEADERBOARD_ID);
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