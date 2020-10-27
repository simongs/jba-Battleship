package battleship;

import java.util.Scanner;

public class GameField {

	Cell[][] field = new Cell[GameConstant.FIELD_SIZE][GameConstant.FIELD_SIZE];
	int remainMarkCount;

	public void initialize() {
		for (int i = 0; i < GameConstant.FIELD_SIZE; i++) {
			for (int j = 0; j < GameConstant.FIELD_SIZE; j++) {
				field[i][j] = new Cell(i, j, CellState.FOG);
			}
		}
		remainMarkCount = GameConstant.TOTAL_CELL_COUNT_OF_SHIPS;
	}

	public void printField(boolean hideFogState) {
		System.out.println("  1 2 3 4 5 6 7 8 9 10");

		for (int i = 0; i < GameConstant.FIELD_SIZE; i++) {
			System.out.print(GameConstant.ROW_CHAR[i] + " ");
			for (int j = 0; j < GameConstant.FIELD_SIZE; j++) {

				Cell cell = field[i][j];
				if (hideFogState) {
					if (cell.cellState == CellState.MARK) {
						System.out.print(CellState.FOG.state + " ");
					} else {
						System.out.print(cell.cellState.state + " ");
					}
				} else {
					System.out.print(cell.cellState.state + " ");
				}
			}
			System.out.println("");
		}
	}

	public void settingField(String playerName) {
		System.out.println(String.format("%s, place your ships on the game field", playerName));
		printField(false);

		for (ShipCategory shipCategory : ShipCategory.values()) {
			placeShipToGameField(shipCategory);
		}

		System.out.println("Press Enter and pass the move to another player");
		new Scanner(System.in).nextLine();
	}

	private void placeShipToGameField(ShipCategory shipCategory) {
		while (true) {
			System.out.println(String.format("Enter the coordinates of the %s (%d cells):", shipCategory.name, shipCategory.size));

			PairCellUserInput pairCellUserInput = getPositionByUserInput();
			
			boolean isValid = checkValid(pairCellUserInput, shipCategory);
			if (!isValid) {
				continue;
			}

			pairCellUserInput.markingToField(field, shipCategory);
			break;
		}

		printField(false);
	}

	private boolean checkValid(PairCellUserInput pairCellUserInput, ShipCategory shipCategory) {
		// is check already exists
		if (pairCellUserInput.firstCell.cellState == CellState.MARK
			|| pairCellUserInput.secondCell.cellState == CellState.MARK) {
			System.out.println("Error! You placed it too close to another one. Try again:");
			return false;
		}

		// check sequencial
		if (!pairCellUserInput.checkSequence()) {
			System.out.println("Error! Wrong ship location! Try again:");
			return false;
		}

		// check length
		if (!pairCellUserInput.checkValidSize(shipCategory)) {
			System.out.println(String.format("Error! Wrong length of the %s! Try again:", shipCategory.name));
			return false;
		}

		// check first position
		if (pairCellUserInput.checkCloseToAnotherShip(field, pairCellUserInput.firstCell)) {
			System.out.println("Error! You placed it too close to another one. Try again:");
			return false;
		}

		// check second position
		if (pairCellUserInput.checkCloseToAnotherShip(field, pairCellUserInput.secondCell)) {
			System.out.println("Error! You placed it too close to another one. Try again:");
			return false;
		}

		return true;
	}

	private PairCellUserInput getPositionByUserInput() {
		Scanner scanner = new Scanner(System.in);
		String[] command = scanner.nextLine().split(" ");
		return new PairCellUserInput(command[0], command[1]);
	}

}
