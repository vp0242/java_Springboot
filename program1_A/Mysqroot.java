package program1_A;

public class Mysqroot {

    // main method to starting execution of java program
    public static void main(String[] args) {
        // float number = readInput("Enter the numbers:");
        if (args.length <=0) {
            System.out.println("Number not provided...");
            return;
        }
        // reading input as command line argument
        try {
            String input = args[0];
            // parsing string to float
            double number = Double.parseDouble(input);
            // initializing new object of sqroot class
            Sqroot sq = new Sqroot(number);
            sq.calculateSqroot();
        } catch (Exception e) {
            System.out.println("Error occured While parsing string argument to float datatype.");
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
            if (number - newNumber <= 0.001) {
                break;
            }
            initialNumber = newNumber;
        }
        System.out.printf(" %.4f %.4f\n", containedNum, newNumber);

    }
}