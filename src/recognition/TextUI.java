package recognition;

import java.util.Scanner;

public class TextUI {
    private final int[][][] weights = {
            {{1, 1, 1}, {1, -1, 1}, {1, -1, 1}, {1, -1, 1}, {1, 1, 1}},
            {{-1, 1, -1}, {-1, 1, -1}, {-1, 1, -1}, {-1, 1, -1}, {-1, 1, -1},},
            {{1, 1, 1}, {-1, -1, 1}, {1, 1, 1}, {1, -1, -1}, {1, 1, 1}},
            {{1, 1, 1}, {-1, -1, 1}, {1, 1, 1}, {-1, -1, 1}, {1, 1, 1}},
            {{1, -1, 1}, {1, -1, 1}, {1, 1, 1}, {-1, -1, 1}, {-1, -1, 1}},
            {{1, 1, 1}, {1, -1, -1}, {1, 1, 1}, {-1, -1, 1}, {1, 1, 1}},
            {{1, 1, 1}, {1, -1, -1}, {1, 1, 1}, {1, -1, 1}, {1, 1, 1}},
            {{1, 1, 1}, {-1, -1, 1}, {-1, -1, 1}, {-1, -1, 1}, {-1, -1, 1}},
            {{1, 1, 1}, {1, -1, 1}, {1, 1, 1}, {1, -1, 1}, {1, 1, 1}},
            {{1, 1, 1}, {1, -1, 1}, {1, 1, 1}, {-1, -1, 1}, {1, 1, 1}},
    };
    private final int[] biases = {-1, 6, 1, 0, 2, 0, -1, 3, -2, -1};
    private final Scanner scn = new Scanner(System.in);
    
    public void start() {
        System.out.println("Input grid:");
        int[][] inputGrid = new int[5][3];
        for (int row = 0; row < 5; ++row) {
            String inRow = scn.nextLine();
            for (int col = 0; col < 3; ++col) {
                if (inRow.charAt(col) == 'X' || inRow.charAt(col) == 'x') {
                    inputGrid[row][col] = 1;
                }
            }
        }
        int digit = calcDigit(inputGrid);
        System.out.println("The number is " + digit);
    }
    
    private int calcDigit(int[][] grid) {
        int[] sums = new int[10];
        for (int i = 0; i < 10; ++i) {
            for (int row = 0; row < 5; ++row) {
                for (int col = 0; col < 3; ++col) {
                    sums[i] += grid[row][col] * weights[i][row][col];
                }
            }
            sums[i] += biases[i];
        }
        int digit = 0;
        int sum = sums[0];
        for (int i = 1; i < sums.length; ++i) {
            if (sums[i] > sum) {
                sum = sums[i];
                digit = i;
            }
        }
        return digit;
    }
}
