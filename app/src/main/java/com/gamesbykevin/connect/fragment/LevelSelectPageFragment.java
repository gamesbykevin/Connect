package com.gamesbykevin.connect.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gamesbykevin.connect.R;

/**
 * Created by Kevin on 8/23/2017.
 */
public class LevelSelectPageFragment extends Fragment {

    /**
     * The argument key for the page number this fragment represents.
     */
    public static final String ARG_PAGE = "page";

    /**
     * The fragment's page number
     */
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
        pageNumber = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout containing a title and body text.
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_level_select_page, container, false);

        TextView textHeader = (TextView)rootView.findViewById(R.id.textHeader);
        textHeader.setText("Level " + (getPageNumber() + 1));

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