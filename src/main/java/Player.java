import java.util.ArrayList;
import java.util.List;

//Jerrick Li, jli326, UIN: 653147894

//tracks player winnings and history
public class Player {
    private int totalWinnings;
    private List<RoundResult> pastWinnings;

    public Player() {
        //base 0
        totalWinnings = 0;
        pastWinnings = new ArrayList<>();
    }

    //adds round result and updates total winnings
    public void addResult(RoundResult result) {
        if (result == null) {
            return;
        }
    
        int won = result.getRoundWinnings(); 
        if (won < 0) { //so theres not negative winnings
            won = 0; 
        }
        totalWinnings = totalWinnings + won;

        pastWinnings.add(result);
    }

    //getter for total winnings
    public int getTotalWinnings() {
        return totalWinnings;
    }

    //getter for past winnings
    public List<RoundResult> getPastWinnings() {
        return new ArrayList<>(pastWinnings);
    }

    //clears winnings and history
    public void clear() {
        totalWinnings = 0;
        pastWinnings.clear();
    }
}
