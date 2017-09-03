package com.gamesbykevin.connect.board;

import android.content.SharedPreferences;

import com.gamesbykevin.androidframeworkv2.base.Disposable;
import com.gamesbykevin.connect.util.UtilityHelper;
import com.gamesbykevin.connect.R;
import com.gamesbykevin.connect.activity.BaseActivity;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import static com.gamesbykevin.connect.util.UtilityHelper.DEBUG;

/**
 * Created by Kevin on 8/27/2017.
 */

public class Boards implements Disposable {

    //this will contain all the stats for our levels
    private HashMap<Board.Shape, ArrayList<Integer>> data;

    private final String statsKey;

    public Boards(BaseActivity activity) {

        //we need to create this type in order to de-serialize the json back to hash map object
        Type type = new TypeToken<HashMap<Board.Shape, ArrayList<Integer>>>(){}.getType();

        //get our data from the shared preferences
        this.data = (HashMap<Board.Shape, ArrayList<Integer>>)activity.getObjectValue(R.string.game_stats_file_key, type);

        //get the stats key
        this.statsKey = activity.getString(R.string.game_stats_file_key);

        //if still null create hash map
        if (this.data == null)
            this.data = new HashMap<>();
    }

    @Override
    public void dispose() {

        if (data != null) {
            data.clear();
            data = null;
        }
    }

    /**
     * Get the size of the array for the given shape.<br>
     * This will help determine if we have previously played levels
     * @param type The desired shape
     * @return The size of the list for the given shape, if not found 0 is returned
     */
    public int getSize(Board.Shape type) {
        if (data == null)
            return 0;
        if (data.get(type) == null)
            return 0;

        //return the size of the array
        return data.get(type).size();
    }

    /**
     * Get the duration to beat the level
     * @param type The shape we are playing
     * @param index Level index
     * @return The duration it took to beat the level (in seconds), if the data does not exist 0 will be returned
     */
    public int getTime(Board.Shape type, int index) {

        //if the data does not exist, return 0 seconds
        if (data == null)
            return 0;
        if (data.get(type) == null)
            return 0;
        if (data.get(type).size() <= index)
            return 0;

        //return the duration for the best time
        return data.get(type).get(index);
    }

    /**
     * Update our board stats with the duration
     * @param type The type of shape we were playing
     * @param index The level index this stat belongs to
     * @param seconds Duration to beat level
     * @return true if stat was updated and is high score, false if it wasn't the high score and not saved
     */
    public boolean update(Board.Shape type, int index, int seconds) {

        //what is our result
        boolean result = false;

        //if container is null, create it
        if (data == null)
            data = new HashMap<>();

        //if shape list doesn't exist, create it
        if (data.get(type) == null)
            data.put(type, new ArrayList<Integer>());

        if (data.get(type).size() <= index) {

            //if the level index doesn't exist yet, add it
            data.get(type).add(seconds);

            //we added a new score
            result = true;

        } else {

            //if our time is faster than the existing time, we have a new record
            if (seconds < data.get(type).get(index)) {

                //we have a new record
                data.get(type).set(index, seconds);

                //we updated our score
                result = true;
            }
        }

        //if we made a change, save it
        if (result)
            save();

        //return our result
        return result;
    }

    /**
     * Store the data in the shared preferences
     */
    private void save() {

        //get the editor so we can change the shared preferences
        SharedPreferences.Editor editor = BaseActivity.getSharedPreferences().edit();

        //convert object to json string
        final String json = BaseActivity.GSON.toJson(data);

        //print data
        if (DEBUG)
            UtilityHelper.logEvent(json);

        //convert to json string and store in preferences
        editor.putString(statsKey, json);

        //make it final by committing the change
        editor.commit();
    }
}