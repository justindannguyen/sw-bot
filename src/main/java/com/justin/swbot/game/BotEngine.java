/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.game;

/**
 * <p>
 * General, main purpose is to hold the bot loop.
 * <p>
 * Bot engine contains the infinitive loop in order to identify the current game state. It asks suggestion, command from
 * the directors for what to do, where to click.
 *
 * @author tuan3.nguyen@gmail.com
 */
public final class BotEngine extends Thread {

    private static final BotEngine ENGINE = new BotEngine();

    public static final BotEngine get() {
        return ENGINE;
    }

    private volatile boolean running = false;

    private BotEngine() {
        // This is the hidden constructor for singleton classes to make sure it can't be instanced by mistake.
        start();
    }

    /**
     * <p>
     * Check if the bot engine is running or fall into sleep mode.
     *
     * <p>
     * When the bot engine is in sleep mode, it still keep the infinitive loop but does nothings.
     *
     * @return <code>true</code> if the bot is running.
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
        while (true) {
            try {
                if (!running) {
                    sleep(500);
                }

                // TODO to be implemented
            } catch (final Exception e) {
                // Provide the global catch exception so that the engine loop is infinitive.
                // TODO to be implemented
                e.printStackTrace();
            }
        }
    }

    /**
     * Set the bot engine as running or fall into sleep mode.
     *
     * @param running
     *            <code>true</code> to active the running mode, otherwise fall into sleep mode.
     */
    public void setRunning(final boolean running) {
        this.running = running;
    }
}
