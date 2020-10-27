package battleship;

public enum ShipCategory {
	AIRCRAFT("Aircraft Carrier", 5),
	BATTLESHIP("Battleship", 4),
	SUBMARINE("Submarine", 3),
	CRUISER("Cruiser", 3),
	DESTROYER("Destroyer", 2),
	;

	String name;
	Integer size;

	ShipCategory(String name, Integer size) {
		this.name = name;
		this.size = size;
	}
}
