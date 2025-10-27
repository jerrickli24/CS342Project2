import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import java.util.Random;
import java.util.Set;

//Neha Kamat, nkama4, UIN: 665218217
//Jerrick Li, jli326, UIN: 653147894

class KenoGameTest {
    
    private DisplayOdds displayOdds;
    private BetCard betCard;
    private Player player;
    private Winnings winnings;
    
    @BeforeEach
    void setUp() {
        displayOdds = new DisplayOdds();
        winnings = new Winnings();
        player = new Player();
    }
    
    //displayodds tests
    @Test
    @DisplayName("Test DisplayOdds returns correct winnings for Spot 1")
    void testDisplayOddsSpot1() {
        assertEquals(2, displayOdds.getWinnings(1, 1));
    }
    
    @Test
    @DisplayName("Test DisplayOdds returns correct winnings for Spot 4")
    void testDisplayOddsSpot4Match2() {
        assertEquals(1, displayOdds.getWinnings(4, 2));
    }
    
    @Test
    @DisplayName("Test DisplayOdds returns correct winnings for Spot 4 match 3")
    void testDisplayOddsSpot4Match3() {
        assertEquals(5, displayOdds.getWinnings(4, 3));
    }
    
    @Test
    @DisplayName("Test DisplayOdds returns correct winnings for Spot 4 match 4")
    void testDisplayOddsSpot4Match4() {
        assertEquals(75, displayOdds.getWinnings(4, 4));
    }
    
    @Test
    @DisplayName("Test DisplayOdds returns correct winnings for Spot 8")
    void testDisplayOddsSpot8Match8() {
        assertEquals(10000, displayOdds.getWinnings(8, 8));
    }
    
    @Test
    @DisplayName("Test DisplayOdds returns correct winnings for Spot 10 no matches")
    void testDisplayOddsSpot10Match0() {
        assertEquals(5, displayOdds.getWinnings(10, 0));
    }
    
    @Test
    @DisplayName("Test DisplayOdds returns correct winnings for Spot 10 jackpot")
    void testDisplayOddsSpot10Match10() {
        assertEquals(100000, displayOdds.getWinnings(10, 10));
    }
    
    @Test
    @DisplayName("Test DisplayOdds returns 0 for invalid spot count")
    void testDisplayOddsInvalidSpot() {
        assertEquals(0, displayOdds.getWinnings(5, 3));
    }
    
    @Test
    @DisplayName("Test DisplayOdds returns 0 for no winning matches")
    void testDisplayOddsNoWinning() {
        assertEquals(0, displayOdds.getWinnings(4, 1));
    }
    
    //betcard tests
    @Test
    @DisplayName("Test BetCard spot count is set correctly")
    void testBetCardSpotCount() {
        betCard = new BetCard(4);
        assertEquals(4, betCard.getSpotCount());
    }
    
    @Test
    @DisplayName("Test BetCard can select valid number")
    void testBetCardSelectNumber() {
        betCard = new BetCard(4);
        assertTrue(betCard.select(15));
    }
    
    @Test
    @DisplayName("Test BetCard cannot select more than spot count")
    void testBetCardSelectTooMany() {
        betCard = new BetCard(1);
        betCard.select(5);
        assertFalse(betCard.select(10));
    }
    
    @Test
    @DisplayName("Test BetCard can unselect number")
    void testBetCardUnselectNumber() {
        betCard = new BetCard(4);
        betCard.select(20);
        assertTrue(betCard.unselect(20));
    }
    
    @Test
    @DisplayName("Test BetCard clear removes all selections")
    void testBetCardClear() {
        betCard = new BetCard(4);
        betCard.select(1);
        betCard.select(2);
        betCard.clear();
        assertEquals(0, betCard.getSelected().size());
    }
    
    @Test
    @DisplayName("Test BetCard is complete when all spots filled")
    void testBetCardComplete() {
        betCard = new BetCard(1);
        betCard.select(10);
        assertTrue(betCard.complete());
    }
    
    @Test
    @DisplayName("Test BetCard is not complete when spots not filled")
    void testBetCardNotComplete() {
        betCard = new BetCard(4);
        betCard.select(10);
        assertFalse(betCard.complete());
    }
    
    @Test
    @DisplayName("Test BetCard auto picking selects correct number of spots")
    void testBetCardAutoPicking() {
        betCard = new BetCard(8);
        Random rng = new Random();
        betCard.autoPicking(rng);
        assertEquals(8, betCard.getSelected().size());
    }
    
    //drawnnumbers tests
    @Test
    @DisplayName("Test DrawnNumbers generates exactly 20 numbers")
    void testDrawnNumbersCount() {
        Random rng = new Random();
        DrawnNumbers drawn = DrawnNumbers.genRandNums(rng);
        assertEquals(20, drawn.getDrawNums().size());
    }
    
    @Test
    @DisplayName("Test DrawnNumbers are all unique")
    void testDrawnNumbersUnique() {
        Random rng = new Random();
        DrawnNumbers drawn = DrawnNumbers.genRandNums(rng);
        Set<Integer> numbers = drawn.getDrawNums();
        assertEquals(20, numbers.size());
    }
    
    @Test
    @DisplayName("Test DrawnNumbers are in valid range 1-80")
    void testDrawnNumbersRange() {
        Random rng = new Random();
        DrawnNumbers drawn = DrawnNumbers.genRandNums(rng);
        for (int num : drawn.getDrawNums()) {
            assertTrue(num >= 1 && num <= 80);
        }
    }
    
    //player tests
    @Test
    @DisplayName("Test Player initial total winnings is zero")
    void testPlayerInitialWinnings() {
        assertEquals(0, player.getTotalWinnings());
    }
    
    @Test
    @DisplayName("Test Player can add result and update winnings")
    void testPlayerAddResult() {
        betCard = new BetCard(1);
        betCard.select(5);
        GamePlay gamePlay = new GamePlay(displayOdds, winnings, 1);
        RoundResult result = gamePlay.nextDraw(betCard);
        player.addResult(result);
        assertTrue(player.getTotalWinnings() >= 0);
    }
    
    //winnings tests
    @Test
    @DisplayName("Test Winnings calculation uses DisplayOdds correctly")
    void testWinningsCalculation() {
        int amount = winnings.addWinnings(1, 1, displayOdds);
        assertEquals(2, amount);
    }
    
    @Test
    @DisplayName("Test Winnings returns 0 for no matches on Spot 4")
    void testWinningsNoMatch() {
        int amount = winnings.addWinnings(4, 0, displayOdds);
        assertEquals(0, amount);
    }
    
    //gameplay tests
    @Test
    @DisplayName("Test GamePlay has more draws initially")
    void testGamePlayHasMoreDraws() {
        GamePlay gamePlay = new GamePlay(displayOdds, winnings, 4);
        assertTrue(gamePlay.hasMoreDraws());
    }
    
    @Test
    @DisplayName("Test GamePlay draws played increments")
    void testGamePlayDrawsPlayed() {
        betCard = new BetCard(1);
        betCard.select(10);
        GamePlay gamePlay = new GamePlay(displayOdds, winnings, 2);
        gamePlay.nextDraw(betCard);
        assertEquals(1, gamePlay.getDrawsPlayed());
    }
}