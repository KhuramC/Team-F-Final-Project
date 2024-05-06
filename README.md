# Team-F-Final-Project
Final project for CS 3330.


Alon: I implemented the game Mastermind the rules are as follows:
1. A secret code will be generated for the player to find out.
2. The player gets to choose how long they would like the code to be and how many attempts for themselves to correctly guess the code.

Mastermind's main methods:

public void startGame() which initiates and displays the game frame, including all components.

private void submitGuess(ActionEvent e) which handles the guess submission logic, determining if the guess is correct and managing game progression.

Public void resetGame() which resets both the game and logic UI components.

Private getUserInput which prompts the user to input the game settings for code length and maximum number of tries.

Private void initializeButtons(int maxTries, int codeLength, ActionListener colorListener, ActionListener submitListener) which initializes the buttons on the game board based off of user input.
