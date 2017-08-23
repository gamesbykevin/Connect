package com.gamesbykevin.connect.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gamesbykevin.connect.R;

import java.util.ArrayList;

/**
 * Created by Kevin on 8/22/2017.
 */
public class CustomAdapter extends ArrayAdapter {

    //object reference
    private final Context context;

    //resource id for the layout
    private int resourceId;

    //list of levels we have beaten
    private ArrayList<Boolean> data;

    //images for completed and not-completed levels
    private Drawable imageGreen, imageRed;

    public CustomAdapter(Context context, int layoutResourceId, ArrayList<Boolean> data) {

        super(context, layoutResourceId, data);

        this.context = context;
        this.resourceId = layoutResourceId;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        //the item view
        View itemView = convertView;
        ViewHolder holder = null;

        if (itemView == null)
        {
            final LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = layoutInflater.inflate(resourceId, parent, false);

            holder = new ViewHolder();
            holder.imgItem = (ImageView)itemView.findViewById(R.id.imgItem);
            holder.txtItem = (TextView)itemView.findViewById(R.id.txtItem);
            itemView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)itemView.getTag();
        }

        //get our images if they don't yet exist
        if (imageRed == null)
            imageRed = ContextCompat.getDrawable(context, R.drawable.rotate_off);
        if (imageGreen == null)
            imageGreen = ContextCompat.getDrawable(context, R.drawable.rotate_on);

        if (data.get(position)) {
            holder.imgItem.setImageDrawable(imageGreen);
            holder.txtItem.setText("");
        } else {
            holder.imgItem.setImageDrawable(imageRed);
            holder.txtItem.setText("" + (position + 1));
        }

        //return the created item
        return itemView;
    }

    static class ViewHolder
    {
        //reference that we will need for each item
        ImageView imgItem;
        TextView txtItem;
    }
}