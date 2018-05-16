package edu.tseidler.states;

import edu.tseidler.model.Board;
import edu.tseidler.model.Language;
import edu.tseidler.model.PlayerList;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class SetupState extends GameState {
    SetupState(Consumer<String> output, Supplier<String> input, Language lang, Board board, PlayerList players) {
        super(output, input, lang, board, players);
    }

    @Override
    public GameState getNextState() {
        output.accept(Language.get("WELCOME"));
        String choice;
        choice = promptAndGetResponse();
        String defaultChoice = Language.get("NO");
        while (isChoiceDIfferentThanYesOrNo(choice)) {
            if (choice.isEmpty()) {
                choice = defaultChoice;
                break;
            }
            choice = promptAndGetResponse();
        }
        if (choice.equalsIgnoreCase(Language.get("YES")))
            return new LanguageSetupState(this);
        else
            return new PlayerSetUpState(this);
    }

    private String promptAndGetResponse() {
        String choice;
        output.accept(Language.build("_SETUP_ _YESORNNO_"));
        choice = input.get();
        return choice;
    }

    private boolean isChoiceDIfferentThanYesOrNo(String resp) {
        return !(Language.get("YES").equalsIgnoreCase(resp) || Language.get("NO").equalsIgnoreCase(resp));
    }

}
