/**
 * Write a description of class MathSolver here.
 *
 * @author (Mohamad Chaker)
 * @version (2019-06-18)
 */

import java.io.*;
import java.lang.*;

public class MathSolver
{
    BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
    int choice = 1, tempChoice;

    public MathSolver() throws IOException
    {

        while (choice != -1)
        {
            System.out.println("Notice: This program is a collection of processes with discrete mathematical functions.");
            System.out.println("Some of the processes may read from FILE. Please read the corresponding example text file as to how to structure a FILE to its corresponding process.");
            System.out.println("Chose an option:");
            System.out.println("-1. Quit");
            System.out.println("0. Calculate roots of a quadratic equation");
            System.out.println("1. Calculate the distance between points (x_1,y_1,z_1) and (x_2,y_2,z_2) on a Cartisean plane.");
            System.out.println("2. Sine Law calculator.");
            choice = Integer.parseInt(keyboard.readLine());
            System.out.println("");

            if (choice == -1)
                System.exit(0);

            else if (choice == 0)
            {
                // This holds the variables a, b, and c of a quadratic equation as doubles.
                double [] quadratic = new double [3];

                // This will hold the roots of a quadratic equation.
                double [] roots = new double [2];          

                System.out.println("Chose an option:");
                System.out.println("1. Read from System.in");
                System.out.println("2. Read from FILE");
                tempChoice = Integer.parseInt(keyboard.readLine());
                System.out.println("");

                if (tempChoice == 1)
                {
                    System.out.println("Enter a:");
                    quadratic [0] = Double.parseDouble(keyboard.readLine());
                    System.out.println("");

                    System.out.println("Enter b:");
                    quadratic [1] = Double.parseDouble(keyboard.readLine());
                    System.out.println("");

                    System.out.println("Enter c:");                
                    quadratic [2] = Double.parseDouble(keyboard.readLine());
                    System.out.println("");

                    roots = quadraticFormula(quadratic);

                    // quadraticFormula will set both values to 0 if there are no roots.
                    if (roots [0] == 0 && roots [1] == 0)
                        System.out.println("The quadratic equation y = " + quadratic [0] + "x^2 + " + quadratic [1] + "x + " + quadratic [2] + " has no real roots.");
                    else
                        System.out.println("The roots of the quadratic equation y = " + quadratic [0] + "x^2 + " + quadratic [1] + "x + " + quadratic [2] + " is " + roots [0] + " and " + roots [1]);
                }

                else if (tempChoice == 2)
                {
                    // Open quadraticSolver.txt for reading.
                    BufferedReader br = new BufferedReader(new FileReader("quadraticSolver.txt"));                

                    //System.out.println("Enter the number of quadratic equations you wish to solve:");

                    // This will determine how many times the loop will repeat i.e how many quadratic equations the user wishes to solve.
                    //int numberToSolve = Integer.parseInt(keyboard.readLine());

                    // This will hold the variables of a quadratic equation seperated by spaces.
                    String abcString;

                    while (br.ready())
                    {
                        abcString = br.readLine();

                        // splitQuadratic will hold the split abcString. Example, string "a b c" becomes three strings "a" "b" and "c", and is put in an array.
                        String [] splitQuadratic = abcString.split(" ");

                        // Convert each string into a double and put it in the quadratic array, particulary because that is what quadraticSolver accepts.
                        quadratic [0] = Double.parseDouble(splitQuadratic [0]); 
                        quadratic [1] = Double.parseDouble(splitQuadratic [1]);                     
                        quadratic [2] = Double.parseDouble(splitQuadratic [2]); 

                        // Read quadraticFormula documentation.
                        roots = quadraticFormula(quadratic);

                        // If the returned roots are equal to zero, the quadratic equation has no real roots. Tell the user.
                        if (roots [0] == 0 && roots [1] == 0)
                        {
                            System.out.println("The quadratic equation y = " + quadratic [0] + "x^2 + " + quadratic [1] + "x + " + quadratic [2] + " has no real roots.");
                        }

                        // If the returned roots are equal (other than the case of them both being 0), the equation has one real root. Tell the user.
                        else if (roots [0] == roots [1])
                        {
                            System.out.println("The root of the quadratic equation y = " + quadratic [0] + "x^2 + " + quadratic [1] + "x + " + quadratic [2] + " is " + roots [0]);
                        }

                        // If the returned roots are both different, the equation has two real roots. Tell the user.
                        else 
                        {
                            System.out.println("The roots of the quadratic equation y = " + quadratic [0] + "x^2 + " + quadratic [1] + "x + " + quadratic [2] + " is " + roots [0] + " and " + roots [1]);
                        }
                    }
                }
            }

            else if (choice == 1)
            {
                // In this case, System.in is input from keyboard.
                System.out.println("1. Read from System.in");
                System.out.println("2. Read from FILE");
                System.out.println("");

                tempChoice = Integer.parseInt(keyboard.readLine());

                if (tempChoice == 1)
                {
                    // Coordinate for point A and point B.
                    double x_A, y_A, z_A;
                    double x_B, y_B, z_B;

                    // Labels for point A and point B.
                    String A, B;

                    // Holds the distance between the two points
                    double distance;

                    // Let user input the three dimensional coordinates of point A and B.
                    System.out.println("Label point A:");
                    A = keyboard.readLine().substring(0);

                    System.out.println("3D Coordinates of point A take the form of (x_A, y_A, z_A).");

                    System.out.println("Enter x_A:");
                    x_A = Double.parseDouble(keyboard.readLine());

                    System.out.println("Enter y_A:");
                    y_A = Double.parseDouble(keyboard.readLine());

                    System.out.println("Enter z_A:");
                    z_A = Double.parseDouble(keyboard.readLine());

                    System.out.println("Label point B:");                
                    B = keyboard.readLine().substring(0);

                    System.out.println("3D Coordinates of point B take the form of (x_B, y_B, z_B).");

                    System.out.println("Enter x_B:");
                    x_B = Double.parseDouble(keyboard.readLine());

                    System.out.println("Enter y_B:");
                    y_B = Double.parseDouble(keyboard.readLine());

                    System.out.println("Enter z_B:");
                    z_B = Double.parseDouble(keyboard.readLine());

                    // threeDimensionalDistanceCalculator will calculate the distance between the two points.
                    distance = threeDimensionalDistanceCalculator(x_A, y_A, z_A, x_B, y_B, z_B);

                    // Print out the distance between the two points.
                    System.out.println("The distance between " + A + " (" + x_A + ", " + y_A + ", " + z_A + ") and " + B + " (" + x_B + ", " + y_B + ", " + z_A + ") is " + distance);
                    System.out.println("");
                }

                else if (tempChoice == 2)
                {
                    // Open a BufferedReader to read from file threeDimensionalDistanceCalculator.txt
                    BufferedReader br = new BufferedReader(new FileReader("threeDimensionalDistanceCalculator.txt"));

                    // This will hold each line in the file. 
                    String dataAsString;

                    // Let the user decide how many distances to be calculated. The number of point pairs are equal to the number of distances to be calculated.
                    System.out.println("How many distances would you like to calculate?");
                    int numberOfDistances = Integer.parseInt(keyboard.readLine());
                    int numberOfPointPairs = numberOfDistances;

                    // The arrays for the points. (x_A [0], y_A [0], z_A [0]) is an ordered pair.
                    double [] x_A = new double [numberOfPointPairs];
                    double [] y_A = new double [numberOfPointPairs];
                    double [] z_A = new double [numberOfPointPairs];

                    double [] x_B = new double [numberOfPointPairs];
                    double [] y_B = new double [numberOfPointPairs];
                    double [] z_B = new double [numberOfPointPairs];                    

                    // The arrays for the labels of the points. A [2] would be the label for point (x_A [2], y_A [2], z_A [2]).
                    String [] A = new String [numberOfPointPairs]; 
                    String [] B = new String [numberOfPointPairs];                   

                    // The arrays for the distances between the points.
                    double [] distances = new double [numberOfDistances];

                    for (int x = 0; x < numberOfPointPairs; x++)
                    {
                        // Read line from file.
                        dataAsString = br.readLine();

                        // Split String into String [] based on spaces. This is essentially what is happening:
                        // 
                        // If we start with:
                        // 
                        // String a = "Hello! How are you?";
                        //
                        // split creates:
                        //
                        // String [] b = new String [4];
                        // b[0] = "Hello!";
                        // b[1] = "How";
                        // b[2] = "are";
                        // b[3] = "you?";

                        String [] dataAsStrings = dataAsString.split(" ");

                        // The first group of characters (all characters until the next space in this case) should be a label for the first point.
                        A [x] = dataAsStrings [0];

                        // The second, third, and fourth group of characters should be the coordinates of the first point.
                        x_A [x] = Double.parseDouble(dataAsStrings [1]);
                        y_A [x] = Double.parseDouble(dataAsStrings [2]);
                        z_A [x] = Double.parseDouble(dataAsStrings [3]);

                        // The sixth group of characters should be a label for the second point.
                        B [x] = dataAsStrings [5];

                        // The seventh, eighth, and nineth group of characters should be the coordinates of the second point.
                        x_B [x] = Double.parseDouble(dataAsStrings [6]);
                        y_B [x] = Double.parseDouble(dataAsStrings [7]);
                        z_B [x] = Double.parseDouble(dataAsStrings [8]);

                        // The distance between (x_A [x], y_A [x], z_A [x]) and (x_B [x], y_B [x], z_B [x]) will be stored in distances [x].
                        distances [x] = threeDimensionalDistanceCalculator(x_A [x], y_A [x], z_A [x], x_B [x], y_B [x], z_B [x]);
                    }

                    // Used for the sorting of distances.
                    int pass = numberOfPointPairs - 1;                    

                    // Let the user decide whether there should be sorting or not. Also, if the user decides to sort, let them choose the method of sorting.
                    System.out.println("0. No sorting");
                    System.out.println("1. Lowest to greatest");
                    System.out.println("2. Grewatest to lowest");
                    tempChoice = Integer.parseInt(keyboard.readLine());
                    System.out.println("");

                    // Do nothing if the user enters zero.
                    if (tempChoice == 0)
                    {
                    }

                    // Lowest to greatest. The method used is called Bubble Sort.
                    else if (tempChoice == 1)
                    {
                        double temp;
                        String temp2;

                        for (int y = 0; y < pass ; y++)
                        {
                            for (int x = 0; x < pass - y; x++)
                            {
                                if (distances[x] > distances[x + 1])
                                {
                                    // swap data
                                    temp = distances[x + 1];
                                    distances[x + 1] = distances[x];
                                    distances[x]= temp;

                                    temp2 = A[x + 1];
                                    A[x + 1] = A[x];
                                    A[x]= temp2;

                                    temp2 = B[x + 1];
                                    B[x + 1] = B[x];
                                    B[x]= temp2;
                                }
                            }
                        }
                    }

                    // Greatest to lowest. Bubble sort is again used.
                    else if (tempChoice == 2)
                    {
                        double temp;
                        String temp2;

                        for (int y = 0; y < pass ; y++)
                        {
                            for (int x = 0; x < pass - y; x++)
                            {
                                if (distances[x] < distances[x + 1])
                                {
                                    // swap data
                                    temp = distances[x + 1];
                                    distances[x + 1] = distances[x];
                                    distances[x]= temp;

                                    temp2 = A[x + 1];
                                    A[x + 1] = A[x];
                                    A[x]= temp2;

                                    temp2 = B[x + 1];
                                    B[x + 1] = B[x];
                                    B[x]= temp2;
                                }
                            }
                        }
                    }

                    // Print information to user.
                    for (int x = 0; x < numberOfPointPairs; x++)
                    {
                        System.out.println("The distance between " + A [x] + " (" + x_A [x] + ", " + y_A [x] + ", " + z_A [x] + ") and " + B [x] + " (" + x_B [x] + ", " + y_B [x] + ", " + z_A [x] + ") is " + distances [x]);
                    }
                }
            }

            else if (choice == 2)
            {                
                System.out.println("0. Read from System.in");
                tempChoice = Integer.parseInt(keyboard.readLine());
                System.out.println("");

                if (tempChoice == 0)
                {
                    System.out.println("In any given triangle:");
                    System.out.println("'a' is a side & 'A' is its corresponding angle.");
                    System.out.println("'b' is a different side & 'B' is its corresponding angle.");

                    System.out.println("0. Compute unknown side");
                    System.out.println("1. Compute unknown angle");
                    tempChoice = Integer.parseInt(keyboard.readLine());
                    System.out.println("");

                    // Explained on lines 324-326.
                    double A, a, b, B;

                    if (tempChoice == 0)
                    {
                        // The user enters A, b, and B. The sineLaw method computes a.
                        System.out.println("0. Angles measured in degrees");
                        System.out.println("1. Angles measured in radians");
                        tempChoice = Integer.parseInt(keyboard.readLine());

                        System.out.println("Solving for a:");
                        System.out.println("");

                        System.out.println("Enter A:");
                        A = Double.parseDouble(keyboard.readLine());
                        System.out.println("");

                        System.out.println("Enter b:");
                        b = Double.parseDouble(keyboard.readLine());
                        System.out.println("");

                        System.out.println("Enter B:");
                        B = Double.parseDouble(keyboard.readLine());
                        System.out.println("");

                        // Convert from degrees to radians. In the Java libraries, trigonometric methods expect input in radians.
                        // All angles must have the same units. Otherwise, the answer would be computed wrong / the triangle wouldn't exist.
                        if (tempChoice == 0)
                        {
                            A = Math.toRadians(A);
                            B = Math.toRadians(B); 
                        }

                        a = sineLaw(0, 0, A, b, B);

                        // Print information.
                        System.out.println("a has a sidelength of " + a);
                        System.out.println("");
                    }

                    else
                    {         
                        // The user enters a, b, and B. The sineLaw method computes A.
                        System.out.println("0. Angles measured in degrees");
                        System.out.println("1. Angles measured in radians");
                        tempChoice = Integer.parseInt(keyboard.readLine());

                        System.out.println("Solving for A:");
                        System.out.println("");

                        System.out.println("Enter a:");
                        a = Double.parseDouble(keyboard.readLine());
                        System.out.println("");

                        System.out.println("Enter b:");
                        b = Double.parseDouble(keyboard.readLine());
                        System.out.println("");

                        System.out.println("Enter B:");
                        B = Double.parseDouble(keyboard.readLine());
                        System.out.println("");

                        // If the user chose degrees, convert degrees to radians. The trigonometric methods in the Java libraries expect input in radians.
                        if (tempChoice == 0)
                        {
                            B = Math.toRadians(B); 
                        }

                        A = sineLaw(1, a, 0, b, B);

                        // If the user chose degrees, convert the computed angle back to degrees.
                        if (tempChoice == 0)
                        {
                            A = Math.toDegrees(A);
                        }

                        // Print information
                        System.out.println("A has an angle of " + A + "°");
                        System.out.println("");
                    }
                }
            }
        }
    }

    /**
     * This method computes the roots of a quadratic equation using the quadratic formula.
     *
     * @param  quadratic []  a double array of size three that contains a, b, and c of the quadratic equation.
     * @return    a double array of size 2 that contains the roots of the quadratic equation.
     */
    public double[] quadraticFormula(double quadratic[])
    {
        // A mathematical term in the quadratic formula.        
        double discriminant;

        // This will be returned by this method.
        double [] roots = new double [2];

        // We are computing this seperately for a particular reason. Read next comment.
        discriminant = (quadratic [1] * quadratic [1]) - (4 * quadratic [0] * quadratic [2]);

        // In the quadratic equation, there is a term in the numerator sqrt(b^2 - 4ac). If b^2 - 4ac < 0, then it cannot be square rooted.
        // This means that the quadratic equation has no roots. Set both roots to zero as a way of informing the user.
        if (discriminant < 0)
        {
            roots[0] = 0;
            roots[1] = 0;
        }

        // Otherwise, if discriminant is greater than zero, the roots can be computed and are computed in the following lines.
        else
        {
            roots[0] = (-quadratic[1] + discriminant) / (2 * quadratic[0]);
            roots[1] = (-quadratic[1] - discriminant) / (2 * quadratic[0]);
        }

        return roots; 
    }

    /**
     * This method calculates the distance between two three dimensional ordered pairs.
     *
     * @param  double x_A the location of a point A in dimension x.
     * @param  double y_A the location of a point A in dimension y.
     * @param  double z_A the location of a point A in dimension z.
     * @param  double x_B the location of a point B in dimension x.
     * @param  double y_B the location of a point B in dimension y.
     * @param  double z_B the location of a point B in dimension z.
     * @return    a double array of size 2 that contains the roots of the quadratic equation.
     */
    public double threeDimensionalDistanceCalculator(double x_A, double y_A, double z_A, double x_B, double y_B, double z_B)
    {
        // Distance equation. 
        // If the user passes zero to all the z dimensions, it becomes a 2D calculator.
        // If the user passes zero to all the y and z dimensions, it's basically a subtractor between two integers.
        double distance = Math.sqrt(((x_B - x_A) * (x_B - x_A)) + ((y_B - y_A) * (y_B - y_A)) + ((z_B - z_A) * (z_B - z_A)));
        return distance;
    }

    /**
     * This method solves for a side / angle in a triangle using Sine Law:
     * 
     * (a / sinA) = (b / sinB) = (c / sinC) = ...
     *
     * @param  int SideOrAngle determines whether the method computers & returns an angle / side.
     * @param  double sideA a side in a triangle
     * @param  double angleA an angle corresponding to sideA
     * @param  double sideB a different side in a triangle
     * @param  double angleB an angle corresponding to sideB
     * @return    a double array of size 2 that contains the roots of the quadratic equation.
     */
    public double sineLaw (int sideOrAngle, double sideA, double angleA, double sideB, double angleB)
    {
        // If the user wishes to solve for a side...
        // This is Sine Law re-arranged for side A.
        if (sideOrAngle == 0)
        {
            sideA = ((sideB) / (Math.sin(angleB))) * (Math.sin(angleA));
            return sideA;
        }

        // If the user wishes to solve for an angle...
        // This is Sine Law re-arranged for angle A.
        else if (sideOrAngle == 1)
        {
            angleA = Math.asin( ((Math.sin(angleB)) / (sideB)) * (sideA));
            return angleA;
        }

        // If sideOrAngle is neither 0 / 1 i.e the user passed a parameter that the method doesn't "support".
        else
        {
            return 0;
        }
    }
}
