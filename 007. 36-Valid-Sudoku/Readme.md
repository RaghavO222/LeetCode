# Valid Sudoku

This repository contains a highly efficient, single-pass Java solution for validating a 9x9 Sudoku board.

## Problem Overview

The goal is to determine if a `9 x 9` Sudoku board is valid. Only the filled cells need to be validated according to three standard Sudoku rules:
* Each row must contain the digits `1-9` without repetition.
* Each column must contain the digits `1-9` without repetition.
* Each of the nine `3 x 3` sub-boxes of the grid must contain the digits `1-9` without repetition.

> **Note:** A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.

## Intuition & Approach

The solution uses an array-based hashing technique to keep track of the numbers already seen in each row, column, and 3x3 sub-box. 

Instead of using standard HashSets (which carry memory and lookup overhead), this approach uses three 2D boolean arrays to act as fast, direct-access maps:
* `rows[9][9]`
* `cols[9][9]`
* `boxes[9][9]`

### Step-by-Step Logic
For every cell `(i, j)` on the board:
1. **Skip Empties:** If the cell contains `'.'`, we simply continue to the next cell.
2. **Map the Value:** We convert the character `1-9` into a zero-based integer index (`0-8`) using `num = board[i][j] - '1'`.
3. **Locate the Sub-Box:** We map the 2D grid coordinates `(i, j)` to a 1D index (`0-8`) representing which of the nine 3x3 grids the cell falls into. We use the formula: `boxIdx = (i / 3) * 3 + (j / 3)`.
4. **Collision Check:** We check if the number has already been marked `true` in the current row, column, or box. If it has, we immediately return `false`.
5. **Record the Number:** If no collisions are found, we mark the number as seen (`true`) in the respective row, column, and box.

## Complexity Analysis

* **Time Complexity:** **O(1)**. Because the board size is strictly fixed at 9x9, the nested loops will always run exactly 81 times. 
* **Space Complexity:** **O(1)**. The memory used by the three 9x9 boolean arrays is constant (243 boolean values in total), regardless of how populated the board is. 

## Code

```java
class Solution {
    public boolean isValidSudoku(char[][] board) {

        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] boxes = new boolean[9][9];

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board[i][j] != '.'){
                    int num = board[i][j] - '1';
                    int boxIdx = (i/3)*3 + (j/3);

                    if(rows[i][num] || cols[j][num] || boxes[boxIdx][num]){
                        return false;
                    }

                    rows[i][num] = cols[j][num] = boxes[boxIdx][num] = true;
                }
            }
        }
        return true;
    }
}
