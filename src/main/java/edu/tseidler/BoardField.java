package edu.tseidler;

public enum BoardField {
    EMPTY {
        @Override
        BoardField other() {
            return EMPTY;
        }
    }, O {
        @Override
        BoardField other() {
            return X;
        }
    }, X {
        @Override
        BoardField other() {
            return O;
        }
    };

    abstract BoardField other();
}
