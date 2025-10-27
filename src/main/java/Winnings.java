
//Jerrick Li, jli326, UIN: 653147894

//calculate winnings based on round, matched nums, and spotcount
public class Winnings {

    //add up winnings baed on spot and match count, and displayOdds
    //returns amount won per round
    public int addWinnings(int spotCount, int matchCount, DisplayOdds displayOdds) {

        if (displayOdds == null) {
            return 0;
        }

        if (spotCount != 1 && spotCount != 4 && spotCount != 8 && spotCount != 10) { //only allowing allowed spots
            return 0;
        }

        if (matchCount < 0) {
            matchCount = 0;
        }

        if (matchCount > spotCount) {
            matchCount = spotCount;
        }

        int amount = displayOdds.getWinnings(spotCount, matchCount);

        if (amount < 0) { //dont allow winnings that aren't won
            amount = 0;
        }

        return amount;
    }
}
