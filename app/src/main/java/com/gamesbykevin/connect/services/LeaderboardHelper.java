package com.gamesbykevin.connect.services;

import com.gamesbykevin.connect.R;
import com.gamesbykevin.connect.board.Board;

import static com.gamesbykevin.connect.activity.LevelSelectActivity.CURRENT_PAGE;

/**
 * Created by Kevin on 8/29/2017.
 */
public class LeaderboardHelper {

    public static void updateLeaderboard(final BaseGameActivity activity, final Board board, final int seconds) {

        final int resId;

        switch (board.getType()) {
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
                throw new RuntimeException("Shape not defined: " + board.getType());
        }

        activity.updateLeaderboard(resId, seconds);
    }
}
