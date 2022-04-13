package cinema;

import java.util.Scanner;

public class Cinema {

    public static String[][] createSeats(int row, int col) {
        String[][] seatsMatrix = new String[row + 1][col + 1];
        for (int i = 0; i <= row; i++) {
            if (i == 0) {
                for (int j = 0; j <= col; j++) {
                    if (j == 0) {
                        seatsMatrix[i][j] = " ";
                        continue;
                    }
                    seatsMatrix[i][j] = String.valueOf(j);
                }
            } else {
                for (int j = 0; j <= col; j++) {
                    if (j == 0) {
                        seatsMatrix[i][j] = String.valueOf(i);
                    } else {
                        seatsMatrix[i][j] = "S";
                    }
                }
            }
        }
        return seatsMatrix;
    }

    public static void showSeats(String[][] seatsMatrix) {
        System.out.println();
        System.out.println("Cinema:");
        for (String[] rows : seatsMatrix)
        {
            for (String value : rows)
            {
                System.out.print(value + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void chooseSeats(String[][] seatsMatrix, int row, int seats) {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("Enter a row number:");
        int rowNumber = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatNumber = scanner.nextInt();
        int price = 0;
        int totalSeats = row * seats;
        int frontHalf = row / 2;
        int secondHalf = row - frontHalf;

        if (rowNumber > row || seatNumber > seats) {
            System.out.println("\nWrong input!");
            chooseSeats(seatsMatrix, row, seats);
        } else if ("B".equals(seatsMatrix[rowNumber][seatNumber])) {
            System.out.println("\nThat ticket has already been purchased!");
            chooseSeats(seatsMatrix, row, seats);
        } else {
            if (totalSeats <= 60) {
                price = 10;
            } else if (totalSeats > 60 && rowNumber <= frontHalf) {
                price = 10;
            } else if (totalSeats > 60 && rowNumber >= secondHalf) {
                price = 8;
            }
            System.out.println();
            System.out.println("Ticket price: $" + price);
            System.out.println();

            seatsMatrix[rowNumber][seatNumber] = "B";
        }
    }

    public static void showStatistics(int row, int seats, String[][] seatsMatrix) {
        int totalSeats = row * seats;
        int numberOfPurchasedTickets = 0;
        int currentIncome = 0;
        int totalIncome = 0;
        int frontHalf = row / 2;
        int secondHalf = row - frontHalf;

        for (int i = 0; i < row + 1; i++) {
            for (int j = 0; j < seats + 1; j++) {
                if ("B".equals(seatsMatrix[i][j])) {
                    if (totalSeats <= 60 || (totalSeats > 60 && i <= frontHalf)) {
                        currentIncome += 10;
                    }  else if (totalSeats > 60 && i >= secondHalf) {
                        currentIncome += 8;
                    }
                    numberOfPurchasedTickets++;
                }
            }
        }

        double boughtTicketsPercentage = (double) numberOfPurchasedTickets / totalSeats * 100;

        frontHalf *= seats;
        secondHalf = totalSeats - frontHalf;
        if (totalSeats <= 60) {
            totalIncome = totalSeats * 10;
        } else if (totalSeats > 60) {
            totalIncome = frontHalf * 10 + secondHalf * 8;
        }


        System.out.println();
        System.out.println("Number of purchased tickets: " + numberOfPurchasedTickets);
        System.out.printf("Percentage: %.2f%%%n", boughtTicketsPercentage);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int row = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        System.out.println();
        String[][] seatsMatrix = createSeats(row, seats);

        while (true) {
            System.out.println("1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit");
            int userChoice = scanner.nextInt();
            if (userChoice == 0) {
                break;
            }
            switch (userChoice) {
                case 1:
                    showSeats(seatsMatrix);
                    break;
                case 2:
                    chooseSeats(seatsMatrix, row, seats);
                    break;
                case 3:
                    showStatistics(row, seats, seatsMatrix);
                    break;
            }
        }
    }
}