package nl.vu.sep.entities;

import nl.vu.sep.entities.enums.CellType;

public record Cell(CellType type, boolean visited) { }
