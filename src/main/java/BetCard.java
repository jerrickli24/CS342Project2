import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Represents a player's bet card with their selected numbers
 */
public class BetCard {
    private int spotCount;
    private Set<Integer> selectedNumbers;
    
    public BetCard(int spotCount) {
        // TODO: Validate spotCount is 1, 4, 8, or 10
        this.spotCount = spotCount;
        this.selectedNumbers = new HashSet<>();
    }
    
    /**
     * Select a number (1-80)
     */
    public boolean select(int n) {
        // TODO: Implement - validate and add number
        return false;
    }
    
    /**
     * Unselect a number
     */
    public boolean unselect(int n) {
        // TODO: Implement
        return false;
    }
    
    /**
     * Clear all selected numbers
     */
    public void clear() {
        // TODO: Implement
    }
    
    /**
     * Check if the bet card is complete
     */
    public boolean complete() {
        // TODO: Implement
        return false;
    }
    
    /**
     * Get the selected numbers
     */
    public Set<Integer> getSelected() {
        // TODO: Implement
        return null;
    }
    
    /**
     * Get the spot count
     */
    public int getSpotCount() {
        return spotCount;
    }
    
    /**
     * Automatically pick random numbers
     */
    public void autoPicking(Random rng) {
        // TODO: Implement - randomly select spotCount numbers
    }
}