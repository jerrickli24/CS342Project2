# CS 342 Project 2 - Keno Game
# Oct. 26, 2025
A JavaFX implementation of the popular Keno lottery game.

## Team Members
- Jerrick Li (jerrickli24)
- Neha Kamat (nehakamat-nk)

## Project Description

This is a Keno lottery game simulator built with JavaFX for CS 342 Fall 2025. Players can select numbers (1, 4, 8, or 10 spots), play multiple drawings, and win money based on how many numbers they match. The game uses the North Carolina state lottery payout structure.

## How to Run

### Requirements
- Java 11 or higher
- Maven 3.6+
- JavaFX 19 (Maven will download this automatically)

### Running the Game
```bash
# Compile and run
mvn clean javafx:run

# Or run tests
mvn test
```

### In VS Code
1. Open the project folder
2. Open `src/main/java/KenoMain.java`
3. Click the "Run" button above the `main` method

## Features

### Welcome Screen
- Menu bar with options for:
  - Display Rules
  - Display Odds of Winning
  - Exit Game
- Start button to begin playing

### Game Screen
- 8x10 grid showing numbers 1-80
- Select 1, 4, 8, or 10 spots to play
- Choose 1-4 consecutive drawings
- Auto-pick option for random number selection
- Animated drawing of 20 random numbers
- Visual feedback showing:
  - Selected numbers (before drawing)
  - Matched numbers (green)
  - Drawn numbers (gray)
- Real-time results and winnings display
- Total winnings tracker
- "New Look" theme option

### Winnings (North Carolina Lottery)
- **1 Spot**: Match 1 = $2
- **4 Spots**: Match 2=$1, Match 3=$5, Match 4=$75
- **8 Spots**: Match 4=$2, Match 5=$12, Match 6=$50, Match 7=$750, Match 8=$10,000
- **10 Spots**: Match 0=$5, Match 5=$2, Match 6=$15, Match 7=$40, Match 8=$450, Match 9=$4,250, Match 10=$100,000

## Project Structure
```
jli326nkama4Project2/
├── pom.xml                      # Maven configuration
├── README.md                    # This file
└── src/
    ├── main/
    │   └── java/
    │       ├── KenoMain.java           # Main entry point
    │       ├── SceneSwitch.java        # Manages scene transitions
    │       ├── HomeMain.java           # Welcome screen
    │       ├── GameMain.java           # Game play screen
    │       ├── BetCard.java            # Player's bet card
    │       ├── Player.java             # Tracks winnings
    │       ├── GamePlay.java           # Game logic controller
    │       ├── DisplayOdds.java        # NC lottery odds table
    │       ├── Winnings.java           # Calculates payouts
    │       ├── DrawnNumbers.java       # Random number generator
    │       └── RoundResult.java        # Stores round results
    └── test/
        └── java/
            └── KenoGameTest.java       # JUnit 5 tests (25+ required)
```

## Requirements Checklist

- [x] Maven project with artifactId: Project2Fall2025
- [x] Two separate JavaFX Scenes (Welcome + Game)
- [x] Menu bar with all required options
- [x] 8x10 GridPane for bet card
- [x] Spot selection (1, 4, 8, 10)
- [x] Drawing selection (1-4)
- [x] Auto-pick functionality
- [x] Animated drawing (20 random numbers)
- [x] Visual feedback for matches
- [x] NC lottery winnings table
- [x] Input validation
- [ ] 25+ JUnit 5 tests (TODO)
- [x] No Scene Builder, FXML, or CSS files
- [x] Only JavaFX components

## How to Play

1. Click "Start Playing" on the welcome screen
2. Select how many spots to play (1, 4, 8, or 10 numbers)
3. Select how many drawings (1-4)
4. Click numbers on the grid to select them (or use Auto Pick)
5. Click "Start Drawing" to begin
6. Watch as 20 random numbers are drawn
7. See your matches highlighted in green
8. View your winnings for that round
9. Continue to next drawing or start a new game

## Demo

[▶️ Watch the Keno game demo](assets/KenoGameDemo.mov)

## Technologies Used

- Java 11
- JavaFX 19
- Maven
- JUnit 5

## Known Issues / TODO

- [ ] Need to complete unit tests (currently have X out of 25+ required)
- [ ] Sound effects could be added
- [ ] Additional themes beyond "New Look"
- [ ] Save game history feature

## Development Notes

### Class Responsibilities

**Model Classes (Game Logic):**
- `BetCard` - Manages player's number selections
- `Player` - Tracks total winnings and history
- `GamePlay` - Controls game flow and drawing logic
- `DisplayOdds` - Stores NC lottery payout table
- `Winnings` - Calculates payouts based on matches
- `DrawnNumbers` - Generates 20 random unique numbers
- `RoundResult` - Data storage for each drawing result

**View Classes (UI):**
- `KenoMain` - Application entry point
- `SceneSwitch` - Manages scene transitions
- `HomeMain` - Welcome screen with menu
- `GameMain` - Main game interface with GridPane

### Design Decisions

- Used HashSet for number storage to prevent duplicates
- Separated game logic from UI for easier testing
- Implemented defensive copying in getters to protect data
- Used animations for better user experience
- GridPane chosen for bet card as required by project spec

## Testing

Run all tests:
```bash
mvn test
```

Expected: At least 25 tests covering:
- BetCard selection logic
- DrawnNumbers generation
- DisplayOdds payout calculations
- Player winnings tracking
- GamePlay drawing mechanics
- Input validation

## Credits

Based on CS 342 Project 2 specifications (Fall 2025)
North Carolina Keno rules: https://nclottery.com/KenoHow

## License

Educational project for CS 342 at UIC