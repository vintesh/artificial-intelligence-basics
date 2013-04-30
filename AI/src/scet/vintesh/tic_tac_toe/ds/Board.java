/*
 * Implemented as Tutorial of Masters Program 
 * M.E. - Computer Engineering 
 * Artificial Intelligence
 * SCET, Surat
 */
package scet.vintesh.tic_tac_toe.ds;

import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;
import scet.vintesh.tic_tac_toe.ds.Board.TileValue;

/**
 *
 * @author Vintesh
 */
public final class Board implements Comparable<Board> {

    /**
     * Represents the O/X/_ value on the Board
     */
    enum TileValue {

        BLANK("_"), ZERO("O"), CROSS("X");
        private String text;

        private TileValue(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return this.text;
        }
    }

    /**
     * For Representing whose move is this. O's or X's
     */
    enum WhoseTurn {

        X, O;

        WhoseTurn getOtherOne() {
            switch (this) {
                case X:
                    return WhoseTurn.O;
                case O:
                    return WhoseTurn.X;
                default:
                    return null;
            }
        }
    }
    private Board parent;
    private PriorityQueue<Board> childs;
    /**
     * Current status of the Board which SIGN is placed @which location
     */
    private TileValue[][] tiles;
    public float MinMaxVal;
    private float heuristicValue;

    public Board(Board parent) {
        // Setting up the Blank Tiles initially
        TileValue[][] tileValues = new TileValue[3][3];
        Arrays.fill(tileValues[0], TileValue.BLANK);
        Arrays.fill(tileValues[1], TileValue.BLANK);
        Arrays.fill(tileValues[2], TileValue.BLANK);

        this.tiles = tileValues;
        this.childs = new PriorityQueue<Board>();
        this.parent = parent;
        // Setting & calculating the position of blank tile.
        this.heuristicValue = calculateHeuristicValue();
    }

    /**
     * For generating the Children for a particular state.
     *
     * @return The Board with state ALL BLANK i.e. No Zero/Cross on any place
     */
    private TileValue[][] getNewTileSet() {

        TileValue[][] newTileValuesForChild = new TileValue[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(tiles[i], 0, newTileValuesForChild[i], 0, 3);
        }
        return newTileValuesForChild;
    }

    /**
     * Generate Possible children according to turn of user
     */
    public void generateChildren(WhoseTurn whichNextSymbolToPut) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (tiles[i][j] == TileValue.BLANK) {
                    TileValue[][] newTileValuesForChild = getNewTileSet();

                    if (whichNextSymbolToPut == WhoseTurn.O) {
                        newTileValuesForChild[i][j] = TileValue.ZERO;
                    } else {
                        newTileValuesForChild[i][j] = TileValue.CROSS;
                    }

                    Board newChild = new Board(this);
                    newChild.setTiles(newTileValuesForChild);
                    this.addChild(newChild);
                }

            }
        }
    }

    /**
     * @TODO: Implement .Equals Method Help can be found out on:
     * http://stackoverflow.com/questions/6160231/efficient-algorithm-for-counting-unique-states-of-tic-tac-toe/6160911
     *
     * @param obj - Object That need to compare
     * @return - true if same object & false if not.
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int compareTo(Board object) {
        // For Arranging the Objects in Decreasing Order  & 
        // reverse the < or > for the Accending order.
        if (this.getHeuristicValue() > object.getHeuristicValue()) {
            return -1;
        } else if (this.getHeuristicValue() < object.getHeuristicValue()) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * @return No of ways in which O can Win
     */
    private float getNoOfChancesWhereOCanWin() {
        int points = 0;

        // For Checking 3 Rows
        for (int i = 0; i < 3; i++) {
            boolean isOpponentSignExist = false;
            for (int j = 0; j < 3; j++) {
                if (tiles[i][j] == TileValue.CROSS) {
                    isOpponentSignExist = true;
                    break;
                }
            }
            if (isOpponentSignExist == false) {
                points++;
            }
        }
        // For Checking 3 Columns
        for (int i = 0; i < 3; i++) {
            boolean isOpponentSignExist = false;
            for (int j = 0; j < 3; j++) {
                if (tiles[j][i] == TileValue.CROSS) {
                    isOpponentSignExist = true;
                    break;
                }
            }
            if (isOpponentSignExist == false) {
                points++;
            }
        }
        // for Checking 2 Diagonals
        if (tiles[0][0] != TileValue.CROSS && tiles[1][1] != TileValue.CROSS && tiles[2][2] != TileValue.CROSS) {
            points++;
        }
        if (tiles[0][2] != TileValue.CROSS && tiles[1][1] != TileValue.CROSS && tiles[2][0] != TileValue.CROSS) {
            points++;
        }
        return points;
    }

    /**
     * @return No of ways in which X can Win
     */
    private float getNoOfChancesWhereXCanWin() {
        int points = 0;

        // For Checking 3 Rows
        for (int i = 0; i < 3; i++) {
            boolean isOpponentSignExist = false;
            for (int j = 0; j < 3; j++) {
                if (tiles[i][j] == TileValue.ZERO) {
                    isOpponentSignExist = true;
                    break;
                }
            }
            if (isOpponentSignExist == false) {
                points++;
            }
        }
        // For Checking 3 Columns
        for (int i = 0; i < 3; i++) {
            boolean isOpponentSignExist = false;
            for (int j = 0; j < 3; j++) {
                if (tiles[j][i] == TileValue.ZERO) {
                    isOpponentSignExist = true;
                    break;
                }
            }
            if (isOpponentSignExist == false) {
                points++;
            }
        }
        // for Checking 2 Diagonals
        if (tiles[0][0] != TileValue.ZERO && tiles[1][1] != TileValue.ZERO && tiles[2][2] != TileValue.ZERO) {
            points++;
        }
        if (tiles[0][2] != TileValue.ZERO && tiles[1][1] != TileValue.ZERO && tiles[2][0] != TileValue.ZERO) {
            points++;
        }
        return points;
    }

    /**
     * Pass Whose player is turn it is & heauristic value will be calculated &
     * return the value
     *
     * |O|X| | .................................................................
     * | | | | Say for this position now turn is of X then heauristic value is .
     * |O| | | 3 - 6 = -3 for the X ............................................
     *
     * @param turn - Whose turn is this X or O
     * @return - Heauristic value of the tile for a particular position of tile.
     */
    private float calculateHeuristicValue() {
        return getNoOfChancesWhereXCanWin() - getNoOfChancesWhereOCanWin();
    }

    /**
     * When the user move to particular state with O/X then to set the TILE
     * Values with O/X
     *
     * @param nextMove - is the position where user move
     * @param tileValue - O/X - which is to be placed
     */
    public void setMove(int nextMove, TileValue tileValue) {
        // nextMove -= 1;
        this.tiles[(nextMove / 3)][(nextMove % 3)] = tileValue;
    }

    /**
     * Add Child to the list of the current Children
     *
     * @param newChild - child that need to be added to list of a node/board.
     */
    private void addChild(Board newChild) {
        childs.add(newChild);
    }

    /**
     * @return - Returns the list of the children available with the node.
     */
    public PriorityQueue<Board> getChilds() {
        return childs;
    }

    public Board getParent() {
        return parent;
    }

    public TileValue[][] getTiles() {
        return this.tiles;
    }

    /**
     * Setting the new TILE Values to the Board
     *
     * @param tiles - new TILE values 3x3 Matrix
     */
    private void setTiles(TileValue[][] tiles) {
        this.tiles = tiles;
        this.heuristicValue = calculateHeuristicValue();
    }

    public float getHeuristicValue() {
        calculateHeuristicValue();
        return heuristicValue;
    }

    /**
     * Get the Board instance with given Min/Max Value
     *
     * @param minMaxValue
     * @return - The board instance for given minMaxValue
     */
    public Board getChildrenByMinMaxVal(float minMaxValue) {
        for (Iterator<Board> it = childs.iterator(); it.hasNext();) {
            Board board = it.next();
            if (board.MinMaxVal == minMaxValue) {
                return board;
            }
        }
        throw new IllegalStateException("No Node Found with the MinMaxVal: " + minMaxValue);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(Arrays.toString(tiles[0])).
                append(Arrays.toString(tiles[1])).
                append(Arrays.toString(tiles[2])).
                append(" h(x): ").append(getHeuristicValue());
        return str.toString();
    }

    /**
     * Tester
     *
     * @param args
     */
    public static void main(String[] args) {
        TileValue[][] board = new TileValue[3][3];
        board[0][0] = TileValue.CROSS;
        board[0][1] = TileValue.BLANK;
        board[0][2] = TileValue.ZERO;
        board[1][0] = TileValue.BLANK;
        board[1][1] = TileValue.BLANK;
        board[1][2] = TileValue.BLANK;
        board[2][0] = TileValue.ZERO;
        board[2][1] = TileValue.BLANK;
        board[2][2] = TileValue.BLANK;
        Board node = new Board(null);
        node.setTiles(board);
        System.out.println("Node: " + node);

        node.generateChildren(WhoseTurn.X);
        for (Iterator<Board> it = node.childs.iterator(); it.hasNext();) {
            Board board1 = it.next();
            System.out.println(board1);
        }
    }
}
