package nl.vu.sep.engine;

import nl.vu.sep.entities.Coordinate;
import nl.vu.sep.entities.Maze;
import nl.vu.sep.entities.enums.CellType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class MazeGeneratorTest {

    @Test
    void generateMaze_WithSameSeedAndSize_ShouldReturnSame() {
        MazeGenerator generator1 = new MazeGenerator(10);
        MazeGenerator generator2 = new MazeGenerator(10);

        Maze maze1 = generator1.generateMaze(10);
        Maze maze2 = generator2.generateMaze(10);

        assertAll(() -> {
            for (int i = 0; i < maze1.getSize(); i++) {
                for (int j = 0; j < maze1.getSize(); j++) {
                    assertEquals(maze1.getCell(new Coordinate(j, i)), maze2.getCell(new Coordinate(j, i)));
                }
            }
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7, 8, 9, 10})
    void generateMaze_WithNormalSeedAndMultipleSizes_ShouldGenerateSizeTimesSizeMinus1Paths(int size) {
        MazeGenerator generator = new MazeGenerator(10);

        Maze maze = generator.generateMaze(size);

        int count = 0;
        int expected = size * size - 1;

        for (int i = 0; i < maze.getSize(); i++) {
            for (int j = 0; j < maze.getSize(); j++) {
                if (maze.getCell(new Coordinate(j, i)).type() == CellType.PATH) count++;
            }
        }

        assertEquals(expected, count);
    }

}
