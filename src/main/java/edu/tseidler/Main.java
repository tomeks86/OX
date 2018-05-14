package edu.tseidler;

import edu.tseidler.states.OXGame;

public class Main {

    public static void main(String[] args) {
        if (args.length > 0) {
            new OXGame(args[0]).start();
        } else {
            new OXGame(Main.class.getResource("settings.properties").getPath()).start();
        }
    }
}
