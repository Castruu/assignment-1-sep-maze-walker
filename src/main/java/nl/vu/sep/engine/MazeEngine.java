package nl.vu.sep.engine;

import nl.vu.sep.entities.Coordinate;
import nl.vu.sep.entities.Maze;
import nl.vu.sep.entities.enums.CellType;
import nl.vu.sep.entities.enums.Direction;

public class MazeEngine {

    private final Maze maze;
    private Coordinate playerCoordinate;

    public MazeEngine(long seed, int size) {
        this(seed, size, new Coordinate(1, 1));
    }

    public MazeEngine(long seed, int size, Coordinate initialPlayerCoordinate) {
        MazeGenerator generator = new MazeGenerator(seed);
        this.maze = generator.generateMaze(size);
        if(maze.isOutOfBounds(initialPlayerCoordinate)) {
            throw new IllegalArgumentException("Player coordinate out of bounds");
        }
        this.playerCoordinate = initialPlayerCoordinate;
        maze.setCellType(initialPlayerCoordinate, CellType.PLAYER);
    }

    public void movePlayer(Direction direction) {
        Coordinate dest = maze.calculateCellCoordinateFromDirection(playerCoordinate, direction);
        if(maze.isOutOfBounds(dest)) {
            throw new IllegalArgumentException("This coordination is out of bounds!");
        }

        Coordinate inBetweenDest = maze.calculateWallCoordinateFromDirection(playerCoordinate, direction);
        if(maze.getCell(inBetweenDest).type() == CellType.WALL) {
            throw new IllegalArgumentException("There is a wall there!");
        }

        maze.setCellType(playerCoordinate, CellType.EMPTY);
        maze.setCellType(dest, CellType.PLAYER);
        playerCoordinate = dest;
    }

    public boolean isPlayerInEndOfMaze() {
        return playerCoordinate.x() == maze.getSize() - 2 && playerCoordinate.y() == maze.getSize() - 2;
    }

    public Maze getMaze() {
        return maze;
    }
}
