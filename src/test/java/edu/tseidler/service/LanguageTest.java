package edu.tseidler.service;

import edu.tseidler.model.Language;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LanguageTest {
    private Language lang;

    @Test
    public void shouldReturnUnchangedString() {
        lang = new Language("en");
        String simpleSentence = "some time ago...";

        assertEquals(Language.build(simpleSentence), simpleSentence);
    }

    @Test
    public void shouldTranslateUpperCaseWord() {
        lang = new Language("en");
        String simpleSentence = "this is a test: PLAYER_SETUP did it work? FIRST";
        String expectedTranslation = "this is a test: enter name of the first player did it work? beginning";

        assertEquals(Language.build(simpleSentence), expectedTranslation);
    }

    @Test
    public void shouldTryToTranslateWithTroubles() {
        lang = new Language("en");
        String simpleSentence = "this is a test: NONEXISTENT hello";
        String expectedTranslation = "this is a test: NONEXISTENT [not defined in the language file] hello";

        assertEquals(Language.build(simpleSentence), expectedTranslation);
    }

    @Test
    public void shouldTranslateInPolish() {
        lang = new Language("pl");
        String simpleSentence = "this is a test: NONEXISTENT hello";
        String expectedTranslation = "this is a test: NONEXISTENT [nie znaleziono w pliku języka] hello";

        assertEquals(Language.build(simpleSentence), expectedTranslation);
    }
}
