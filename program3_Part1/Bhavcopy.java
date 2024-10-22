package program3_Part1;

import java.util.*;

public class Bhavcopy {

    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Bhavcopy bh = new Bhavcopy();

        while (true) {
            int choice = bh.getChoice();
            if (choice == 11) {
                System.out.println("Exiting program..\n");
                break;
            }
            if (choice == -1) {
                continue;
            }

            FileOperation file = new FileOperation(choice);
            file.startFileOperation();
        }
    }

    int getChoice() {
        while (true) {

            System.out.println("\nEnter your choice:");
            System.out.println("1.Get information about a particular symbol");
            System.out.println("2.Count the number of symbols for a given SERIES.");
            System.out.println("3.List all symbols where ((CLOSE - OPEN) / OPEN) > N%");
            System.out.println("4.List all symbols where ((HIGH - LOW) / LOW) > N%");
            System.out.println("5.Standard deviation for all symbols of a given series");
            System.out.println("6.Top N symbols having maximum gain");
            System.out.println("7.Bottom N symbols having lowest gain");
            System.out.println("8.Top N most traded (by volume) symbols");
            System.out.println("9.Bottom N least traded (by volume) symbols");
            System.out.println("10.Highest and lowest traded (by TOTRDVAL) for a given series");
            System.out.print("\nEnter Your Choice or Enter <Quit> to Exit Program: ");
            String userInput = sc.nextLine();

            if (userInput.equalsIgnoreCase("quit")) {
                return 11;
            }
            try {

                int choice = Integer.parseInt(userInput);
                if (choice >= 1 && choice <= 10) {
                    return choice;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and 9.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid Input. Please enter a valid number or 'Quit' to exit.");
            }
            return -1;
        }
    }
}
