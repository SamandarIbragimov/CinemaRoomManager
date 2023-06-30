package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {

    public static void printStat(int rows, int seats, char[][] grid){
        int count = 0,curIncome=0,totIncome;

        totIncome = rows * seats > 60? seats * (rows / 2 * 10 + (rows - rows / 2) * 8): rows * seats * 10;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                if(grid[i][j] == 'B'){
                    count++;
                    curIncome+=price(rows,seats,i+1);
                }
            }
        }
        String s = String.format("Percentage: %.2f",count*100.0/(rows*seats));
        System.out.println("Number of purchased tickets: "+count);
        System.out.println(s+"%\n");
        System.out.println("Current income: $"+curIncome);
        System.out.println("Total income: $"+totIncome);
    }

    public static void printGrid(int rows, int seats, char[][] grid){
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 1; i <= seats; i++) {

            System.out.print(" " + i);
        }

        System.out.println();
        for (int i = 0; i < rows; i++) {
            System.out.print(i+1);
            for (int j = 0; j < seats; j++) {
                System.out.print(" "+grid[i][j]);
            }
            System.out.println();
        }

    }

    public static int price(int rows, int seats, int row){
        int totalSeats = rows * seats;

        if(totalSeats > 60){
            return row <= rows/2? 10: 8;
        } else{
            return 10;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = sc.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int seats = sc.nextInt();

        char[][] grid = new char[rows][seats];

        for(int i=0;i<rows;i++){
            for(int j=0;j<seats;j++){
                grid[i][j] = 'S';
            }
        }
        String menu = "\n1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit";

        System.out.println(menu);
        while(true){
            int option = sc.nextInt();
            if(option == 1){
                printGrid(rows,seats,grid);
                System.out.println();
                System.out.println(menu);
            } else if(option == 2){
                while(true){
                    System.out.println();
                    System.out.println("Enter a row number:");
                    int row = sc.nextInt();

                    System.out.println("Enter a seat number in that row:");
                    int seat = sc.nextInt();

                    if(row<1||row>rows||seat<0||seat>seats)
                        System.out.println("Wrong input!");
                    else if (grid[row - 1][seat - 1] == 'B')
                        System.out.println("That ticket has already been purchased!");
                    else{
                        grid[row-1][seat-1] = 'B';
                        System.out.println("Ticket price: $"+ price(rows,seats,row));
                        System.out.println();
                        System.out.println(menu);
                        break;
                    }
                }
            } else if(option == 3){
                printStat(rows,seats,grid);
                System.out.println(menu);
            } else
                break;
        }

    }
}