package battleship;

import java.util.Arrays;

public class Cell {
	int row; // height
	int column; // width
	CellState cellState;
	ShipCategory shipCategory;

	public Cell(String userInput) {
		char[] charArray = userInput.toCharArray();

		Integer row = GameConstant.ROW_INDEX_MAP.get(charArray[0]);
		if (row == null) {
			throw new InvalidUserInputException("You have to input character [A ~ J]");
		}

		this.row = row;
		column = Integer.parseInt(new String(Arrays.copyOfRange(charArray, 1, charArray.length))) - 1;

		if (CellUtils.isExceedLimit(this)) {
			throw new InvalidUserInputException("Your input has exceeded the boundary value.");
		}
	}

	public Cell(int row, int column, CellState cellState) {
		this.row = row;
		this.column = column;
		this.cellState = cellState;
	}

}

enum CellState {
	FOG('~'), MARK('O'), HIT('X'), MISS('M');

	char state;

	CellState(char state) {
		this.state = state;
	}
}
