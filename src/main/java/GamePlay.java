import java.util.HashSet;
import java.util.Random;
import java.util.Set;

//Jerrick Li, jli326, UIN: 653147894

//manages the main game logic and drawings
public class GamePlay {
    private Random rand;
    private DisplayOdds displayOdds;
    private Winnings winnings;
    private int totalDrawings;
    private int drawsPlayed;

    //gameplay constructor, intializes to 0
    public GamePlay(DisplayOdds displayOdds, Winnings winnings, int totalDrawings) {
        this.displayOdds = displayOdds;
        this.winnings = winnings;
        this.totalDrawings = totalDrawings;
        this.drawsPlayed = 0;

        this.rand = new Random(System.currentTimeMillis());
    }

    //execute next drawing based on the player's BetCard
    //build and return a RoundResult
    public RoundResult nextDraw(BetCard bet) {

        if (!hasMoreDraws()) {
            return null;
        }

        //make 20 rand nums
        DrawnNumbers DN = DrawnNumbers.genRandNums(rand);

        Set<Integer> drawnCopy = DN.getDrawNums(); 
        Set<Integer> betCopy = new HashSet<>(bet.getSelected()); 
        Set<Integer> matched = new HashSet<>();

        for (int n : betCopy) {
            if (drawnCopy.contains(n)) {
                matched.add(n);
            }
        }
        int matchCount = matched.size();

    
        int spotCount = bet.getSpotCount();
        int roundWin = winnings.addWinnings(spotCount, matchCount, displayOdds); //returns how much won per round

        //keep drawing
        drawsPlayed = drawsPlayed + 1;

        //create RoundResult based on above findings
        RoundResult RR = new RoundResult(
                drawsPlayed,              
                drawnCopy,                   
                matched,                     
                matchCount,               
                roundWin                     
        );
        return RR;
    }

    //check if there's more draws to play
    public boolean hasMoreDraws() {
        if (drawsPlayed < totalDrawings) {
            return true;
        } else {
            return false;
        }
    }

    //getter for drawsPlayed
    public int getDrawsPlayed() {
        return drawsPlayed;
    }

    //getter for totaldrawings
    public int getTotalDrawings() {
        return totalDrawings;
    }

    //helper to reset counts
    public void reset(int newTotalDrawings) {
        this.totalDrawings = newTotalDrawings;
        this.drawsPlayed = 0;
    }
}