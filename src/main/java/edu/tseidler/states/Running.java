package edu.tseidler.states;

class Running extends GameState {

    public Running(GameState previousState) {
        super(previousState);
    }

    @Override
    GameState getNextState() {
        return new GameOverState(this);
    }
}
