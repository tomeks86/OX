package edu.tseidler.states;

import edu.tseidler.model.Board;
import edu.tseidler.model.Language;
import edu.tseidler.model.PlayerList;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class LanguageSetupState extends GameState {
    protected LanguageSetupState(Consumer<String> output, Supplier<String> input, Language lang, Board board, PlayerList players) {
        super(output, input, lang, board, players);
    }

    @Override
    public GameState getNextState() {
        output.accept(lang.get("CHOOSE_LANGUAGE"));
        lang = switchLang(input.get());
        return new BoardSetUpState(output, input, lang, board, players);
    }

    private Language switchLang(String languageShort) {
        if ("pl".contains(languageShort))
            return new Language(languageShort);
        return this.lang;
    }
}
