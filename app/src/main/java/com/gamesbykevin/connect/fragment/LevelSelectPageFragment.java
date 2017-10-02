package com.gamesbykevin.connect.fragment;

import android.app.Fragment;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.gamesbykevin.connect.R;
import com.gamesbykevin.connect.activity.LevelSelectActivity;
import com.gamesbykevin.connect.activity.OptionsActivity;
import com.gamesbykevin.connect.board.Board;

import static android.view.View.GONE;
import static com.gamesbykevin.androidframeworkv2.activity.BaseActivity.GSON;
import static com.gamesbykevin.androidframeworkv2.activity.BaseActivity.getSharedPreferences;
import static com.gamesbykevin.connect.activity.MainActivity.getBoards;

/**
 * Created by Kevin on 8/23/2017.
 */
public class LevelSelectPageFragment extends Fragment {

    /**
     * The argument key for the page number this fragment represents.
     */
    public static final String ARG_PAGE = "page";

    //the fragment's page number
    private int pageNumber;

    //store our view reference
    private ViewGroup view;

    //are we resuming a saved game?
    private boolean resume = false;

    /**
     * Factory method for this fragment class.
     * @param pageNumber The desired page #
     * @return The fragment representing the current page #
     */
    public static LevelSelectPageFragment create(int pageNumber) {

        LevelSelectPageFragment fragment = new LevelSelectPageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;

    }

    public LevelSelectPageFragment() {
        //default constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        //call parent
        super.onCreate(savedInstanceState);

        //get the arguments passed
        this.pageNumber = getArguments().getInt(ARG_PAGE);

        //are we resuming a saved game?
        resume = (this.pageNumber >= LevelSelectActivity.Level.values().length);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //inflate the layout to access the ui elements
        this.view = (ViewGroup) inflater.inflate(R.layout.fragment_level_select_page, container, false);

        //make sure the correct level size is shown
        setupImageViewLevelSize((ImageView)view.findViewById(R.id.levelSize), LevelSelectActivity.Level.values()[getPageNumber()]);

        //display the level # image
        setupImageViewLevelNumber((ImageView)view.findViewById(R.id.levelNumber));

        //make sure the correct image shape is shown
        setupImageViewShape((ImageView)view.findViewById(R.id.levelSelectShape));

        //return our altered view
        return view;
    }

    @Override
    public void onResume() {

        //call parent
        super.onResume();

        //get our ui buttons so we can manipulate
        Button buttonLeader = (Button) view.findViewById(R.id.buttonLeaderboard);
        Button buttonPlay = (Button) view.findViewById(R.id.buttonPlay);
        ImageView imageOverlay = (ImageView) view.findViewById(R.id.overlayDisabled);
        ImageView bestTimeView = (ImageView) view.findViewById(R.id.imageViewBest);

        //each digit for our time
        int value1 = -1, value2 = -1, value3 = -1, value4 = -1;

        if (resume) {

            //update the buttons correctly
            buttonLeader.setVisibility(View.INVISIBLE);
            buttonPlay.setText(getString(R.string.button_text_resume));
            imageOverlay.setVisibility(View.GONE);
            bestTimeView.setVisibility(View.INVISIBLE);

            //get the time from our shared preferences
            String[] data = getSharedPreferences().getString(getString(R.string.saved_game_timer_key), "").split(",");

            //load the time value
            value1 = Integer.parseInt(data[0]);
            value2 = Integer.parseInt(data[1]);
            value3 = Integer.parseInt(data[2]);
            value4 = Integer.parseInt(data[3]);

        } else {

            //update best time
            int seconds = getBoards().getTime(OptionsActivity.OPTION_BOARD_SHAPE, getPageNumber());

            //how much time is remaining
            int remaining = seconds;

            //calculate each number for minutes and seconds
            if (seconds > 0) {
                value1 = (int) (remaining / 600); //minutes 10's
                remaining = remaining - (value1 * 600); //take away the remaining
                value2 = (int) (remaining / 60); //minutes 1's
                remaining = remaining - (value2 * 60);
                value3 = (int) (remaining / 10); //seconds 10's
                remaining = remaining - (value3 * 10);
                value4 = remaining;
            }

            //is this level enabled
            if (getBoards().getSize(OptionsActivity.OPTION_BOARD_SHAPE) >= getPageNumber()) {

                //make sure buttons are enabled
                buttonLeader.setEnabled(true);
                buttonPlay.setEnabled(true);
                imageOverlay.setVisibility(View.GONE);
            } else {

                //disable everything
                buttonLeader.setEnabled(false);
                buttonPlay.setEnabled(false);
                imageOverlay.setVisibility(View.VISIBLE);
            }
        }

        //update the timer ui
        updateImageViewTimer(value1, (ImageView)view.findViewById(R.id.bestTime1));
        updateImageViewTimer(value2, (ImageView)view.findViewById(R.id.bestTime2));
        updateImageViewTimer(value3, (ImageView)view.findViewById(R.id.bestTime3));
        updateImageViewTimer(value4, (ImageView)view.findViewById(R.id.bestTime4));
    }

    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        if (resume) {
            return getSharedPreferences().getInt(getString(R.string.saved_game_level_key), 0);
        } else {
            return pageNumber;
        }
    }

    private void setupImageViewLevelSize(ImageView imageView, LevelSelectActivity.Level level) {

        //image resource id
        int resId;

        switch(level) {

            case Level01:
                resId = R.drawable.size_3;
                break;

            case Level02:
                resId = R.drawable.size_5;
                break;

            case Level03:
                resId = R.drawable.size_7;
                break;

            case Level04:
                resId = R.drawable.size_9;
                break;

            case Level05:
                resId = R.drawable.size_11;
                break;

            case Level06:
                resId = R.drawable.size_13;
                break;

            case Level07:
                resId = R.drawable.size_15;
                break;

            case Level08:
                resId = R.drawable.size_17;
                break;

            case Level09:
                resId = R.drawable.size_19;
                break;

            case Level10:
                resId = R.drawable.size_21;
                break;

            default:
                throw new RuntimeException("Level not defined: " + level.toString());
        }

        //set our bitmap accordingly
        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), resId));
    }

    private void setupImageViewLevelNumber(ImageView imageView) {

        //image resource id
        int resId;

        switch (getPageNumber()) {

            case 0:
                resId = R.drawable.level_1;
                break;

            case 1:
                resId = R.drawable.level_2;
                break;

            case 2:
                resId = R.drawable.level_3;
                break;

            case 3:
                resId = R.drawable.level_4;
                break;

            case 4:
                resId = R.drawable.level_5;
                break;

            case 5:
                resId = R.drawable.level_6;
                break;

            case 6:
                resId = R.drawable.level_7;
                break;

            case 7:
                resId = R.drawable.level_8;
                break;

            case 8:
                resId = R.drawable.level_9;
                break;

            case 9:
                resId = R.drawable.level_10;
                break;

            default:
                throw new RuntimeException("Page number not defined: " + getPageNumber());
        }

        //set our bitmap accordingly
        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), resId));
    }

    private void setupImageViewShape(ImageView imageView) {

        //the image resource id
        int resId;

        //get the shape
        Board.Shape tmp = OptionsActivity.OPTION_BOARD_SHAPE;

        //if resuming saved game, get shape from shared preferences
        if (resume)
            tmp = GSON.fromJson(getSharedPreferences().getString(getString(R.string.saved_game_shape_key), ""), Board.Shape.class);

        //set the correct image
        switch (tmp) {

            case Square:
                if (getPageNumber() == 0) {
                    resId = R.drawable.level_select_square_1;
                } else if (getPageNumber() == 1) {
                    resId = R.drawable.level_select_square_2;
                } else if (getPageNumber() == 2) {
                    resId = R.drawable.level_select_square_3;
                } else if (getPageNumber() == 3) {
                    resId = R.drawable.level_select_square_4;
                } else if (getPageNumber() == 4) {
                    resId = R.drawable.level_select_square_5;
                } else if (getPageNumber() == 5) {
                    resId = R.drawable.level_select_square_6;
                } else if (getPageNumber() == 6) {
                    resId = R.drawable.level_select_square_7;
                } else if (getPageNumber() == 7) {
                    resId = R.drawable.level_select_square_8;
                } else if (getPageNumber() == 8) {
                    resId = R.drawable.level_select_square_9;
                } else if (getPageNumber() == 9) {
                    resId = R.drawable.level_select_square_10;
                } else {
                    resId = R.drawable.level_select_square_10;
                }
                break;

            case Hexagon:
                if (getPageNumber() == 0) {
                    resId = R.drawable.level_select_hexagon_1;
                } else if (getPageNumber() == 1) {
                    resId = R.drawable.level_select_hexagon_2;
                } else if (getPageNumber() == 2) {
                    resId = R.drawable.level_select_hexagon_3;
                } else if (getPageNumber() == 3) {
                    resId = R.drawable.level_select_hexagon_4;
                } else if (getPageNumber() == 4) {
                    resId = R.drawable.level_select_hexagon_5;
                } else if (getPageNumber() == 5) {
                    resId = R.drawable.level_select_hexagon_6;
                } else if (getPageNumber() == 6) {
                    resId = R.drawable.level_select_hexagon_7;
                } else if (getPageNumber() == 7) {
                    resId = R.drawable.level_select_hexagon_8;
                } else if (getPageNumber() == 8) {
                    resId = R.drawable.level_select_hexagon_9;
                } else if (getPageNumber() == 9) {
                    resId = R.drawable.level_select_hexagon_10;
                } else {
                    resId = R.drawable.level_select_hexagon_10;
                }
                break;

            case Diamond:
                if (getPageNumber() == 0) {
                    resId = R.drawable.level_select_diamond_1;
                } else if (getPageNumber() == 1) {
                    resId = R.drawable.level_select_diamond_2;
                } else if (getPageNumber() == 2) {
                    resId = R.drawable.level_select_diamond_3;
                } else if (getPageNumber() == 3) {
                    resId = R.drawable.level_select_diamond_4;
                } else if (getPageNumber() == 4) {
                    resId = R.drawable.level_select_diamond_5;
                } else if (getPageNumber() == 5) {
                    resId = R.drawable.level_select_diamond_6;
                } else if (getPageNumber() == 6) {
                    resId = R.drawable.level_select_diamond_7;
                } else if (getPageNumber() == 7) {
                    resId = R.drawable.level_select_diamond_8;
                } else if (getPageNumber() == 8) {
                    resId = R.drawable.level_select_diamond_9;
                } else if (getPageNumber() == 9) {
                    resId = R.drawable.level_select_diamond_10;
                } else {
                    resId = R.drawable.level_select_diamond_10;
                }
                break;

            default:
                throw new RuntimeException("Type value not defined: " + OptionsActivity.OPTION_BOARD_SHAPE.toString());
        }

        //set our bitmap accordingly
        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), resId));
    }

    private void updateImageViewTimer(final int value, final ImageView imageView) {

        //resource id
        final int resId;

        //if we are at the max displayed value, everything will be 9
        if (value > 9) {

            resId = R.drawable.nine;

        } else {

            //find the appropriate drawable
            switch (value) {

                case -1:
                    resId = R.drawable.na;
                    break;

                case 0:
                    resId = R.drawable.zero;
                    break;

                case 1:
                    resId = R.drawable.one;
                    break;

                case 2:
                    resId = R.drawable.two;
                    break;

                case 3:
                    resId = R.drawable.three;
                    break;

                case 4:
                    resId = R.drawable.four;
                    break;

                case 5:
                    resId = R.drawable.five;
                    break;

                case 6:
                    resId = R.drawable.six;
                    break;

                case 7:
                    resId = R.drawable.seven;
                    break;

                case 8:
                    resId = R.drawable.eight;
                    break;

                case 9:
                    resId = R.drawable.nine;
                    break;

                default:
                    throw new RuntimeException("value not defined: " + value);
            }
        }

        //update bitmap accordingly
        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), resId));
    }
}