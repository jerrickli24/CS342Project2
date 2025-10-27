import java.util.HashMap;
import java.util.Map;

//Neha Kamat, nkama4, UIN: 665218217
//Stores the Keno odds and winnings table

public class DisplayOdds {

    private Map<Integer, Map<Integer, Integer>> winnings;
    
    public DisplayOdds() {

        winnings = new HashMap<>();
        initializeWinnings();

    }
    
    
    //initialize NC Keno winnings table (pulled from the website from project guidelines)
    private void initializeWinnings() {
        Map<Integer, Integer> spot1 = new HashMap<>();
        spot1.put(1, 2);
        winnings.put(1, spot1);
        
        Map<Integer, Integer> spot4 = new HashMap<>();
        spot4.put(2, 1);
        spot4.put(3, 5);
        spot4.put(4, 75);
        winnings.put(4, spot4);
        
        Map<Integer, Integer> spot8 = new HashMap<>();
        spot8.put(4, 2);
        spot8.put(5, 12);
        spot8.put(6, 50);
        spot8.put(7, 750);
        spot8.put(8, 10000);
        winnings.put(8, spot8);
        
        Map<Integer, Integer> spot10 = new HashMap<>();
        spot10.put(0, 5);
        spot10.put(5, 2);
        spot10.put(6, 15);
        spot10.put(7, 40);
        spot10.put(8, 450);
        spot10.put(9, 4250);
        spot10.put(10, 100000);
        winnings.put(10, spot10);
    }
    
    //get winnings for specific spot count + match count
    public int getWinnings(int spotCount, int matchCount) {
        if (!winnings.containsKey(spotCount)) {
            return 0;
        }
        Map<Integer, Integer> spotWinnings = winnings.get(spotCount);
        return spotWinnings.getOrDefault(matchCount, 0);
    }
    
    //get formatted odds table as string for display
    public String getOddsTable() {
        StringBuilder table = new StringBuilder();
        table.append("KENO WINNINGS TABLE\n");
        table.append("===================\n\n");
        
        table.append("SPOT 1:\n");
        table.append("  Match 1: $2\n\n");
        
        table.append("SPOT 4:\n");
        table.append("  Match 2: $1\n");
        table.append("  Match 3: $5\n");
        table.append("  Match 4: $75\n\n");
        
        table.append("SPOT 8:\n");
        table.append("  Match 4: $2\n");
        table.append("  Match 5: $12\n");
        table.append("  Match 6: $50\n");
        table.append("  Match 7: $750\n");
        table.append("  Match 8: $10,000\n\n");
        
        table.append("SPOT 10:\n");
        table.append("  Match 0: $5\n");
        table.append("  Match 5: $2\n");
        table.append("  Match 6: $15\n");
        table.append("  Match 7: $40\n");
        table.append("  Match 8: $450\n");
        table.append("  Match 9: $4,250\n");
        table.append("  Match 10: $100,000\n");
        
        return table.toString();
    }
}