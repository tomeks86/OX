package edu.tseidler;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

class LanguageSetupState implements GameState {
    private final Map<String, String> lang;

    public LanguageSetupState(Map<String, String> lang) {
        this.lang = lang;
    }

    @Override
    public void printTo(Consumer<String> output, Map<String, String> lang) {
        output.accept(lang.get("CHOOSE_LANGUAGE"));
    }

    @Override
    public GameState getNextState(Supplier<String> inputSupplier, Map<String, String> lang) {
        lang = switchLang(inputSupplier.get());
        return new GameState() {
            @Override
            public void printTo(Consumer<String> output, Map<String, String> lang) {
                output.accept(lang.get("BOARD_SETUP"));
            }

            @Override
            public GameState getNextState(Supplier<String> inputSupplier, Map<String, String> lang) {
                int[] boardDimensions = new int[2];
                try {
//                    boardDimensions = InputParser.parseBoardSize(inputSupplier.get());
                } catch (IllegalStateException e) {
                    boardDimensions = new int[] {3, 3};
                }
                return null;
            }
        };
    }

    private Map<String,String> switchLang(String s) {
        if ("en pl".contains(s))
            return LanguageSelector.selectLanguage(s);
        return lang;
    }
}
