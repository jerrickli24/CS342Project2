import java.util.HashSet;
import java.util.Random;
import java.util.Set;

//Jerrick Li, jli326, UIN: 653147894

//holds the 20 nums drawn each round (winning nums)
//also, has helper to gen a new set using randoms
public class DrawnNumbers {
    private Set<Integer> numbers;

    //constructor, store given numbers (assumes valid set)
    public DrawnNumbers(Set<Integer> numbers) {
        this.numbers = new HashSet<>(numbers);
    }

    //getter for the drawn numbers
    //returns copy to protect the class
    public Set<Integer> getDrawNums() {
        return new HashSet<>(numbers);
    }

    //static helper
    //makes a new DrawnNumbers with 20 unique random nums (1 to 80)
    public static DrawnNumbers genRandNums(Random rng) {
        Set<Integer> pickedNums = new HashSet<>();

        while (pickedNums.size() < 20) {
            int n = rng.nextInt(80) + 1; 
            pickedNums.add(n); 
        }
        return new DrawnNumbers(pickedNums);
    }
}
