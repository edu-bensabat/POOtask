public class Robot37 {
    private final int dre = 122121433;
    private final String name = "Eduardo Bensabat";
    public int currentRow;
    public int currentColumn;
    public int totalRows;
    public int totalColumns;
    public boolean goToCenterMode;
    public boolean clockwiseMode;
    public boolean counterClockwiseMode;
    public boolean stopMode;
    public boolean roomSizeMode;
    public boolean alternatingMode;
    public int robotId;
    public GPS gps;
    public int previousRow;
    public int previousColumn;
    public int centerRow;
    public int centerColumn;
    public int initialColumn;
    public int initialRow;
    public boolean initialClockwiseDirection;

    public Robot37(int robotId, GPS gps) {
        this.stopMode = true;
        this.roomSizeMode = true;
        this.clockwiseMode = false;
        this.counterClockwiseMode = false;
        this.goToCenterMode = false;
        this.initialClockwiseDirection = true;
        this.alternatingMode = false;
        this.robotId = robotId;
        this.gps = gps;
    }

    public int getDRE() {
        return this.dre;
    }

    public String getName() {
        return this.name;
    }

    public void print() {
        System.out.println("dre: " + getDRE());
        System.out.println("name: " + getName());
    }

    public Move move() {
        this.currentRow = gps.getRow(robotId);
        this.currentColumn = gps.getColumn(robotId);
        if (roomSizeMode) {
            previousColumn = currentColumn - 1;
            previousRow = currentRow - 1;
            while (previousColumn != currentColumn) {
                previousColumn = currentColumn;
                gps.move(Move.RIGHT);
                currentColumn = gps.getColumn(robotId);
            }
            totalColumns = currentColumn;
            while (previousRow != currentRow) {
                previousRow = currentRow;
                gps.move(Move.DOWN);
                currentRow = gps.getRow(robotId);
            }
            totalRows = currentRow;
            this.centerRow = totalRows / 2 + 1;
            this.centerColumn = totalColumns / 2 + 1;
            this.initialRow = totalRows + 1;
            this.initialColumn = totalColumns + 1;
            this.roomSizeMode = false;
        }
        if (stopMode) {
            return Move.STOP;
        } else if (goToCenterMode) {
            this.initialRow = totalRows + 1;
            this.initialColumn = totalColumns + 1;
            currentRow = gps.getRow(robotId);
            currentColumn = gps.getColumn(robotId);
            if (currentRow < centerRow) {
                gps.move(Move.DOWN);
                currentRow = gps.getRow(robotId);
                return Move.DOWN;
            } else if (currentRow > centerRow) {
                gps.move(Move.UP);
                currentRow = gps.getRow(robotId);
                currentRow = gps.getRow(robotId);
                return Move.UP;
            } else if (currentColumn > centerColumn) {
                gps.move(Move.LEFT);
                currentColumn = gps.getColumn(robotId);
                return Move.LEFT;
            } else if (currentColumn < centerColumn) {
                gps.move(Move.RIGHT);
                currentColumn = gps.getColumn(robotId);
                return Move.RIGHT;
            } else {
                stop();
                return Move.STOP;
            }
        } else if (clockwiseMode) {
            this.initialRow = totalRows + 1;
            this.initialColumn = totalColumns + 1;
            if (currentRow == totalRows && currentColumn != 1) {
                gps.move(Move.LEFT);
                currentColumn = gps.getColumn(robotId);
                return Move.LEFT;
            } else if (currentColumn == totalColumns && currentRow != totalRows) {
                gps.move(Move.DOWN);
                currentRow = gps.getRow(robotId);
                return Move.DOWN;
            } else if (currentColumn == 1 && currentRow != 1) {
                gps.move(Move.UP);
                currentRow = gps.getRow(robotId);
                return Move.UP;
            } else if (currentRow == 1 && currentColumn != totalColumns) {
                gps.move(Move.RIGHT);
                currentColumn = gps.getColumn(robotId);
                return Move.RIGHT;
            } else {
                gps.move(Move.DOWN);
                currentRow = gps.getRow(robotId);
                return Move.DOWN;
            }
        } else if (counterClockwiseMode) {
            this.initialRow = totalRows + 1;
            this.initialColumn = totalColumns + 1;
            if (currentRow == totalRows && currentColumn != totalColumns) {
                gps.move(Move.RIGHT);
                currentColumn = gps.getColumn(robotId);
                return Move.RIGHT;
            } else if (currentColumn == totalColumns && currentRow != 1) {
                gps.move(Move.UP);
                currentRow = gps.getRow(robotId);
                return Move.UP;
            } else if (currentColumn == 1 && currentRow != totalRows) {
                gps.move(Move.DOWN);
                currentRow = gps.getRow(robotId);
                return Move.DOWN;
            } else if (currentRow == 1 && currentColumn != 1) {
                gps.move(Move.LEFT);
                currentColumn = gps.getColumn(robotId);
                return Move.LEFT;
            } else {
                gps.move(Move.DOWN);
                currentRow = gps.getRow(robotId);
                return Move.DOWN;
            }
        } else if (alternatingMode) {
            if (currentRow != totalRows && currentRow != 1 && currentColumn != totalColumns && currentColumn != 1) {
                gps.move(Move.DOWN);
                currentRow = gps.getRow(robotId);
                return Move.DOWN;
            } else {
                if (currentRow == initialRow && currentColumn == initialColumn) {
                    initialClockwiseDirection = !initialClockwiseDirection;
                }
                if (initialRow > totalRows) {
                    initialRow = currentRow;
                    initialClockwiseDirection = true;
                }
                if (initialColumn > totalColumns) {
                    initialColumn = currentColumn;
                    initialClockwiseDirection = true;
                }
                if (initialClockwiseDirection) {
                    if (currentRow == totalRows && currentColumn != 1) {
                        gps.move(Move.LEFT);
                        currentColumn = gps.getColumn(robotId);
                        return Move.LEFT;
                    } else if (currentColumn == totalColumns && currentRow != totalRows) {
                        gps.move(Move.DOWN);
                        currentRow = gps.getRow(robotId);
                        return Move.DOWN;
                    } else if (currentColumn == 1 && currentRow != 1) {
                        gps.move(Move.UP);
                        currentRow = gps.getRow(robotId);
                        return Move.UP;
                    } else {
                        gps.move(Move.RIGHT);
                        currentColumn = gps.getColumn(robotId);
                        return Move.RIGHT;
                    }
                }
                if (currentRow == totalRows && currentColumn != totalColumns) {
                    gps.move(Move.RIGHT);
                    currentColumn = gps.getColumn(robotId);
                    return Move.RIGHT;
                } else if (currentColumn == totalColumns && currentRow != 1) {
                    gps.move(Move.UP);
                    currentRow = gps.getRow(robotId);
                    return Move.UP;
                } else if (currentColumn == 1 && currentRow != totalRows) {
                    gps.move(Move.DOWN);
                    currentRow = gps.getRow(robotId);
                    return Move.DOWN;
                } else {
                    gps.move(Move.LEFT);
                    currentColumn = gps.getColumn(robotId);
                    return Move.LEFT;
                }
            }
        }
        stop();
        return Move.STOP;
    }

    public void stop() {
        this.stopMode = true;
        this.clockwiseMode = false;
        this.counterClockwiseMode = false;
        this.alternatingMode = false;
        this.goToCenterMode = false;
    }

    public void clockwise() {
        this.stopMode = false;
        this.clockwiseMode = true;
        this.counterClockwiseMode = false;
        this.alternatingMode = false;
        this.goToCenterMode = false;
    }

    public void counterClockwise() {
        this.stopMode = false;
        this.clockwiseMode = false;
        this.counterClockwiseMode = true;
        this.alternatingMode = false;
        this.goToCenterMode = false;
    }

    public void alternating() {
        this.stopMode = false;
        this.clockwiseMode = false;
        this.counterClockwiseMode = false;
        this.alternatingMode = true;
        this.goToCenterMode = false;
    }

    public void goToCenter() {
        this.stopMode = false;
        this.clockwiseMode = false;
        this.counterClockwiseMode = false;
        this.alternatingMode = false;
        this.goToCenterMode = true;
    }
}
