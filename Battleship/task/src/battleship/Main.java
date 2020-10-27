package battleship;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	Player player1 = new Player("Player 1", new GameField());
    	Player player2 = new Player("Player 2", new GameField());

		player1.initialize();
		player2.initialize();

		player1.enemy = player2;
		player2.enemy = player1;

		while (true) {
			if (play(player1)) {
				break;
			}

			if (play(player2)) {
				break;
			}
		}

		System.out.println("You sank the last ship. You won. Congratulations!");
    }

	private static boolean play(Player player) {
		player.play();

		if (player.isGameFinished()) {
			return true;
		} else {
			System.out.println("Press Enter and pass the move to another player");
			new Scanner(System.in).nextLine();

			return false;
		}
	}
}
