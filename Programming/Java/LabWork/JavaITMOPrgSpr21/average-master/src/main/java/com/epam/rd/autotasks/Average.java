package com.epam.rd.autotasks;

import java.util.Scanner;

public class Average {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sum = 0;
        int count = 0;
        int num;
        while (true) {
            num = scanner.nextInt();
            if (num == 0)
                break;
            sum += num;
            count++;
        }
        System.out.println(sum / count);
        scanner.close();

    }

}