package com.telegram.ccyrate.bot.state;

public class BotStateMgr {

    private static BotStateMgr instance;

    private BotState currentState;

    private BotStateMgr() {
        currentState = new StopState();
    }

    public static BotStateMgr getInstance() {
        if (instance == null) {
            instance = new BotStateMgr();
        }
        return instance;
    }

    public void setState(BotState currentState) {
        this.currentState = currentState;
    }

    public BotState getState() {
        return currentState;
    }

    public boolean isBotStarted() {
        return currentState instanceof StartState;
    }
}
