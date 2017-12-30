package com.justin.swbot.ui;

import com.justin.swbot.game.GameState;

/**
 * Created by akivamu on 25/12/17.
 */

public interface HomeView {
    void updateGameStatus(final GameState state);

    void updateStatus(final String message);
}
