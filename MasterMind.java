import java.util.*;

// Note: Version 1 is implemented
public class MasterMind {
    private static final int NUMBER_OF_COLORS = 4;
    private static final String[] COLORS = {"Red", "Blue", "Green", "White"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Guess the sequence of " + NUMBER_OF_COLORS + " colors.");

        String[] answer = generateAnswer();
        String[] guess = new String[NUMBER_OF_COLORS];
        int attempts = 0;

        while (true) {
            attempts++;
            System.out.println("Enter your guess (" + NUMBER_OF_COLORS + " colors separated by spaces, case-sensitive):");
            for (int i = 0; i < NUMBER_OF_COLORS; i++) {
                guess[i] = scanner.next();
            }
            int[] result = resultOfGuess(answer, guess);
            System.out.println("Result: " + result[0] + " correct position, " + result[1] + " correct color but wrong position.");
            if (result[0] == NUMBER_OF_COLORS) {
                System.out.println("Congratulations! You've guessed the correct answer in " + attempts + " attempts.");
                break;
            }
        }
    }

    private static String[] generateAnswer() {
        Random random = new Random();
        String[] sequence = new String[NUMBER_OF_COLORS];
        for (int i = 0; i < NUMBER_OF_COLORS; i++) {
            sequence[i] = COLORS[random.nextInt(COLORS.length)];
        }
        return sequence;
    }

    private static int[] resultOfGuess(String[] secretSequence, String[] guess) {
        int correctPosition = 0;
        int correctColor = 0;
        boolean[] visited = new boolean[NUMBER_OF_COLORS];

        // correct positions
        for (int i = 0; i < NUMBER_OF_COLORS; i++) {
            if (guess[i].equals(secretSequence[i])) {
                correctPosition++;
                visited[i] = true;
            }
        }

        // correct colors but wrong positions
        for (int i = 0; i < NUMBER_OF_COLORS; i++) {
            if (!visited[i]) {
                for (int j = 0; j < NUMBER_OF_COLORS; j++) {
                    if (!visited[j] && guess[i].equals(secretSequence[j])) {
                        correctColor++;
                        visited[j] = true;
                        break;
                    }
                }
            }
        }

        return new int[]{correctPosition, correctColor};
    }
}



/*
2. Estimating the complexity of the move search in different stages of the game:
The complexity of generating the answer is O(NUMBER_OF_COLORS), where NUMBER_OF_COLORS
is the length of the sequence -- in our case, this is O(4) since NUMBER_OF_COLORS is 4, but
this can be changed if the NUMBER_OF_COLORS is also changed.
This is because we need to iterate through each position in the sequence and select a
random color for that position.
Finding the result of a guess involves comparing each color in the guess to the corresponding position
in the answer. This is done twice -- once to find correct positions and once to find
correct colors in incorrect positions. Therefore, the complexity of finding the result of a guess is
O(NUMBER_OF_COLORS^2) -- in our case, this is O(4^2) since NUMBER_OF_COLORS is 4, but
this can be changed if the NUMBER_OF_COLORS is also changed.

3. Suggesting a system of qubits that describes the game, and define the game states and state
vectors:
Game states: Each qubit can represent a color choice for a particular position in the sequence.
Therefore, the state of the game can be represented by a superposition of all possible color
combinations for the sequence.
State vectors: The state vector will help us in keeping track of the chances of each color
being in each position of the sequence.

4. Design quantum gates that implement the operations of the sequential classical algorithm:
Initial step: Quantum gates help us get all the color choices ready at once by putting the
qubits into a superposition representing all possible color combinations.
Finding the result of a guess: Quantum gates need to be designed to compare the guessed
sequence with the answer and update the state vector accordingly to show the
correct position and matches.

5. Apply the designed gates to superposition states aiming at parallelization of the move
search:
Using quantum magic to get all the possible guesses ready simultaneously.
It's like having many versions of yourself trying different guesses at the same time.
Quantum gates help us compare all these guesses against the answer at once.
It's like looking at all the possibilities at the same time.
After checking, some guesses will be more likely to be correct than others.
We use quantum tricks to find out which one is the most promising.
This way, by doing everything together, quantum computers can find the right answer faster than
classical computers.

 */
