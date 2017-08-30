package com.gamesbykevin.connect.services;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.gamesbykevin.androidframeworkv2.util.UtilityHelper;
import com.gamesbykevin.connect.activity.BaseActivity;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;

import static com.gamesbykevin.androidframeworkv2.util.UtilityHelper.DEBUG;

/**
 * Created by Kevin on 8/1/2017.
 */
public abstract class BaseGameActivity extends BaseActivity implements GameHelper.GameHelperListener {

    // The game helper object. This class is mainly a wrapper around this object.
    protected GameHelper mHelper;

    // We expose these constants here because we don't want users of this class
    // to have to know about GameHelper at all.
    public static final int CLIENT_GAMES = GameHelper.CLIENT_GAMES;
    public static final int CLIENT_PLUS = GameHelper.CLIENT_PLUS;
    public static final int CLIENT_ALL = GameHelper.CLIENT_ALL;

    // Requested clients. By default, that's just the games client.
    protected int mRequestedClients = CLIENT_GAMES;

    private final static String TAG = "BaseGameActivity";

    /**
     * Did we want to access the achievements
     */
    public boolean ACCESS_ACHIEVEMENT = false;

    /**
     * Did we want to access the leader boards
     */
    public boolean ACCESS_LEADERBOARD = false;

    /**
     * The leader board id we want to display
     */
    public static String LEADERBOARD_ID = "";

    /**
     * Do we skip future login? (this is in case the player does not want to sign in)
     */
    public static boolean BYPASS_LOGIN = false;

    /**
     * Constructs a BaseGameActivity with default client (GamesClient)
     */
    protected BaseGameActivity() {
        super();
    }

    /**
     * Constructs a BaseGameActivity with the requested clients.
     * @param requestedClients The requested clients (a combination of CLIENT_GAMES,
     *         CLIENT_PLUS).
     */
    protected BaseGameActivity(int requestedClients) {
        super();
        setRequestedClients(requestedClients);
    }

    /**
     * Sets the requested clients. The preferred way to set the requested clients is
     * via the constructor, but this method is available if for some reason your code
     * cannot do this in the constructor. This must be called before onCreate or getGameHelper()
     * in order to have any effect. If called after onCreate()/getGameHelper(), this method
     * is a no-op.
     *
     * @param requestedClients A combination of the flags CLIENT_GAMES, CLIENT_PLUS
     *         or CLIENT_ALL to request all available clients.
     */
    protected void setRequestedClients(int requestedClients) {
        mRequestedClients = requestedClients;
    }

    public GameHelper getGameHelper() {
        if (mHelper == null) {
            mHelper = new GameHelper(this, mRequestedClients);
            mHelper.enableDebugLog(DEBUG);
        }
        return mHelper;
    }

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        if (mHelper == null) {
            getGameHelper();
        }
        mHelper.setup(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //only call game helper once
        mHelper.onStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mHelper.onStop();
    }

    @Override
    protected void onActivityResult(int request, int response, Intent data) {
        super.onActivityResult(request, response, data);
        mHelper.onActivityResult(request, response, data);
    }

    public GoogleApiClient getApiClient() {
        return getGameHelper().getApiClient();
    }

    public void unlockAchievement(final int resId) {
        try {
            String achievementId = getString(resId);
            Games.Achievements.unlock(getApiClient(), achievementId);
        } catch (Exception e) {
            UtilityHelper.handleException(e);
        }
    }

    public void incrementAchievement(final int resId, final int incrementValue) {
        try {
            String achievementId = getString(resId);
            Games.Achievements.increment(getApiClient(), achievementId, incrementValue);
        } catch (Exception e) {
            UtilityHelper.handleException(e);
        }
    }

    public void trackEvent(final int resId) {
        trackEvent(resId, 1);
    }

    public void trackEvent(final int resId, final int incrementValue) {
        try {
            String eventId = getString(resId);
            Games.Events.increment(getApiClient(), eventId, incrementValue);
        } catch (Exception e) {
            UtilityHelper.handleException(e);
        }
    }

    public void updateLeaderboard(final int resId, final long score) {
        try {
            String leaderboardId = getString(resId);
            Games.Leaderboards.submitScore(getApiClient(), leaderboardId, score);
        } catch (Exception e) {
            UtilityHelper.handleException(e);
        }
    }

    protected boolean isSignedIn() {
        return mHelper.isSignedIn();
    }

    protected void beginUserInitiatedSignIn() {
        mHelper.beginUserInitiatedSignIn();
    }

    protected void signOut() {
        try {
            mHelper.signOut();
        } catch (Exception e) {
            UtilityHelper.handleException(e);
        }
    }

    protected void showAlert(String message) {
        mHelper.makeSimpleDialog(message).show();
    }

    protected void showAlert(String title, String message) {
        mHelper.makeSimpleDialog(title, message).show();
    }

    protected void enableDebugLog(boolean enabled) {
        if (mHelper != null) {
            mHelper.enableDebugLog(enabled);
        }
    }

    @Deprecated
    protected void enableDebugLog(boolean enabled, String tag) {
        Log.w(TAG, "BaseGameActivity.enabledDebugLog(bool,String) is deprecated. Use enableDebugLog(boolean)");
        enableDebugLog(enabled);
    }

    protected String getInvitationId() {
        return mHelper.getInvitationId();
    }

    protected void reconnectClient() {
        mHelper.reconnectClient();
    }

    protected boolean hasSignInError() {
        return mHelper.hasSignInError();
    }

    protected GameHelper.SignInFailureReason getSignInError() {
        return mHelper.getSignInError();
    }

    public void onClickAchievements(View view) {

        //if we are connected, display default achievements ui
        if (getApiClient().isConnected()) {
            displayAchievementUI();
        } else {
            //UtilityHelper.logEvent("beginUserInitiatedSignIn() before");
            //if not connected, re-attempt google play login
            beginUserInitiatedSignIn();
            //UtilityHelper.logEvent("beginUserInitiatedSignIn() after");

            //flag that we want to open the achievements
            ACCESS_ACHIEVEMENT = true;
        }
    }

    public void displayAchievementUI() {

        if (getApiClient().isConnected()) {

            //UtilityHelper.logEvent("Displaying achievement ui");
            startActivityForResult(Games.Achievements.getAchievementsIntent(getApiClient()), 1);
        } else {

            //if not connected, re-attempt google play login
            beginUserInitiatedSignIn();

            //flag that we want to open the achievements
            ACCESS_ACHIEVEMENT = true;
        }
    }

    public void displayLeaderboardUI(final String leaderboardId) {

        if (getApiClient().isConnected()) {

            //UtilityHelper.logEvent("Displaying leaderboard ui " + leaderboardId);
            startActivityForResult(Games.Leaderboards.getLeaderboardIntent(getApiClient(), leaderboardId), 1);
        } else {

            //if not connected, re-attempt google play login
            beginUserInitiatedSignIn();

            //flag that we want to open the achievements
            ACCESS_LEADERBOARD = true;
        }
    }

    @Override
    public void onSignInSucceeded() {
        UtilityHelper.displayMessage(this, "Google Play login worked!");

        if (ACCESS_ACHIEVEMENT) {

            //if we just came from achievements button and are now signed in, display ui
            displayAchievementUI();

            //flag back false
            ACCESS_ACHIEVEMENT = false;

        } else if (ACCESS_LEADERBOARD) {

            //if we just logged in trying to access leaderboard display it
            displayLeaderboardUI(LEADERBOARD_ID);

            ACCESS_LEADERBOARD = false;
        }

        //don't bypass auto login
        BYPASS_LOGIN = false;
    }

    @Override
    public void onSignInFailed() {
        UtilityHelper.displayMessage(this, "Google play login failed!");

        //bypass auto login
        BYPASS_LOGIN = true;
    }
}