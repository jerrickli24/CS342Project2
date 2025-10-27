import java.util.HashSet;
import java.util.Random;
import java.util.Set;

//Jerrick Li, jli326, UIN: 653147894

//represents player's bet card w/ selected #s
//handles player picking #s for keno game

public class BetCard {
    private int spotCount;
    private Set<Integer> selectedNumbers;
    
    //constructor, makes new betcard
    public BetCard(int spotCount) {
        if (spotCount != 1 && spotCount != 4 && spotCount != 8 && spotCount != 10) { //check for valid spotcount
            throw new IllegalArgumentException("Spot count must be 1, 4, 8, or 10");
        }
        this.spotCount = spotCount;
        this.selectedNumbers = new HashSet<>();
    }
    
    //player selects valid num, 1 to 80
    //return: true if selected, false if can't be selected
    public boolean select(int n) {
        if (n < 1 || n > 80) {
            return false;
        }
        
        if (selectedNumbers.contains(n)) {
            return false;
        }
    
        if (selectedNumbers.size() >= spotCount) {
            return false;
        }

        selectedNumbers.add(n);
        return true;
    }
    
    //deselect number
    //return: true if removed, false if not there 
    public boolean unselect(int n) {
        // Check if the number is in the set
        if (selectedNumbers.contains(n)) {
            selectedNumbers.remove(n);
            return true;
        }
        return false;
    }
    
    //clear all selected numbers
    public void clear() {
        selectedNumbers.clear();
    }
    
    //checks if betcard is filled 
    public boolean complete() {
        if (selectedNumbers.size() == spotCount) {
            return true;
        } else {
            return false;
        }
    }
    
    //get the selected numbers
    public Set<Integer> getSelected() {
        Set<Integer> copy = new HashSet<>(); //creating a new hashset so the original one can't be changed
        for (Integer num : selectedNumbers) {
            copy.add(num);
        }
        return copy;
    }
    
    //gets spotcount
    public int getSpotCount() {
        return spotCount;
    }
    
    //auto picks random numbers for the player
    public void autoPicking(Random rng) {
        clear();
        
        while (selectedNumbers.size() < spotCount) {
            int randomNum = rng.nextInt(80) + 1;
            selectedNumbers.add(randomNum);
        }
    }
}