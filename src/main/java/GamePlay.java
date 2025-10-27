import java.util.Random;

/**
 * Manages the main game logic and drawing process
 */
public class GamePlay {
    private Random rng;
    private DisplayOdds oddsTable;
    private Winnings winnings;
    private int totalDrawings;
    private int drawsPlayed;
    
    public GamePlay(DisplayOdds displayOdds, Winnings winnings, int totalDrawings) {
        // TODO: Initialize
    }
    
    /**
     * Execute the next drawing
     */
    public RoundResult nextDraw(BetCard bet) {
        // TODO: Implement - generate 20 random numbers, check matches, calculate winnings
        return null;
    }
    
    /**
     * Check if there are more drawings to play
     */
    public boolean hasMoreDraws() {
        // TODO: Implement
        return false;
    }
    
    public int getDrawsPlayed() {
        return drawsPlayed;
    }
    
    public int getTotalDrawings() {
        return totalDrawings;
    }
}