package game.sudoku.services;

public interface SetupTableService {
    int[][] hideFromBlock(int difficulty, int squareRoot, int[][] data);

    boolean isSudokuSolvable(int[][] board);
}
