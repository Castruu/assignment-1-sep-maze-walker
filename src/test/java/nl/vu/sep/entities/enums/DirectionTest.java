package nl.vu.sep.entities.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    @Test
    void getDirectionFromLetter_WithS_ShouldReturnSouth() {
        assertEquals(Direction.SOUTH, Direction.getDirectionFromLetter('S'));
    }

    @Test
    void getDirectionFromLetter_WithN_ShouldReturnNorth() {
        assertEquals(Direction.NORTH, Direction.getDirectionFromLetter('N'));
    }

    @Test
    void getDirectionFromLetter_WithE_ShouldReturnEast() {
        assertEquals(Direction.EAST, Direction.getDirectionFromLetter('E'));
    }

    @Test
    void getDirectionFromLetter_WithW_ShouldReturnWest() {
        assertEquals(Direction.WEST, Direction.getDirectionFromLetter('W'));
    }

    @Test
    void getDirectionFromLetter_WithT_ShouldThrowError() {
        assertThrows(IllegalArgumentException.class, () -> {
            Direction.getDirectionFromLetter('T');
        });
    }

}
