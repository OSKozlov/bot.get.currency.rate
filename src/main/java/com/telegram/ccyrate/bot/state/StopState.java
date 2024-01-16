package com.telegram.ccyrate.bot.state;

public class StopState implements BotState {

    @Override
    public void changeState(BotStateMgr stateContext) {
        stateContext.setState(this);
    }
}
