package recognition;

import java.util.Scanner;

public class TextUI {
    private int[][] weights = {{2, 1, 2}, {4, -4, 4}, {2, -1, 2}};
    private int bias = -5;
    private Scanner scn = new Scanner(System.in);
    
    public void start() {
        System.out.println("Input grid:");
        int[][] inputGrid = new int[3][3];
        for (int i = 0; i < 3; ++i) {
            String row = scn.nextLine();
            for (int j = 0; j < 3; ++j) {
                if (row.charAt(j) == 'X') {
                    inputGrid[i][j] = weights[i][j];
                }
            }
        }
        int digit = calcDigit(inputGrid);
        System.out.println("The number is " + (digit > 0 ? "0" : "1"));
    }
    
    private int calcDigit(int[][] grid) {
        int sum = bias;
        for (int[] row : grid) {
            for (int num : row) {
                sum += num;
            }
        }
        return sum;
    }
}
