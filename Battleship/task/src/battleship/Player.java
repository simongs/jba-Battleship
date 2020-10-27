package battleship;

import java.util.Arrays;
import java.util.Scanner;

public class Player {

	String playerName;
	GameField gameField;
	Player enemy;

	public Player(String playerName, GameField gameField) {
		this.playerName = playerName;
		this.gameField = gameField;
	}

	public void initialize() {
		gameField.initialize();
		gameField.settingField(playerName);
	}

	public void play() {
		print();

		System.out.println(playerName + ", it's your turn:");

		while (true) {

			Scanner scanner = new Scanner(System.in);
			Cell cell;
			try {
				cell = new Cell(scanner.nextLine());

				if (CellUtils.isAlreadyCheckedStatus(enemy.gameField.field, cell)) {
					break;
				}

				final Cell changedCell = CellUtils.changeCellState(enemy.gameField.field, cell);

				if (changedCell.cellState == CellState.HIT) {
					Long totalHitCountByShip = Arrays.stream(enemy.gameField.field)
							.flatMap(cells -> Arrays.stream(cells))
							.filter(i -> i.shipCategory == changedCell.shipCategory)
							.filter(i -> i.cellState == CellState.HIT)
							.count();

					if (totalHitCountByShip.intValue() == changedCell.shipCategory.size) {
						System.out.println("You sank a ship!"); // totally
					} else {
						System.out.println("You hit a ship!"); // partially
					}
				} else if (changedCell.cellState == CellState.MISS) {
					System.out.println("You missed!");
				}

				if (changedCell.cellState == CellState.HIT) {
					enemy.gameField.remainMarkCount--;
				}

				break;
			} catch (InvalidUserInputException e) {
				System.out.println("Error! You entered the wrong coordinates! Try again:");
				continue;
			}
		}
	}

	void print() {
		enemy.gameField.printField(true);
		System.out.println("-----------------------");
		this.gameField.printField(false);
	}

	public boolean isGameFinished() {
		return enemy.gameField.remainMarkCount == 0;
	}
}
