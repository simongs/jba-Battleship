package battleship;

public class PairCellUserInput {

	Cell firstCell;
	Cell secondCell;

	public PairCellUserInput(String firstPosition, String secondPosition) {
		firstCell = new Cell(firstPosition);
		secondCell = new Cell(secondPosition);
	}

	public boolean checkValidSize(ShipCategory shipCategory) {
		int diff;
		if (firstCell.row == secondCell.row) {
			diff = Math.abs(firstCell.column - secondCell.column) + 1;
		} else {
			diff = Math.abs(firstCell.row - secondCell.row) + 1;
		}

		return shipCategory.size == diff;
	}

	public void markingToField(Cell[][] field, ShipCategory shipCategory) {

		if (firstCell.row == secondCell.row) {
			int min = Math.min(firstCell.column, secondCell.column);
			int max = Math.max(firstCell.column, secondCell.column);

			for (int i = min; i <= max; i++) {
				field[firstCell.row][i].cellState = CellState.MARK;
				field[firstCell.row][i].shipCategory = shipCategory;

			}
		} else {
			int min = Math.min(firstCell.row, secondCell.row);
			int max = Math.max(firstCell.row, secondCell.row);

			for (int i = min; i <= max; i++) {
				field[i][firstCell.column].cellState = CellState.MARK;
				field[i][firstCell.column].shipCategory = shipCategory;
			}
		}

	}

	public boolean checkSequence() {
		if (firstCell.row == secondCell.row) {
			return true;
		} else if (firstCell.column == secondCell.column) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkCloseToAnotherShip(Cell[][] field, Cell cell) {

		for (Direction direction : Direction.values()) {
			int checkHeight = cell.row + direction.row;
			int checkWidth = cell.column + direction.column;

			// check border
			if (checkHeight < 0 || checkWidth < 0  || checkHeight >= GameConstant.FIELD_SIZE || checkWidth >= GameConstant.FIELD_SIZE) {
				continue;
			}

			if (field[checkHeight][checkWidth].cellState == CellState.MARK) {
				return true;
			}
		}

		return false;
	}

	public enum Direction {
		NORTH(-1, 0), SOUTH(1, 0), EAST(0, 1), WEST(0, -1);

		int row;
		int column;

		Direction(int row, int column) {
			this.row = row;
			this.column = column;
		}
	}
}



