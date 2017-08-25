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

        TextView textHeader = (TextView)rootView.findViewById(R.id.textHeader);
        textHeader.setText(getString(R.string.text_level) + " " + (getPageNumber() + 1));

        //make sure the correct image is shown
        ImageView imageView = (ImageView)rootView.findViewById(R.id.levelSelectShape);

        int typeValue = OptionsActivity.OPTION_BOARD_SHAPE.getValue();

        //set the correct image
        if (typeValue == Board.Shape.Square.getValue()) {
            imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.level_select_square));
        } else if (typeValue == Board.Shape.Hexagon.getValue()) {
            imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.level_select_hexagon));
        } else if (typeValue == Board.Shape.Diamond.getValue()) {
            imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.level_select_diamond));
        } else {
            throw new RuntimeException("Type value not defined: " + typeValue);
        }

        //return our altered view
        return rootView;
    }

    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return pageNumber;
    }
}