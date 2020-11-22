package Model;

public class Indexing {

    public static final int EMPTY = -1;

    public static final int WHITE_SHORT_SOLID_SQUARE = 1111;
    public static final int WHITE_SHORT_HOLLOW_SQUARE = 1121;
    public static final int WHITE_TALL_SOLID_SQUARE = 1211;
    public static final int WHITE_TALL_HOLLOW_SQUARE = 1221;
    public static final int WHITE_SHORT_SOLID_CIRCLE = 1112;
    public static final int WHITE_SHORT_HOLLOW_CIRCLE = 1122;
    public static final int WHITE_TALL_SOLID_CIRCLE = 1212;
    public static final int WHITE_TALL_HOLLOW_CIRCLE = 1222;

    public static final int BLACK_SHORT_SOLID_SQUARE = 2111;
    public static final int BLACK_SHORT_HOLLOW_SQUARE = 2121;
    public static final int BLACK_TALL_SOLID_SQUARE = 2211;
    public static final int BLACK_TALL_HOLLOW_SQUARE = 2221;
    public static final int BLACK_SHORT_SOLID_CIRCLE = 2112;
    public static final int BLACK_SHORT_HOLLOW_CIRCLE = 2122;
    public static final int BLACK_TALL_SOLID_CIRCLE = 2212;
    public static final int BLACK_TALL_HOLLOW_CIRCLE = 2222;

    public static final int[][] VICTORY_CONDITIONS = {
        {0, 1, 2, 3}, {4, 5, 6, 7}, {8, 9, 10, 11}, {12, 13, 14, 15},
        {0, 4, 8, 12}, {1, 5, 9, 13}, {2, 6, 10, 15}, {3, 7, 11, 15},
        {0, 5, 10, 15}, {3, 6, 9, 12}
    };

}
