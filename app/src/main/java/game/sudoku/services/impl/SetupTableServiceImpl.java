package game.sudoku.services.impl;

import com.annimon.stream.IntStream;

import java.util.Random;

import game.sudoku.services.SetupTableService;

public class SetupTableServiceImpl implements SetupTableService {

    @Override
    public boolean isSudokuSolvable(int[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board.length; column++) {
                if (board[row][column] == 0) {
                    for (int k = 1; k <= board.length; k++) {
                        board[row][column] = k;
                        if (isValid(board, row, column) && isSudokuSolvable(board)) {
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

    @Override
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
