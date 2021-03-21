package com.epam.rd.autotasks;

class Spiral {
    static int[][] spiral(int rows, int columns) {
        int total = rows * columns;
        int[][] answer = new int[rows][columns];
        int i = 0;
        int j = 0;
        int dir[] = { 0, 0, 0, 1 };
        int rowCount = rows - 1;
        int colCount = columns;
        int flag = 0;
        while (flag < total) {
            if (dir[0] == 1 || dir[1] == 1) {
                for (int t = 0; t < rowCount; t++) {
                    flag++;
                    answer[i][j] = flag;
                    if (dir[0] == 1) {
                        i--;
                    }
                    if (dir[1] == 1) {
                        i++;
                    }
                }
                rowCount--;
                if (dir[0] == 1) {
                    dir[0] = 0;
                    dir[3] = 1;
                    i++;
                    j++;
                }
                if (dir[1] == 1) {
                    dir[1] = 0;
                    dir[2] = 1;
                    i--;
                    j--;
                }
            }

            if (dir[2] == 1 || dir[3] == 1) {
                for (int t = 0; t < colCount; t++) {
                    flag++;
                    answer[i][j] = flag;
                    if (dir[2] == 1) {
                        j--;
                    }
                    if (dir[3] == 1) {
                        j++;
                    }
                }
                colCount--;

                if (dir[2] == 1) {
                    dir[2] = 0;
                    dir[0] = 1;
                    j++;
                    i--;
                }
                if (dir[3] == 1) {
                    dir[3] = 0;
                    dir[1] = 1;
                    j--;
                    i++;
                }
            }
        }
        return answer.clone();
    }
}
