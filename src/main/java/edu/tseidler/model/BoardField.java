package edu.tseidler.model;

import static edu.tseidler.service.Color.*;

public enum BoardField {
    EMPTY {
        @Override
        public BoardField other() {
            return EMPTY;
        }
    }, O {
        @Override
        public BoardField other() {
            return X;
        }

        @Override
        public String toString() {
            return GREEN + "O" + RESET;
        }
    }, X {
        @Override
        public BoardField other() {
            return O;
        }

        @Override
        public String toString() {
            return RED + "X" + RESET;
        }
    };

    public abstract BoardField other();
}
