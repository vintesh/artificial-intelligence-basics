/*
 * Implemented as Tutorial of Masters Program 
 * M.E. - Computer Engineering 
 * Artificial Intelligence
 * SCET, Surat
 */
package scet.vintesh.eight_puzzle.ds;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Vintesh
 */
public final class Board implements Comparable<Board> {

    private Board parent;
    private ArrayList<Board> childs;
    /**
     * Tiles having the value 0 to 8 ... 0 means the BLACK TILE
     */
    private int[][] tiles = new int[3][3];
    private float heuristicValue;
    private float actualValue;
    private Position blankTilePosition;

    public Board(Board parent, int[][] tiles) {
        this.parent = parent;
        this.tiles = tiles;
        // Setting & calculating the position of blank tile.
        this.calculateActualCostUptillNow();
        this.calculateHeuristicValue();
        this.blankTilePosition = calculateBlankTilePosition();
    }

    /**
     * Calculating the h(x) value
     */
    public void calculateHeuristicValue() {
        this.heuristicValue = calculateManhattanDistance() + calculateMisPlacedTiles();
    }

    /**
     * Calculate the sum of the Misplaced Tiles from the GOAL State.
     *
     * @return - Number of misplaced Tiles from the GOAL STATE
     */
    private float calculateMisPlacedTiles() {
        int totalSum = 0;
        if (SearchSpace.goalState != null) {
            int[][] goalStateTiles = SearchSpace.goalState.getTiles();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (tiles[i][j] != goalStateTiles[i][j]) {
                        totalSum++;
                    }
                }
            }
        }
        return totalSum;
    }

    /**
     * Calculates sum of Manhattan distances for this board and stores it in
     * private field to promote immutability. From the StackOverFlow.
     */
    private float calculateManhattanDistance() {
        int manhattanDistanceSum = 0;
        int N = 3;// For 3x3 Puzzle Square

        for (int x = 0; x < N; x++) // x-dimension, traversing rows (i)
        {
            for (int y = 0; y < N; y++) { // y-dimension, traversing cols (j)
                int value = tiles[x][y]; // tiles array contains board elements
                if (value != 0) { // we don't compute MD for element 0
                    int targetX = (value - 1) / N; // expected x-coordinate (row)
                    int targetY = (value - 1) % N; // expected y-coordinate (col)
                    int dx = x - targetX; // x-distance to expected coordinate
                    int dy = y - targetY; // y-distance to expected coordinate
                    manhattanDistanceSum += Math.abs(dx) + Math.abs(dy);
                }
            }
        }
        return manhattanDistanceSum;
    }

    /*
     * gx
     */
    public void calculateActualCostUptillNow() {
        if (parent != null) {
            this.actualValue = parent.getActualValue() + 1;
        } else {
            this.actualValue = 1;
        }
    }

    /**
     * Function F(x) = h(x) + g(x)
     *
     * @return the F(x) value
     */
    public float getFinalHeauristicValue() {
        return this.actualValue + this.heuristicValue;
    }

    public ArrayList<Board> generateSetAndGetChilds(Board board) {
        ArrayList<MovesOfBlankSlide> possibleMoves = findPossibleMoves(board.getBlankTilePosition());
        final ArrayList<Board> childerns = generateChilds(board, possibleMoves);
        this.childs = childerns;
        return childerns;
    }

    /**
     * Finding the List of directions in which the tile can go.
     *
     * @param blankTilePosition The position of the BLANK tile in the Board
     * @return List of the possible MovesOfBlankSlide
     */
    private static ArrayList<MovesOfBlankSlide> findPossibleMoves(Position blankTilePosition) {
        ArrayList<MovesOfBlankSlide> possibleMoves = new ArrayList<MovesOfBlankSlide>();
        // Checking the ROW Position
        if (blankTilePosition.x == 0) {
            possibleMoves.add(MovesOfBlankSlide.DOWN);
        } else if (blankTilePosition.x == 1) {
            possibleMoves.add(MovesOfBlankSlide.DOWN);
            possibleMoves.add(MovesOfBlankSlide.UP);
        } else if (blankTilePosition.x == 2) {
            possibleMoves.add(MovesOfBlankSlide.UP);
        }
        // Chekcing the COLUMN Position
        if (blankTilePosition.y == 0) {
            possibleMoves.add(MovesOfBlankSlide.RIGHT);
        } else if (blankTilePosition.y == 1) {
            possibleMoves.add(MovesOfBlankSlide.LEFT);
            possibleMoves.add(MovesOfBlankSlide.RIGHT);
        } else if (blankTilePosition.y == 2) {
            possibleMoves.add(MovesOfBlankSlide.LEFT);
        }

        return possibleMoves;
    }

    /**
     * Generates the Child nodes of a particular nodes using list of possible
     * moves.
     *
     * @param possibleMoves
     * @return list of Child Nodes
     */
    private static ArrayList<Board> generateChilds(Board board, ArrayList<MovesOfBlankSlide> possibleMoves) {
        ArrayList<Board> childList = new ArrayList<Board>();

        for (MovesOfBlankSlide moves : possibleMoves) {
            // Cloning the Array to Generate the new Children
            int[][] tiles = new int[3][];
            for (int i = 0; i < 3; i++) {
                tiles[i] = board.getTiles()[i].clone();
            }
            // This is not working as the 2D array passed By Reference Problem
            // int[][] tiles = board.getTiles();
            Position blankTileXY = board.getBlankTilePosition();
            int x = tiles[blankTileXY.x][blankTileXY.y];

            switch (moves) {
                case UP:
                    tiles[blankTileXY.x][blankTileXY.y] = tiles[blankTileXY.x - 1][blankTileXY.y];
                    tiles[blankTileXY.x - 1][blankTileXY.y] = x;
                    childList.add(new Board(board, tiles));
                    break;
                case DOWN:
                    tiles[blankTileXY.x][blankTileXY.y] = tiles[blankTileXY.x + 1][blankTileXY.y];
                    tiles[blankTileXY.x + 1][blankTileXY.y] = x;
                    childList.add(new Board(board, tiles));
                    break;
                case LEFT:
                    tiles[blankTileXY.x][blankTileXY.y] = tiles[blankTileXY.x][blankTileXY.y - 1];
                    tiles[blankTileXY.x][blankTileXY.y - 1] = x;
                    childList.add(new Board(board, tiles));
                    break;
                case RIGHT:
                    tiles[blankTileXY.x][blankTileXY.y] = tiles[blankTileXY.x][blankTileXY.y + 1];
                    tiles[blankTileXY.x][blankTileXY.y + 1] = x;
                    childList.add(new Board(board, tiles));
                    break;
                default:
                    throw new IllegalStateException("Wrong TILE MOVE ..... CHILDREN are not generated");
            }
        }
        return childList;
    }

    /**
     * Calculates the Position of blank Tile in the board.
     *
     * @return - returns the POSITION object which specifies the position X,Y of
     * the Blank Tile
     */
    private Position calculateBlankTilePosition() {
        int tilesOfBoard[][] = getTiles();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tilesOfBoard[i][j] == 0) {
                    return new Position(i, j);
                }
            }
        }
        throw new IllegalStateException("Blank - Tile is not found.");
    }

    public int[][] getTiles() {
        return tiles;
    }

    public Position getBlankTilePosition() {
        return blankTilePosition;
    }

    /**
     * @return - Node's g(x) value
     */
    public float getActualValue() {
        return actualValue;
    }

    /**
     * Compares The given Object to the other.
     *
     * @param o - The Comparable Object
     * @return +1 - when Current object is Greater then the Given/Passed Object
     * -1 - when Current object is Less then the Given/Passed Object 0 - when
     * both objects are Equal in terms of their ESTIMATED COST.
     */
    public int compareTo(Board board) {
        if (this.getFinalHeauristicValue() < board.getFinalHeauristicValue()) {
            return -1;
        } else if (this.getFinalHeauristicValue() > board.getFinalHeauristicValue()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Board) {
            int[][] boardState = ((Board) obj).getTiles();
            int[][] currentBoardState = this.getTiles();
            boolean flag = true;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (boardState[i][j] != currentBoardState[i][j]) {
                        flag = false;
                        break;
                    }
                }
            }
            return flag;
        }
        throw new IllegalStateException(".Equals method is called on different type object");
    }

    @Override
    public String toString() {
        return "State= " + Arrays.toString(tiles[0]) + " , " + Arrays.toString(tiles[1]) + " , "
                + Arrays.toString(tiles[2]) + " | f(x)= " + getFinalHeauristicValue() + " | h(x)= " + heuristicValue + " | g(x)= "
                + actualValue;
    }

    public void setParent(Board parent) {
        this.parent = parent;
    }

    public void setChildren(ArrayList<Board> successorsToSet) {
        this.childs = successorsToSet;
    }

    public Board getParent() {
        return this.parent;
    }

    public boolean isChildAvailable() {
        return childs == null ? false : true;
    }

    public ArrayList<Board> getChildrens() {
        return childs;
    }
}
