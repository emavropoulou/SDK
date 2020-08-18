package another.sudoku.helpers;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.annimon.stream.IntStream;

public class Initialization {

    public int[][] initializeSudokuNumbers(int SquareRoot) {
        int counter;

        int mplampla = 0;
        int secondCounter = 0;

        int[][] data = new int[SquareRoot * SquareRoot][SquareRoot * SquareRoot];

        for (int x = 0; x < (SquareRoot * SquareRoot); x++) {
            counter = 0;
            List<Integer> testarray = fillList(SquareRoot);
            List<Integer> testedNumbers = new ArrayList<>();
            for (int y = 0; y < (SquareRoot * SquareRoot); y++) {
                testedNumbers.clear();

                boolean ttt = false;
                while (!ttt) {
                    mplampla = mplampla + 1;
                    int numberToPut = getRandom(testarray);

                    data[x][y] = numberToPut;

                    testarray.remove(Integer.valueOf(numberToPut));

                    if (isAvailableInRow(x, y, data) && isAvailableInColumn(x, y, data) && isAvailableInBlock(x, y, SquareRoot, data)) {
                        ttt = true;
                    } else {
                        ttt = false;

                        testarray.add(numberToPut);

                        if (!testedNumbers.contains(numberToPut)) {
                            testedNumbers.add(numberToPut);
                        }

                        Collections.sort(testedNumbers);
                        Collections.sort(testarray);

                        if (testarray.equals(testedNumbers)) {
                            counter = counter + 1;
                            if (counter == (SquareRoot * SquareRoot)) {
                                counter = 0;
                                secondCounter = secondCounter + 1;
                                testedNumbers.clear();
                                testarray.clear();
                                testarray = fillList(SquareRoot);
                                if (secondCounter < x) {
                                    for (int p = x - secondCounter; p < (SquareRoot * SquareRoot); p++) {
                                        for (int g = 0; g < SquareRoot * SquareRoot; g++) {
                                            data[p][g] = 0;
                                        }
                                    }
                                    if (x != 0) {
                                        y = 0;
                                        x = x - secondCounter;
                                    } else {
                                        x = 0;
                                        y = 0;
                                    }
                                } else {
                                    for (int p = 0; p < (SquareRoot * SquareRoot); p++) {
                                        for (int g = 0; g < SquareRoot * SquareRoot; g++) {
                                            data[p][g] = 0;
                                        }
                                    }
                                    secondCounter = 0;
                                    counter = 0;
                                    x = 0;
                                    y = 0;
                                }
                            } else {
                                if (y != 0) {
                                    testedNumbers.clear();
                                    testarray.clear();
                                    testarray = fillList(SquareRoot);
                                    for (int g = 0; g < SquareRoot * SquareRoot; g++) {
                                        data[x][g] = 0;
                                    }
                                    y = 0;
                                } else {
                                    testedNumbers.clear();
                                    testarray.clear();
                                    testarray = fillList(SquareRoot);
                                    for (int g = 0; g < SquareRoot * SquareRoot; g++) {
                                        data[x - 1][g] = 0;
                                    }
                                    if (x != 0) {
                                        y = 0;
                                        x = x - 1;
                                    } else {
                                        x = 0;
                                        y = 0;
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }
        return data;
    }


    public int[][] myinitializeSudokuNumbers(int SquareRoot) {
        int mplampla = 0;
        int counterx = 0;
        int countery = 0;
        int tempx = 0;
        int tempy = 0;

        int[][] data = new int[SquareRoot * SquareRoot][SquareRoot * SquareRoot];

        for (int x = 0; x < (SquareRoot * SquareRoot); x++) {
            tempy = 0;
            countery = 0;

            List<Integer> testarray = fillList(SquareRoot);
            List<Integer> nonfittinginSquare = new ArrayList<>();
            for (int y = 0; y < (SquareRoot * SquareRoot); y++) {
                boolean ttt = false;
                while (!ttt) {
                    mplampla = mplampla + 1;

                    for (int b = 0; b < testarray.size(); b++) {
                        data[x][y] = testarray.get(b);
                        if (isAvailableInRow(x, y, data) && isAvailableInColumn(x, y, data) && isAvailableInBlock(x, y, SquareRoot, data)) {
                        } else {
                            nonfittinginSquare.add(testarray.get(b));
                        }
                        data[x][y] = 0;
                    }
                    testarray.removeAll(nonfittinginSquare);
                    testarray.remove(Integer.valueOf(0));

                    if ((testarray.size() != 0)) {
                        int numberToPut = getRandom(testarray);

                        data[x][y] = numberToPut;
                        testarray.remove(Integer.valueOf(numberToPut));

                        ttt = true;
                        testarray.addAll(nonfittinginSquare);
                        nonfittinginSquare.clear();
                    } else {
                        ttt = false;
                        testarray.addAll(nonfittinginSquare);
                        nonfittinginSquare.clear();
                        if (tempy >= y) {
                            countery = countery + 1;
                        } else {
                            countery = 1;
                            tempy = y;
                        }
                        if (y > 0 && y >= countery) {
                            for (int g = y - countery; g <= y; g++) {
                                testarray.add(data[x][g]);
                                data[x][g] = 0;
                            }
                            y = y - countery;
                        } else {
                            testarray.clear();
                            testarray = fillList(SquareRoot);
                            if (tempx <= x) {
                                counterx = counterx + 1;
                            } else {
                                counterx = 1;
                                tempx = x;
                            }
                            if (x > 0 && x >= counterx) {
                                for (int p = x - counterx; p <= x; p++) {
                                    for (int g = 0; g < (SquareRoot * SquareRoot); g++) {
                                        data[p][g] = 0;
                                    }
                                }
                                x = x - counterx;
                                y = 0;
                                countery = 0;
                                tempy = 0;
                            } else {
                                for (int p = 0; p <= x; p++) {
                                    for (int g = 0; g < (SquareRoot * SquareRoot); g++) {
                                        data[p][g] = 0;
                                    }
                                }
                                x = 0;
                                y = 0;
                                counterx = 0;
                                countery = 0;
                                tempy = 0;
                                tempx = 0;
                            }
                        }
                    }
                }
            }
        }
        return data;
    }

    private List<Integer> fillList(int squareRoot) {
        List<Integer> tempList = new ArrayList<>();
        for (int i = 1; i <= squareRoot * squareRoot; i++) {
            tempList.add(i);
        }
        return tempList;
    }

    private static int getRandom(List<Integer> array) {
        int rnd = new Random().nextInt(array.size());
        return Integer.parseInt(array.get(rnd).toString());
    }

    private boolean isAvailableInRow(int currentRow, int currentColumn, int[][] data) {
        for (int j = 0; j < currentColumn; j++) {
            if (data[currentRow][j] == data[currentRow][currentColumn]) return false;
        }
        return true;
    }

    private int[] currentRowState(int currentRow, int currentColumn, int[][] data) {

        int[] row = new int[currentColumn];
        System.arraycopy(data[currentRow], 0, row, 0, currentColumn);
        return row;
    }

    private int[] currentColumnState(int currentRow, int currentColumn, int[][] data) {

        int[] column = new int[currentRow];
        for (int i = 0; i < currentRow; i++) {
            column[i] = data[i][currentColumn];
        }
        return column;
    }

    private boolean isAvailableInColumn(int currentRow, int currentColumn, int[][] data) {
        for (int i = 0; i < currentRow; i++) {
            if (data[i][currentColumn] == data[currentRow][currentColumn]) return false;
        }
        return true;
    }

    private boolean isAvailableInBlock(int row, int column, int squareRoot, int[][] data) {
        int block = findSquare(row, column, squareRoot);
        int rowStartBlock = (block / squareRoot) * squareRoot;
        int rowEndBlock = rowStartBlock + squareRoot;
        int columnStartBlock = (block % squareRoot) * squareRoot;
        int columnEndBlock = columnStartBlock + squareRoot;
        for (int r = rowStartBlock; r < rowEndBlock; r++) {
            for (int c = columnStartBlock; c < columnEndBlock; c++) {
                if (r != row && c != column) {
                    if (data[r][c] == data[row][column]) return false;
                }
            }
        }
        return true;
    }

    private int[] currentBlockState(int row, int column, int squareRoot, int[][] data) {
        int blockState[];
        List<Integer> list = new ArrayList<>();
        int block = findSquare(row, column, squareRoot);
        int rowStartBlock = (block / squareRoot) * squareRoot;
        int rowEndBlock = rowStartBlock + squareRoot;
        int columnStartBlock = (block % squareRoot) * squareRoot;
        int columnEndBlock = columnStartBlock + squareRoot;
        if (rowStartBlock == row && columnStartBlock == column) {
            blockState = new int[1];
            blockState[0] = 0;
        } else {
            for (int r = rowStartBlock; r < rowEndBlock; r++) {
                for (int c = columnStartBlock; c < columnEndBlock; c++) {
                    if (data[r][c] != 0) {
                        list.add(data[r][c]);
                    } else {
                        break;
                    }
                }
            }
            blockState = new int[list.size()];
            for (int m = 0; m < list.size(); m++) {
                blockState[m] = list.get(m);
            }
        }
        return blockState;

    }

    private int findSquare(int row, int column, int squareRoot) {
        return (((row / squareRoot) * squareRoot) + (column / squareRoot));
    }

    public int[][] anotherInit(int SquareRoot) {
        int secondCounter = 0;
        int[][] data = new int[SquareRoot * SquareRoot][SquareRoot * SquareRoot];
        Log.i("Start", "time");
        for (int x = 0; x < (SquareRoot * SquareRoot); x++) {
            List<Integer> testarray = fillList(SquareRoot);

            for (int y = 0; y < (SquareRoot * SquareRoot); y++) {
                List<Integer> fittingArray = new ArrayList<>();
                boolean ttt;

                for (int z = 0; z < testarray.size(); z++) {
                    data[x][y] = testarray.get(z);

                    boolean test1 = isAvailableInRow(x, y, data);
                    boolean test2 = isAvailableInColumn(x, y, data);
                    boolean test3 = isAvailableInBlock(x, y, SquareRoot, data);


                    ttt = (test1 && test2 && test3);

                    if (ttt) {
                        fittingArray.add(testarray.get(z));
                    }

                    data[x][y] = 0;
                }

                if (fittingArray.size() != 0) {
                    data[x][y] = getRandom(fittingArray);
                } else {
                    secondCounter = secondCounter + 1;
                    if (secondCounter < x) {
                        for (int p = x - secondCounter; p < (SquareRoot * SquareRoot); p++) {
                            for (int g = 0; g < SquareRoot * SquareRoot; g++) {
                                data[p][g] = 0;
                            }
                        }
                        if (x != 0) {
                            y = -1;
                            x = x - secondCounter;
                        } else {
                            x = 0;
                            y = -1;
                        }
                    } else {
                        for (int p = 0; p < (SquareRoot * SquareRoot); p++) {
                            for (int g = 0; g < SquareRoot * SquareRoot; g++) {
                                data[p][g] = 0;
                            }
                        }
                        secondCounter = 0;
                        x = 0;
                        y = -1;
                    }
                }
            }
        }
        return data;
    }

    /* Union of multiple arrays */
    private static int[] unionArrays(int[]... arrays) {
        int maxSize = 0;
        int counter = 0;

        for (int[] array : arrays) maxSize += array.length;
        int[] accumulator = new int[maxSize];

        for (int[] array : arrays)
            for (int i : array)
                if (!isDuplicated(accumulator, counter, i))
                    accumulator[counter++] = i;

        int[] result = new int[counter];
        System.arraycopy(accumulator, 0, result, 0, counter);

        return result;
    }

    private static boolean isDuplicated(int[] array, int counter, int value) {
        for (int i = 0; i < counter; i++) if (array[i] == value) return true;
        return false;
    }

    public boolean solve(int[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board.length; column++) {
                if (board[row][column] == 0) {
                    for (int k = 1; k <= board.length; k++) {
                        board[row][column] = k;
                        if (isValid(board, row, column) && solve(board)) {
                            return true;
                        }
                        board[row][column] = 0;
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(int[][] board, int row, int column) {
        return (rowConstraint(board, row)
                && columnConstraint(board, column)
                && subsectionConstraint(board, row, column));
    }

    private boolean rowConstraint(int[][] board, int row) {
        boolean[] constraint = new boolean[board.length];
        return IntStream.range(0, board.length)
                .allMatch(column -> checkConstraint(board, row, constraint, column));
    }

    private boolean columnConstraint(int[][] board, int column) {
        boolean[] constraint = new boolean[board.length];
        return IntStream.range(0, board.length)
                .allMatch(row -> checkConstraint(board, row, constraint, column));
    }

    private boolean subsectionConstraint(int[][] board, int row, int column) {
        boolean[] constraint = new boolean[board.length];
        double squared = Math.sqrt((double) board.length);
        int subsectionRowStart = (row / (int) squared) * (int) squared;
        int subsectionRowEnd = subsectionRowStart + (int) squared;

        int subsectionColumnStart = (column / (int) squared) * (int) squared;
        int subsectionColumnEnd = subsectionColumnStart + (int) squared;

        for (int r = subsectionRowStart; r < subsectionRowEnd; r++) {
            for (int c = subsectionColumnStart; c < subsectionColumnEnd; c++) {
                if (!checkConstraint(board, r, constraint, c)) return false;
            }
        }
        return true;
    }

    private boolean checkConstraint(
            int[][] board,
            int row,
            boolean[] constraint,
            int column) {
        if (board[row][column] != 0) {
            if (!constraint[board[row][column] - 1]) {
                constraint[board[row][column] - 1] = true;
            } else {
                return false;
            }
        }
        return true;
    }


    public int[][] hideFromBlock(int difficulty, int squareRoot, int[][] data) {

        for (int i = 0; i < (squareRoot * squareRoot); i++) {
            int rowStartBlock = (i / squareRoot) * squareRoot;
            int columnStartBlock = (i % squareRoot) * squareRoot;
            int squares;
            switch (difficulty) {
                case 1:
                    squares = 4;
                    break;
                case 2:
                    squares = 5;
                    break;
                case 3:
                    squares = 6;
                    break;
                default:
                    squares = 4;
                    break;
            }
            for (int x = 0; x < squares; x++) {
                Random generator = new Random();
                int randomx;
                int randomy;
                boolean repeat;
                do {
                    randomx = generator.nextInt(squareRoot) + rowStartBlock;
                    randomy = generator.nextInt(squareRoot) + columnStartBlock;
                    if (data[randomx][randomy] != 0) {
                        repeat = false;
                        data[randomx][randomy] = 0;
                    } else {
                        repeat = true;
                    }
                }
                while (repeat);
            }
        }
        return data;
    }
}
