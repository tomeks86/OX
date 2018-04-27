package edu.tseidler.model;

import static edu.tseidler.service.Color.*;

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

        @Override
        public String toString() {
            return GREEN + "O" + RESET;
        }
    }, X {
        @Override
        BoardField other() {
            return O;
        }

        @Override
        public String toString() {
            return RED + "X" + RESET;
        }
    };

    abstract BoardField other();
}
