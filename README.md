# Team-F-Final-Project
Final project for CS 3330: 


## Description
A 2 Player Game Pack with the games Battleship, Mancala, MasterMind, and Connect4, all implemented with a GUI.

## Dependencies
Java 21 and its standard library. For testing: JUnit5.



### Alon: I implemented the game Mastermind the rules are as follows:
1. A secret code will be generated for the player to find out.
2. The player gets to choose how long they would like the code to be and how many attempts for themselves to correctly guess the code.

Mastermind's main methods:

public void startGame() which initiates and displays the game frame, including all components.

private void submitGuess(ActionEvent e) which handles the guess submission logic, determining if the guess is correct and managing game progression.

Public void resetGame() which resets both the game and logic UI components.

Private getUserInput which prompts the user to input the game settings for code length and maximum number of tries.

Private void initializeButtons(int maxTries, int codeLength, ActionListener colorListener, ActionListener submitListener) which initializes the buttons on the game board based off of user input.

WARNING WHEN TESTING: GameSettings always propts user for input and input does not effect outcome of tets, but all input fields need to above 0 for all tests to run. (A lot of pannels pop up, put in numbers greater than 0)


### Hipolito: I implemented the game Mancala. the rules are as follows:
1. The board consists of two rows of six holes (or pits) each.
2. Four stones go to each pit at the beginning of a game.
3. Each player has a “store” to the right side of the Mancala board
4. The game begins with one player picking up all the pieces in any one of the holes on their side.
5. Moving counter-clockwise, the player deposits one stone in each hole until the stones run out.
6. If you run into your own store, deposit one piece in it. If you run into your opponent’s store, skip it.
7. If the last piece you drop is in your own store, you get a free turn.
8. If the last piece you drop is in an empty hole on your side, you capture that piece and any pieces in the hole directly opposite.
9. Return captured pieces to your store.
10. The game ends when all six spaces on one side of the Mancala board are empty. Remaining pieces are put into that player's store.
11. The player with the most stones in their store at the end of the game wins.


### Khuram: implemented the game Connect4 and the mainMenu to select a game.
Features:
1.Users can select from the four games available to play.
2.Users can toggle a timer and choose the length of said timer for turns in a game.
3.Users can choose their color for their discs.
4.Users can choose the size of the board they would like to play one.
5.Users can play a game of Connect4 (get four in a row).
