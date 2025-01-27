# Bulls and Cows Game

This is a simple implementation of the **Bulls and Cows** game written in Kotlin. The game generates a secret code consisting of alphanumeric characters, and the player tries to guess the code based on feedback given after each attempt.

In this game:
- **Bulls** refer to characters that are both correct in value and position.
- **Cows** refer to characters that are correct in value but incorrect in position.

The player keeps guessing until the secret code is fully guessed.

---

## Features

- **Code Generation**: The game allows the user to specify the length of the secret code and the number of possible symbols (0-9, a-z).
- **Feedback on Guesses**: After each guess, the player gets feedback on how many bulls and cows are in the guess.
- **Validation**: The game ensures valid input for code length and symbol quantity.

---

## How to Play

1. When you run the game, you will be prompted to input the length of the secret code (between 1 and 36).
2. Next, you'll be asked to specify the number of possible symbols in the secret code. This can be any integer.
3. The secret code will be generated, and you can start guessing it.
4. After each guess, you will receive feedback in the form of **bulls** and **cows**.
   - **Bull** means the character is correct and in the right position.
   - **Cow** means the character is correct but in the wrong position.
5. The game continues until you guess the secret code correctly.

### Classes:

- **CodeGenerator**:
    - Generates the secret code based on a user-defined length and possible symbols.
    - Ensures the length is valid (1 to 36).
  
- **AttempValidator**:
    - Validates each guess by counting the bulls and cows.
    - Checks if the user has won the game.

- **Game**:
    - Controls the flow of the game.
    - Manages user input and displays feedback after each attempt.
    - Ends the game when the user correctly guesses the code.

### Functions:

- `generateSecretCode()`: Generates a random secret code.
- `grade()`: Evaluates the guess, counting bulls and cows.
- `checkBulls()`: Checks how many bulls (correct characters in the correct position) are in the guess.
- `checkCows()`: Checks how many cows (correct characters but in the wrong position) are in the guess.
- `checkWin()`: Checks if the guess is correct.

---

## Running the Game

To run this game:

1. Clone the repository or download the Kotlin file.
2. Run the `main()` function to start playing.
