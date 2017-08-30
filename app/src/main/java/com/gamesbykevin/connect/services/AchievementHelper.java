package com.gamesbykevin.connect.services;

import com.gamesbykevin.connect.R;
import com.gamesbykevin.connect.board.Board;

import static com.gamesbykevin.connect.activity.LevelSelectActivity.CURRENT_PAGE;

/**
 * Created by Kevin on 8/29/2017.
 */

public class AchievementHelper {

    public static void completedGame(final BaseGameActivity activity, final Board board) {

        //we beat our first puzzle
        activity.unlockAchievement(R.string.achievement_complete_first_puzzle);

        //keep track of our increment achievements
        activity.incrementAchievement(R.string.achievement_complete_10_puzzles, 1);
        activity.incrementAchievement(R.string.achievement_complete_50_puzzles, 1);

        //resource id of the achievement unlocked
        final int resId;

        //determine which game we completed
        switch (board.getType()) {

            case Square:

                switch (CURRENT_PAGE) {

                    case 0:
                        resId = R.string.achievement_level_1_square;
                        break;

                    case 1:
                        resId = R.string.achievement_level_2_square;
                        break;

                    case 2:
                        resId = R.string.achievement_level_3_square;
                        break;

                    case 3:
                        resId = R.string.achievement_level_4_square;
                        break;

                    case 4:
                        resId = R.string.achievement_level_5_square;
                        break;

                    case 5:
                        resId = R.string.achievement_level_6_square;
                        break;

                    case 6:
                        resId = R.string.achievement_level_7_square;
                        break;

                    case 7:
                        resId = R.string.achievement_level_8_square;
                        break;

                    case 8:
                        resId = R.string.achievement_level_9_square;
                        break;

                    case 9:
                        resId = R.string.achievement_level_10_square;
                        break;

                    default:
                        throw new RuntimeException("Page not defined: " + CURRENT_PAGE);
                }
                break;

            case Hexagon:

                switch (CURRENT_PAGE) {

                    case 0:
                        resId = R.string.achievement_level_1_hexagon;
                        break;

                    case 1:
                        resId = R.string.achievement_level_2_hexagon;
                        break;

                    case 2:
                        resId = R.string.achievement_level_3_hexagon;
                        break;

                    case 3:
                        resId = R.string.achievement_level_4_hexagon;
                        break;

                    case 4:
                        resId = R.string.achievement_level_5_hexagon;
                        break;

                    case 5:
                        resId = R.string.achievement_level_6_hexagon;
                        break;

                    case 6:
                        resId = R.string.achievement_level_7_hexagon;
                        break;

                    case 7:
                        resId = R.string.achievement_level_8_hexagon;
                        break;

                    case 8:
                        resId = R.string.achievement_level_9_hexagon;
                        break;

                    case 9:
                        resId = R.string.achievement_level_10_hexagon;
                        break;

                    default:
                        throw new RuntimeException("Page not defined: " + CURRENT_PAGE);
                }
                break;

            case Diamond:

                switch (CURRENT_PAGE) {

                    case 0:
                        resId = R.string.achievement_level_1_diamond;
                        break;

                    case 1:
                        resId = R.string.achievement_level_2_diamond;
                        break;

                    case 2:
                        resId = R.string.achievement_level_3_diamond;
                        break;

                    case 3:
                        resId = R.string.achievement_level_4_diamond;
                        break;

                    case 4:
                        resId = R.string.achievement_level_5_diamond;
                        break;

                    case 5:
                        resId = R.string.achievement_level_6_diamond;
                        break;

                    case 6:
                        resId = R.string.achievement_level_7_diamond;
                        break;

                    case 7:
                        resId = R.string.achievement_level_8_diamond;
                        break;

                    case 8:
                        resId = R.string.achievement_level_9_diamond;
                        break;

                    case 9:
                        resId = R.string.achievement_level_10_diamond;
                        break;

                    default:
                        throw new RuntimeException("Page not defined: " + CURRENT_PAGE);
                }
                break;

            default:
                throw new RuntimeException("Type not defined " + board.getType());
        }

        //unlock the identified achievement
        activity.unlockAchievement(resId);
    }
}
