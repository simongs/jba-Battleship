package battleship;

import java.util.HashMap;
import java.util.Map;

public class GameConstant {
	public final static Integer FIELD_SIZE = 10;
	public final static Integer TOTAL_CELL_COUNT_OF_SHIPS = 17; // 5 + 4 + 3 + 3 + 2

	public static char[] ROW_CHAR = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
	public static Map<Character, Integer> ROW_INDEX_MAP = new HashMap<>();

	static {
		for (int i = 0; i < FIELD_SIZE; i++) {
			ROW_INDEX_MAP.put(ROW_CHAR[i], i);
		}
	}
}
