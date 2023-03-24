package com.example.demo;

import java.util.Arrays;
import java.util.Scanner;

public class Puzzle {
    public static final int MATRIX_SIZE = 9;
    public static final int SUB_MATRIX_SIZE = 3;

    public Puzzle() {
    }

    public int[][] puzzleSolver(String input) {


        int[][] matrix = convertMatrix(input);

        if (solve(matrix)) {
            return matrix;
        } else {
            return null;
        }
    }


    private static boolean solve(int[][] matrix) {
        for (int row = 0; row < MATRIX_SIZE; row++) {
            for (int col = 0; col < MATRIX_SIZE; col++) {
                if (matrix[row][col] == 0) {
                    for (int num = 1; num <= MATRIX_SIZE; num++) {
                        if (isNotDuplicateInRow(matrix, num, row)
                                && isNotDuplicateInCol(matrix, num, col)
                                && isNotDuplicatedInSubMatrix(matrix, num, row, col)) {

                            matrix[row][col] = num;

                            if(solve(matrix)){
                                return true;
                            } else {
                                matrix[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static int[][] convertMatrix(String input) {
        Scanner scanner = new Scanner(input);
        String line = scanner.nextLine();
        int[][] matrix = new int[MATRIX_SIZE][MATRIX_SIZE];
        int row = 0;

        while (scanner.hasNext()) {

            boolean addedLineToMatrix = false;
            line = line.replaceAll("[^0-9]+", "");
            if (line.length() == 9) {
                int[] currentIntArr = Arrays.stream(line.split(""))
                        .mapToInt(Integer::parseInt).toArray();
                matrix[row] = currentIntArr;
                row++;
            }
            line = scanner.nextLine();
        }

        row = 0;
        return matrix;
    }

    private static boolean isNotDuplicateInRow(int[][] matrix, int num, int row) {
        for (int i = 0; i < MATRIX_SIZE; i++) {
            if (matrix[row][i] == num) {
                return false;
            }
        }
        return true;
    }


    private static boolean isNotDuplicateInCol(int[][] matrix, int num, int col) {
        for (int i = 0; i < MATRIX_SIZE; i++) {
            if (matrix[i][col] == num) {
                return false;
            }
        }
        return true;
    }

    private static boolean isNotDuplicatedInSubMatrix(int[][] matrix, int num, int row, int col) {
        int zeroRowInSubMatrix = row - row % SUB_MATRIX_SIZE;
        int zeroColInSubMatrix = col - col % SUB_MATRIX_SIZE;
        for (int i = zeroRowInSubMatrix; i < zeroRowInSubMatrix + SUB_MATRIX_SIZE; i++) {
            for (int j = zeroColInSubMatrix; j < zeroColInSubMatrix + SUB_MATRIX_SIZE; j++) {
                if (matrix[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }
}
