package program1_B;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Mysqroot {

    // main method to starting execution of java program
    public static void main(String[] args) {
        String filepath =args[0];
         File file = new File(filepath);
         if (!file.exists()) {
            System.out.println("File Not Found!");
         }else{
            readFile(filepath);
         }
       
    }

    public static void readFile(String filepath) {
        try {
            // obj of bufferreader class for reading files in lines or chunks
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;

            // reading file till nest line is appear to be null
            while ((line = reader.readLine()) != null) {
                try {
                    
                    if (line.trim().isEmpty()) {
                     System.out.println("No Number is Provided :Please Give Proper Input Number...");
                     continue;
                    }
                    double number = Double.parseDouble(line);
                    Sqroot sq = new Sqroot(number);
                    sq.calculateSqroot();

                } catch (Exception e) {
                    System.out.println("Error occured While parsing string argument to Double datatype.");
                }
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Error occured While reading text file..");
        }

    }

}

class Sqroot {
    double number;

    // parameterized constructor of given class sqroot
    public Sqroot(double number) {
        this.number = number;
    }

    // validing to make sure number is a nonzero value
    public boolean isvalidNum() {
        if (number <= 0) {
            return false;
        }
        return true;
    }

    // calculating square root of given input number
    public void calculateSqroot() {
        // if given input number is invalid number ,it will exit program
        if (!isvalidNum()) {
            System.out.println("Incorrect Number: Please Enter a Non-Zero Number Again...");
            return;
        }
        // starting iteration with initial value 1
        double initialNumber = 1;
        double newNumber = 0;
        double containedNum = number;
        for (int index = 0; index <= 25; index++) {
            // newton's formula to fing out sqroot of any number
            newNumber = initialNumber - (initialNumber * initialNumber - number) / (2 * initialNumber);
            // if diff between two iterations is less than 0.001 then breaking for loop
            if (number - newNumber < 0.001) {
                break;
            }
            initialNumber = newNumber;
        }
        System.out.printf(" %.2f %.4f\n", containedNum, newNumber);

    }
}