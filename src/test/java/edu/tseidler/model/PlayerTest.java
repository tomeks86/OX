package edu.tseidler.model;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test
public class PlayerTest {
    public static final String PLAYER1_NAME = "stefan";
    public static final String PLAYER2_NAME = "franek";
    private Player player1;
    private Player player2;
    private PlayerList playerList;

    @BeforeMethod
    public void setUpPlayers() {
        player1 = new Player(PLAYER1_NAME, BoardField.O, true);
        player2 = Player.second(player1, PLAYER2_NAME);
    }

    public void shouldCreatePlayers() {
        SoftAssert sa = new SoftAssert();
        sa.assertEquals(player1.getName(), PLAYER1_NAME);
        sa.assertEquals(player2.getName(), PLAYER2_NAME);
        sa.assertEquals(player1.getMark(), BoardField.O);
        sa.assertEquals(player2.getMark(), BoardField.X);
        sa.assertTrue(player1.isFirst());
        sa.assertFalse(player2.isFirst());
        sa.assertAll();
    }

    public void shouldAddPlayersInGoodOrder() {
        playerList = new PlayerList();
        playerList.add(player1);
        playerList.add(player2);

        SoftAssert sa = new SoftAssert();
        sa.assertEquals(playerList.getNext(), player1);
        sa.assertEquals(playerList.getNext(), player2);
        sa.assertEquals(playerList.getNext(), player1);
        sa.assertEquals(playerList.getNext(), player2);
        sa.assertAll();
    }

    public void shouldSwitchBeginingPlayer() {
        playerList = new PlayerList();
        playerList.add(player1);
        playerList.add(player2);

        playerList.switchStarting();
        Player newPlayer1 = playerList.getNext();
        Player newPlayer2 = playerList.getNext();

        SoftAssert sa = new SoftAssert();
        sa.assertEquals(newPlayer1, player2);
        sa.assertEquals(newPlayer1.isFirst(), true);
        sa.assertEquals(newPlayer2, player1);
        sa.assertEquals(newPlayer2.isFirst(), false);
        sa.assertAll();
    }

    public void shouldNotSwitchBeginningPlayer() {
        playerList = new PlayerList();
        playerList.add(player2);
        playerList.add(player1);

        playerList.switchStarting();
        Player newPlayer1 = playerList.getNext();
        Player newPlayer2 = playerList.getNext();

        SoftAssert sa = new SoftAssert();
        sa.assertEquals(newPlayer1, player2);
        sa.assertEquals(newPlayer1.isFirst(), true);
        sa.assertEquals(newPlayer2, player1);
        sa.assertEquals(newPlayer2.isFirst(), false);
        sa.assertAll();
    }

    public void shouldNotSwitchBeginingPlayerAfterUsingSwitchTwice() {
        playerList = new PlayerList();
        playerList.add(player1);
        playerList.add(player2);

        playerList.switchStarting();
        playerList.switchStarting();
        Player newPlayer1 = playerList.getNext();
        Player newPlayer2 = playerList.getNext();

        SoftAssert sa = new SoftAssert();
        sa.assertEquals(newPlayer1, player1);
        sa.assertEquals(newPlayer1.isFirst(), true);
        sa.assertEquals(newPlayer2, player2);
        sa.assertEquals(newPlayer2.isFirst(), false);
        sa.assertAll();
    }

    public void shouldSwitchFirstAfterUsingSwitchWithNotFirstFirstSequence() {
        playerList = new PlayerList();
        playerList.add(player1);
        playerList.add(player2);
        playerList.getNext();

        playerList.switchStarting();

        SoftAssert sa = new SoftAssert();
        sa.assertEquals(player2.isFirst(), true);
        sa.assertEquals(player1.isFirst(), false);
        sa.assertAll();
    }
}
