package nl.vu.sep.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MazeTest {

    @Test
    void constructor_WithSize2_ShouldThrowError() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Maze(2);
        }, "Should have thrown exception with with size 2.");
    }

}
