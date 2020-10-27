package battleship;

public class CellUtils {
	public static boolean isExceedLimit(Cell cell) {
		if (cell.column < 0 || cell.row < 0) {
			return true;
		}

		if (cell.column >= GameConstant.FIELD_SIZE
			|| cell.row >= GameConstant.FIELD_SIZE) {
			return true;
		}

		return false;
	}

	public static Cell changeCellState(Cell[][] field, Cell cell) {
		Cell current = field[cell.row][cell.column];

		if (current.cellState == CellState.MARK) {
			current.cellState = CellState.HIT;
		} else if (current.cellState == CellState.FOG) {
			current.cellState = CellState.MISS;
		}

		return current;
	}

	public static boolean isAlreadyCheckedStatus(Cell[][] field, Cell cell) {
		Cell current = field[cell.row][cell.column];

		if (current.cellState == CellState.HIT ||
		 current.cellState == CellState.MISS) {
			return true;
		}

		return false;
	}
}
