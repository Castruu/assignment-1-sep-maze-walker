package nl.vu.sep.engine;

import nl.vu.sep.entities.Cell;
import nl.vu.sep.entities.Coordinate;
import nl.vu.sep.entities.enums.CellType;
import nl.vu.sep.entities.enums.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MazeEngineTest {

    @Test
    void constructor_WithSeed10AndSize10_ShouldHaveAPlayerOn1x1() {
        MazeEngine engine = new MazeEngine(10, 10);
        Cell cell = engine.getMaze().getCell(new Coordinate(1, 1));

        assertEquals(CellType.PLAYER, cell.type());
    }

    @Test
    void movePlayer_From1x1ToNorth_ShouldThrowError() {
        MazeEngine engine = new MazeEngine(10, 10, new Coordinate(1, 1));

        assertThrows(IllegalArgumentException.class,
                () -> engine.movePlayer(Direction.NORTH)
        );
    }

    @Test
    void movePlayer_From1x1ToSouthWithPathIn1x2_ShouldSetPlayerOn1x3() {
        MazeEngine engine = new MazeEngine(10, 10, new Coordinate(1, 1));
        engine.getMaze().setCellType(new Coordinate(1, 2), CellType.PATH);

        engine.movePlayer(Direction.SOUTH);

        Cell cell = engine.getMaze().getCell(new Coordinate(1, 3));

        assertEquals(CellType.PLAYER, cell.type());
    }

    @Test
    void isPlayerAtEndOfMaze_WithPlayerAt5x5OnMazeSize3_ShouldReturnTrue() {
        MazeEngine engine = new MazeEngine(10, 3, new Coordinate(5, 5));

        assertTrue(engine.isPlayerInEndOfMaze());
    }




}
