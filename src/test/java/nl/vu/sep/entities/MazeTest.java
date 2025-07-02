package nl.vu.sep.entities;

import nl.vu.sep.entities.enums.CellType;
import nl.vu.sep.entities.enums.Direction;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MazeTest {

    @Test
    void constructor_WithSize2_ShouldThrowError() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Maze(2);
        });
    }

    @Test
    void constructor_WithSize16_ShouldThrowError() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Maze(16);
        });
    }

    @Test
    void constructor_WithSize3_ShouldCreateValidMaze() {
        assertDoesNotThrow(() -> {
            new Maze(3);
        });
    }

    @Test
    void constructor_WithSize15_ShouldCreateValidMaze() {
        assertDoesNotThrow(() -> {
            new Maze(15);
        });
    }

    @Test
    void getSize_WithMazeSize3_ShouldReturn7() {
        Maze maze = new Maze(3);

        assertEquals(7, maze.getSize());
    }

    @Test
    void getCell_At0x0OnNewMaze_ShouldReturnWall() {
        Maze maze = new Maze(3);

        assertEquals(CellType.WALL, maze.getCell(new Coordinate(0, 0)).type());
    }


    @Test
    void getCell_At1x1OnNewMaze_ShouldReturnEmptyCell() {
        Maze maze = new Maze(3);

        assertEquals(CellType.EMPTY, maze.getCell(new Coordinate(1, 1)).type());
    }

    @Test
    void getCell_AtLastCellOnNewMaze_ShouldReturnWall() {
        Maze maze = new Maze(10);

        Cell cell = maze.getCell(new Coordinate(maze.getSize() - 1, maze.getSize() - 1));
        assertEquals(CellType.WALL, cell.type());
    }

    @Test
    void setCellType_AtEmptyCellWithPlayer_ShouldCorrectlyMarkPlayer() {
        Maze maze = new Maze(10);

        maze.setCellType(new Coordinate(5, 5), CellType.PLAYER);

        Cell cell = maze.getCell(new Coordinate(5, 5));
        assertEquals(CellType.PLAYER, cell.type());
    }

    @Test
    void markVisited_AtRandomCell_ShouldMarkVisited() {
        Maze maze = new Maze(10);

        Random random = new Random();
        int coordinate = random.nextInt(maze.getSize());

        maze.markVisited(new Coordinate(coordinate, coordinate));

        assertTrue(maze.getCell(new Coordinate(coordinate, coordinate)).visited());
    }

    @Test
    void isOutOfBounds_At0x0_ShouldReturnFalse() {
        Maze maze = new Maze(10);

        assertFalse(maze.isOutOfBounds(new Coordinate(0, 0)));
    }

    @Test
    void isOutOfBounds_AtMinus1xMinus1_ShouldReturnTrue() {
        Maze maze = new Maze(10);

        assertTrue(maze.isOutOfBounds(new Coordinate(-1, -1)));
    }

    @Test
    void isOutOfBounds_AtMinus1x0_ShouldReturnTrue() {
        Maze maze = new Maze(10);

        assertTrue(maze.isOutOfBounds(new Coordinate(-1, 0)));
    }

    @Test
    void isOutOfBounds_At0xMinus1_ShouldReturnTrue() {
        Maze maze = new Maze(10);

        assertTrue(maze.isOutOfBounds(new Coordinate(0, -1)));
    }

    @Test
    void isOutOfBounds_AtEdgeXEdge_ShouldReturnFalse() {
        Maze maze = new Maze(10);

        assertFalse(maze.isOutOfBounds(new Coordinate(maze.getSize() - 1, maze.getSize() - 1)));
    }

    @Test
    void isOutOfBounds_AtSizeXEdge_ShouldReturnTrue() {
        Maze maze = new Maze(10);

        assertTrue(maze.isOutOfBounds(new Coordinate(maze.getSize(), maze.getSize() - 1)));
    }

    @Test
    void isOutOfBounds_AtEdgeXSize_ShouldReturnTrue() {
        Maze maze = new Maze(10);

        assertTrue(maze.isOutOfBounds(new Coordinate(maze.getSize() - 1, maze.getSize())));
    }

    @Test
    void isOutOfBounds_AtSizeXSize_ShouldReturnTrue() {
        Maze maze = new Maze(10);

        assertTrue(maze.isOutOfBounds(new Coordinate(maze.getSize(), maze.getSize())));
    }



    @Test
    void calculateWallCoordinateFromDirection_At3x3WithNorth_ShouldReturn3x2() {
        Maze maze = new Maze(10);

        Coordinate coordinate = maze.calculateWallCoordinateFromDirection(new Coordinate(3, 3), Direction.NORTH);

        assertEquals(new Coordinate(3, 2), coordinate);
    }

    @Test
    void calculateCellCoordinateFromDirection_At3x3WithNorth_ShouldReturn3x1() {
        Maze maze = new Maze(10);

        Coordinate coordinate = maze.calculateCellCoordinateFromDirection(new Coordinate(3, 3), Direction.NORTH);

        assertEquals(new Coordinate(3, 1), coordinate);
    }

    @Test
    void toString_WithNewBoard_ShouldNotHavePlayerChar() {
        Maze maze = new Maze(10);

        assertFalse(maze.toString().contains(" * "));
    }

    @Test
    void toString_WithBoardWithPlayerOn3x3_ShouldHavePlayerChar() {
        Maze maze = new Maze(10);
        maze.setCellType(new Coordinate(3, 3), CellType.PLAYER);

        assertTrue(maze.toString().contains(" * "));
    }

    @Test
    void toString_WithBoardWithPlayerOn3x2_ShouldNotHavePlayerChar() {
        Maze maze = new Maze(10);
        maze.setCellType(new Coordinate(3, 2), CellType.PLAYER);

        assertFalse(maze.toString().contains(" * "));
    }

    @Test
    void toString_WithBoardWithPlayerOn2x3_ShouldNotHavePlayerChar() {
        Maze maze = new Maze(10);
        maze.setCellType(new Coordinate(2, 3), CellType.PLAYER);

        assertFalse(maze.toString().contains(" * "));
    }


    @Test
    void toString_WithBoardWithPathOn1x0_ShouldHavePathCharOnIndexOneTwoThree() {
        Maze maze = new Maze(10);
        maze.setCellType(new Coordinate(1, 0), CellType.PATH);

        assertAll(
                () -> assertEquals(' ', maze.toString().charAt(1)),
                () -> assertEquals(' ', maze.toString().charAt(2)),
                () -> assertEquals(' ', maze.toString().charAt(3))
        );
    }

    @Test
    void toString_WithBoardWithPathOn0x0_ShouldHavePathCharExactlyOnIndexZero() {
        Maze maze = new Maze(10);
        maze.setCellType(new Coordinate(0, 0), CellType.PATH);

        assertAll(
                () -> assertEquals(' ', maze.toString().charAt(0)),
                () -> assertNotEquals(' ', maze.toString().charAt(1))
        );
    }


}
