import java.util.HashMap;
import java.util.Map;

/**
 * Stores the North Carolina Keno odds and winnings table
 */
public class DisplayOdds {
    private Map<Integer, Map<Integer, Integer>> winnings;
    
    public DisplayOdds() {
        winnings = new HashMap<>();
        initializeWinnings();
    }
    
    /**
     * Initialize the NC Keno winnings table
     * Based on https://nclottery.com/KenoHow
     */
    private void initializeWinnings() {
        // TODO: Initialize winnings for Spot 1, 4, 8, 10
        // Spot 1: match 1 = $2
        // Spot 4: match 2=$1, 3=$5, 4=$75
        // Spot 8: match 4=$2, 5=$12, 6=$50, 7=$750, 8=$10,000
        // Spot 10: match 0=$5, 5=$2, 6=$15, 7=$40, 8=$450, 9=$4,250, 10=$100,000
    }
    
    /**
     * Get winnings for a specific spot count and match count
     */
    public int getWinnings(int spotCount, int matchCount) {
        // TODO: Implement
        return 0;
    }
    
    /**
     * Get formatted odds table as string for display
     */
    public String getOddsTable() {
        // TODO: Format and return the odds table
        return "KENO WINNINGS TABLE\n===================\n";
    }
}