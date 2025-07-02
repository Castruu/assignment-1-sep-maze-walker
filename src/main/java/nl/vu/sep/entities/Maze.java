package nl.vu.sep.entities;

import nl.vu.sep.entities.enums.CellType;
import nl.vu.sep.entities.enums.Direction;

public class Maze {

    private final Cell[][] maze;

    public Maze(int size) {
        if (size < 3 || size > 15) {
            throw new IllegalArgumentException("Size must be between 3 and 15");
        }
        maze = new Cell[size * 2 + 1][size * 2 + 1];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                CellType type = CellType.WALL;
                if(i % 2 != 0 && j % 2 != 0) {
                    type = CellType.EMPTY;
                }
                maze[i][j] = new Cell(type, false);
            }
        }
    }

    public void setCellType(Coordinate coordinate, CellType type) {
        maze[coordinate.y()][coordinate.x()] = new Cell(type, maze[coordinate.y()][coordinate.x()].visited());
    }

    public void markVisited(Coordinate coordinate) {
        maze[coordinate.y()][coordinate.x()] = new Cell(maze[coordinate.y()][coordinate.x()].type(), true);
    }

    public Coordinate calculateWallCoordinateFromDirection(Coordinate from, Direction direction) {
        return new Coordinate(
                from.x() + direction.getOffset().x(),
                from.y() + direction.getOffset().y()
        );
    }

    public Coordinate calculateCellCoordinateFromDirection(Coordinate from, Direction direction) {
        return new Coordinate(
                from.x() + direction.getOffset().x() * 2,
                from.y() + direction.getOffset().y() * 2
        );
    }

    public boolean isOutOfBounds(Coordinate coordinate) {
        return coordinate.x() < 0 || coordinate.x() >= maze.length || coordinate.y() < 0 || coordinate.y() >= maze[0].length;
    }

    public Cell getCell(Coordinate coordinate) {
        return maze[coordinate.y()][coordinate.x()];
    }

    /**
     * Due to the fact of the walls being cells of the 2D grid called maze,
     * this method returns the calculated by the formula size_passed_on_constructor * 2 + 1
     * @return int maze.length calculated by validCells * 2 + 1
     */
    public int getSize() {
        return maze.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                switch (maze[i][j].type()) {
                    case WALL: {
                        if (i % 2 != 0) {
                            sb.append("|");
                        } else {
                            if(j % 2 != 0) {
                                sb.append("===");
                            } else {
                                sb.append("+");
                            }
                        }
                        break;
                    }
                    case PATH: {
                        if (j % 2 == 0) {
                            sb.append(" ");
                        } else {
                            sb.append("   ");
                        }
                        break;
                    }
                    case PLAYER: {
                        if(i % 2 == 0 || j % 2 == 0) continue;
                        sb.append(" * ");
                        break;
                    }
                    case EMPTY: {
                        sb.append("   ");
                        break;
                    }

                }
            }
            sb.append('\n');
        }

        return sb.toString();
    }
}
