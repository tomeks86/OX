package edu.tseidler;

import edu.tseidler.model.BoardField;
import edu.tseidler.model.Player;
import edu.tseidler.model.PlayerList;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test
public class PlayerTest {
    private Player player1;
    private Player player2;
    private PlayerList playerList;

    @BeforeMethod
    public void setUpPlayers() {
        player1 = Player.first("stefan", BoardField.O);
        player2 = Player.second(player1, "franek");
    }

    public void shouldCreatePlayers() {
        SoftAssert sa = new SoftAssert();
        sa.assertEquals(player1.name, "stefan");
        sa.assertEquals(player2.name, "franek");
        sa.assertEquals(player1.mark, BoardField.O);
        sa.assertEquals(player2.mark, BoardField.X);
        sa.assertTrue(player1.first);
        sa.assertFalse(player2.first);
        sa.assertAll();
    }

    public void shouldAddPlayersInGoodOrder() {
        playerList = new PlayerList();
        playerList.add(player1);
        playerList.add(player2);

        SoftAssert sa = new SoftAssert();
        sa.assertEquals(playerList.getNext(), player1);
        sa.assertEquals(playerList.getNext(), player2);
        sa.assertAll();
    }
}
