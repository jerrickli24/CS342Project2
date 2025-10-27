import java.util.Set;
import java.util.HashSet;

//Jerrick Li, jli326, UIN: 653147894

//keeps results of single drawing round
//keeps track of what numbers were drawn, what matched, and how much won
public class RoundResult {
    private int drawPlace;
    private Set<Integer> numbersDrawn;
    private Set<Integer> matchedNumbers;
    private int matchCounter;
    private int roundWinnings;
    
    //constructor, creates new result for drawing
    public RoundResult(int drawPlace, Set<Integer> numbersDrawn, Set<Integer> matchedNumbers, 
                      int matchCounter, int roundWinnings) {
        this.drawPlace = drawPlace;
        
        this.numbersDrawn = new HashSet<>(); //copy (OG won't be changed)
        for (Integer num : numbersDrawn) {
            this.numbersDrawn.add(num);
        }
        
        this.matchedNumbers = new HashSet<>();
        for (Integer num : matchedNumbers) {
            this.matchedNumbers.add(num);
        }
        
        this.matchCounter = matchCounter;
        this.roundWinnings = roundWinnings;
    }
    
    //getter for draw place
    //i.e. 1st draw, 2nd draw, etc.
    public int getDrawPlace() {
        return drawPlace;
    }
    
    //getter for the 20 winning numbers drawn
    public Set<Integer> getNumbersDrawn() {
        Set<Integer> copy = new HashSet<>();
        for (Integer num : numbersDrawn) {
            copy.add(num);
        }
        return copy;
    }
    
    //getter for matched numbers
    public Set<Integer> getMatchedNumbers() {
        Set<Integer> copy = new HashSet<>();
        for (Integer num : matchedNumbers) {
            copy.add(num);
        }
        return copy;
    }
    
    //getter for number of matched numbers
    public int getMatchCounter() {
        return matchCounter;
    }
    
    //getter for the winnings won
    public int getRoundWinnings() {
        return roundWinnings;
    }
    
    //string result of results, like the round, how many nums matched, winnings, etc.
    public String result() {
        String resultString = "Drawing round #" + drawPlace + ": ";
        resultString = resultString + "Matched " + matchCounter + " numbers, ";
        resultString = resultString + "Won $" + roundWinnings;
        return resultString;
    }
}