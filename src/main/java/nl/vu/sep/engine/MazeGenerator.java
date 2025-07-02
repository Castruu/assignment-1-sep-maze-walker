package nl.vu.sep.engine;

import nl.vu.sep.entities.Coordinate;
import nl.vu.sep.entities.Maze;
import nl.vu.sep.entities.enums.CellType;
import nl.vu.sep.entities.enums.Direction;

import java.util.Random;

public class MazeGenerator {
    private final static int SHUFFLING_CONSTANT = 50;

    private final Random random;


    public MazeGenerator(long seed) {
        random = new Random(seed);
    }

    public Maze generateMaze(int size) {
        Maze maze = new Maze(size);

        maze.markVisited(new Coordinate(1, 1));
        makePath(maze, new Coordinate(1, 1),  new Coordinate(maze.getSize() - 2, maze.getSize() - 2));


        return maze;
    }

    private void makePath(Maze maze, Coordinate from, Coordinate to) {
        if(from.equals(to)) {
            return;
        }

        for(Direction direction : sortDirections()) {
            Coordinate nextFrom = maze.calculateCellCoordinateFromDirection(from, direction);
            if(maze.isOutOfBounds(nextFrom)) continue;
            if(maze.getCell(nextFrom).visited()) continue;

            Coordinate wallCoordinate = maze.calculateWallCoordinateFromDirection(from, direction);

            maze.setCellType(wallCoordinate, CellType.PATH);
            maze.markVisited(nextFrom);

            makePath(maze, nextFrom, to);
        }
    }

    private Direction[] sortDirections() {
        Direction[] directions = Direction.values().clone();
        for (int i = 0; i < SHUFFLING_CONSTANT; i++) {
            int newIndex = random.nextInt(Direction.values().length);
            Direction temp = directions[i % Direction.values().length];
            directions[i % Direction.values().length] = directions[newIndex];
            directions[newIndex] = temp;
        }
        return directions;
    }

}
