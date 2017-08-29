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

public class TutorialPageFragment extends Fragment {

    /**
     * The argument key for the page number this fragment represents.
     */
    public static final String ARG_PAGE = "page";

    //the fragment's page number
    private int pageNumber;

    //store our view reference
    private ViewGroup view;

    /**
     * Factory method for this fragment class.
     * @param pageNumber The desired page #
     * @return The fragment representing the current page #
     */
    public static TutorialPageFragment create(int pageNumber) {

        TutorialPageFragment fragment = new TutorialPageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public TutorialPageFragment() {
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
        this.view = (ViewGroup) inflater.inflate(R.layout.fragment_tutorial_page, container, false);

        ImageView imageView = (ImageView)view.findViewById(R.id.tutorialImage);
        TextView textView = (TextView)view.findViewById(R.id.instructionsText);

        final int resIdImage;
        final int resIdText;

        switch (getPageNumber()) {

            case 0:
                resIdImage = R.drawable.instructions1;
                resIdText = R.string.tutorial_instructions_1;
                break;

            case 1:
                resIdImage = R.drawable.instructions2;
                resIdText = R.string.tutorial_instructions_2;
                break;

            case 2:
                resIdImage = R.drawable.instructions3;
                resIdText = R.string.tutorial_instructions_3;
                break;

            case 3:
                resIdImage = R.drawable.instructions4;
                resIdText = R.string.tutorial_instructions_4;
                break;

            case 4:
                resIdImage = R.drawable.instructions5;
                resIdText = R.string.tutorial_instructions_5;
                break;

            case 5:
                resIdImage = R.drawable.instructions6;
                resIdText = R.string.tutorial_instructions_6;
                break;


            default:
                throw new RuntimeException("Page # not defined: " + getPageNumber());
        }

        //update bitmap accordingly
        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), resIdImage));

        //assign the appropriate instruction text
        textView.setText(resIdText);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onResume() {

        //call parent
        super.onResume();
    }

    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return pageNumber;
    }
}