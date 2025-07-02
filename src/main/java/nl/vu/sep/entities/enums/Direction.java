package nl.vu.sep.entities.enums;

import nl.vu.sep.entities.Coordinate;

public enum Direction {

    NORTH(0, -1),
    SOUTH(0, 1),
    EAST(1, 0),
    WEST(-1, 0);

    final Coordinate offset;

    Direction(int x, int y) {
        this.offset = new Coordinate(x, y);
    }

    public Coordinate getOffset() {
        return offset;
    }

    public static Direction getDirectionFromLetter(char c) {
        return switch (c) {
            case 'N' -> Direction.NORTH;
            case 'S' -> Direction.SOUTH;
            case 'E' -> Direction.EAST;
            case 'W' -> Direction.WEST;
            default -> throw new IllegalArgumentException(String.format("Unknown direction %c! Available: N, S, E, W.", c));
        };
    }

}
