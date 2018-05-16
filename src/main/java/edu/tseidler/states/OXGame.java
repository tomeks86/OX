package edu.tseidler.states;

import edu.tseidler.Main;
import edu.tseidler.model.*;
import edu.tseidler.service.FileLineConsumer;
import edu.tseidler.service.FileLineSupplier;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class OXGame {
    private static final Supplier<String> DEFAULT_STRING_SUPPLIER = new Scanner(System.in)::nextLine;
    private static final Consumer<String> DEFAULT_STRING_CONSUMER = System.out::println;
    private static final Consumer<String> ERROR_CONSUMER = System.err::println;


    private static final Logger logger = Logger.getLogger(Main.class);
    private static final int MAXIMUM_NUMBER_OF_ROUNDS = 3;
    private static Properties properties;
    private final Supplier<String> inputSupplier;
    private final Consumer<String> output;
    private final Board board;
    private final Language lang;
    private final PlayerList playerList;
    private GameState currentState;

    public OXGame(String settingsFileName) {
        properties = readProperties(settingsFileName);
        inputSupplier = getStringSupplier();
        output = getStringConsumer();
        lang = loadLanguage("");
        board = getBoard();
        playerList = getPlayerList();

        this.currentState = new SetupState(output, inputSupplier, lang, board, playerList);
    }

    public void start() {
        while (GameState.gamesPlayed <= MAXIMUM_NUMBER_OF_ROUNDS) {
            doOneCycle();
        }
    }

    private void doOneCycle() {
        this.currentState = currentState.getNextState();
    }

    private PlayerList getPlayerList() {
        PlayerList playerList = new PlayerList();
        String player1Name = (String) properties.getOrDefault("player1_name",
                defaultProperties.get("player1_name"));
        String player2Name = (String) properties.getOrDefault("player2_name",
                defaultProperties.get("player2_name"));
        BoardField player1Mark = playerMarkSetup();
        BoardField player2Mark = player1Mark.other();
        boolean isPlayer1First = isStartingPlayerSetup();
        boolean isPlayer2First = !isPlayer1First;
        playerList.add(new Player(player1Name, player1Mark, isPlayer1First));
        playerList.add(new Player(player2Name, player2Mark, isPlayer2First));
        return playerList;
    }

    private BoardField playerMarkSetup() {
        BoardField player1Mark = BoardField.X;
        try {
            player1Mark = BoardField.valueOf((String) properties.getOrDefault("player1_mark",
                    defaultProperties.get("player1_mark")));
            if (player1Mark == BoardField.EMPTY) {
                player1Mark = BoardField.valueOf(defaultProperties.get("player1_mark"));
                logger.log(Level.WARN, "cannot use EMPTY for player mark!");
            }
        } catch (IllegalArgumentException e) {
            logger.log(Level.WARN, "wrong player1 mark, should be X or O, leaving default: " + defaultProperties.get("player1_mark"));
        }
        return player1Mark;
    }

    private Boolean isStartingPlayerSetup() {
        String valueRead = (String) properties.getOrDefault("player1_first",
                defaultProperties.get("player1_first"));
        if (!(valueRead.equalsIgnoreCase("true") || valueRead.equalsIgnoreCase("false"))) {
            String isFirst = defaultProperties.get("player1_first");
            logger.log(Level.WARN, "player1_first should be true or false, leaving default: " + isFirst);
            valueRead = isFirst;
        }
        return Boolean.valueOf(valueRead);
    }

    static Language loadLanguage(String langShort) {
        if (!availableLangShorts.contains(langShort))
            langShort = !availableLangShorts.contains(properties.get("language")) ?
                    defaultProperties.get("language") :
                    (String) properties.get("language");
        return new Language(langShort);
    }

    static Set<String> availableLangShorts = new HashSet<String>() {{
        add("en");
        add("pl");
    }};

    private static Map<String, String> defaultProperties = new HashMap<String, String>() {{
        put("input", "stdin");
        put("output", "stdout");
        put("language", "en");
        put("player1_name", "Jacek");
        put("player1_mark", "X");
        put("player1_first", "true");
        put("player2_name", "Placek");
        put("board_rows", "3");
        put("board_cols", "3");
        put("board_winn", "3");
    }};

    private Board getBoard() {
        int board_rows;
        int board_cols;
        int board_winn;
        try {
            board_rows = Integer.valueOf((String) properties.get("board_rows"));
            board_cols = Integer.valueOf((String) properties.get("board_cols"));
            board_winn = Integer.valueOf((String) properties.get("board_winn"));
        } catch (Exception e) {
            logger.log(Level.ERROR, "error parsing board parameters from settings file");
            board_rows = Integer.valueOf(defaultProperties.get("board_rows"));
            board_cols = Integer.valueOf(defaultProperties.get("board_cols"));
            board_winn = Integer.valueOf(defaultProperties.get("board_winn"));
        }
        BoardParameters parameters = new BoardParameters(board_rows, board_cols, board_winn);
        return new Board(parameters);
    }

    private Consumer<String> getStringConsumer() {
        Map<String, Consumer<String>> consumerMap = new HashMap<String, Consumer<String>>() {{
            put("stdout", DEFAULT_STRING_CONSUMER);
            put("stderr", ERROR_CONSUMER);
            try {
                Path path = Paths.get((String) properties.getOrDefault("output_file", "output.log"));
                put("file", new FileLineConsumer(path));
            } catch (IOException e) {
                logger.log(Level.WARN, "file operation error - falling back to console output");
                put("file", DEFAULT_STRING_CONSUMER);
            }
        }};
        return consumerMap.get(properties.get("output"));
    }

    private Supplier<String> getStringSupplier() {
        Map<String, Supplier<String>> supplierMap = new HashMap<String, Supplier<String>>() {{
            put("stdin", DEFAULT_STRING_SUPPLIER);
            try {
                put("file", new FileLineSupplier(Main.class.getResourceAsStream((String) properties.getOrDefault("input_file", "scenarios/error"))));
            } catch (NullPointerException e) {
                logger.log(Level.WARN, "input file in settings.properties doesn't exist - falling back to console input");
                put("file", DEFAULT_STRING_SUPPLIER);
            }
        }};
        return supplierMap.getOrDefault(properties.get("input"), DEFAULT_STRING_SUPPLIER);
    }

    private Properties readProperties(String settings_file) {
        Properties properties = new Properties();
        if (settings_file.isEmpty()) {
            readPropertiesFromResources(properties);
        } else {
            readPropertiesFromFile(settings_file, properties);
        }
        return properties;
    }

    private void readPropertiesFromFile(String settings_file, Properties properties) {
        try (InputStream inputStream = Files.newInputStream(Paths.get(settings_file))) {
            properties.load(inputStream);
        } catch (IOException e) {
            logger.log(Level.ERROR, e.getMessage());
            e.printStackTrace();
        }
    }

    private void readPropertiesFromResources(Properties properties) {
        try (InputStream inputStream = Main.class.getResourceAsStream("settings.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            logger.log(Level.ERROR, e.getMessage());
            e.printStackTrace();
        }
    }
}
