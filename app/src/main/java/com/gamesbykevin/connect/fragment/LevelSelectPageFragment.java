package com.gamesbykevin.connect.fragment;

import android.app.Fragment;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gamesbykevin.connect.R;
import com.gamesbykevin.connect.activity.LevelSelectActivity;
import com.gamesbykevin.connect.activity.OptionsActivity;
import com.gamesbykevin.connect.board.Board;

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
        super.onCreate(savedInstanceState);

        //get the arguments passed
        this.pageNumber = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //inflate the layout to access the ui elements
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_level_select_page, container, false);

        //display the level # image
        setupImageViewLevelNumber((ImageView)rootView.findViewById(R.id.levelNumber));

        //make sure the correct level size is shown
        setupImageViewLevelSize((ImageView)rootView.findViewById(R.id.levelSize), LevelSelectActivity.Level.values()[getPageNumber()]);

        //make sure the correct image shape is shown
        setupImageViewShape((ImageView)rootView.findViewById(R.id.levelSelectShape));

        //return our altered view
        return rootView;
    }

    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return pageNumber;
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

        //get the current selected shape
        Board.Shape type = OptionsActivity.OPTION_BOARD_SHAPE;

        //the image resource id
        int resId;

        //set the correct image
        switch (type) {

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
                throw new RuntimeException("Type value not defined: " + type.toString());
        }

        //set our bitmap accordingly
        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), resId));
    }
}