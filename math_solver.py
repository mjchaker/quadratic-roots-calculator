"""
MathSolver - A collection of processes with discrete mathematical functions.

@author: Mohamad Chaker
@version: 2019-06-18 (Converted to Python 2025-12-11)
"""

import math


class MathSolver:
    def __init__(self):
        self.choice = 1

    def run(self):
        """Main program loop"""
        while self.choice != -1:
            print("Notice: This program is a collection of processes with discrete mathematical functions.")
            print("Some of the processes may read from FILE. Please read the corresponding example text file as to how to structure a FILE to its corresponding process.")
            print("Chose an option:")
            print("-1. Quit")
            print("0. Calculate roots of a quadratic equation")
            print("1. Calculate the distance between points (x_1,y_1,z_1) and (x_2,y_2,z_2) on a Cartesian plane.")
            print("2. Sine Law calculator.")

            try:
                self.choice = int(input())
                print()
            except ValueError:
                print("Invalid input. Please enter a number.")
                continue

            if self.choice == -1:
                print("Exiting program...")
                break

            elif self.choice == 0:
                self.quadratic_solver()

            elif self.choice == 1:
                self.distance_calculator()

            elif self.choice == 2:
                self.sine_law_calculator()

    def quadratic_solver(self):
        """Solves quadratic equations"""
        print("Chose an option:")
        print("1. Read from System.in")
        print("2. Read from FILE")

        try:
            temp_choice = int(input())
            print()
        except ValueError:
            print("Invalid input.")
            return

        if temp_choice == 1:
            # Read from user input
            try:
                print("Enter a:")
                a = float(input())
                print()

                print("Enter b:")
                b = float(input())
                print()

                print("Enter c:")
                c = float(input())
                print()

                quadratic = [a, b, c]
                roots = self.quadratic_formula(quadratic)

                # Check if there are no real roots
                if roots[0] == 0 and roots[1] == 0:
                    print(f"The quadratic equation y = {quadratic[0]}x^2 + {quadratic[1]}x + {quadratic[2]} has no real roots.")
                else:
                    print(f"The roots of the quadratic equation y = {quadratic[0]}x^2 + {quadratic[1]}x + {quadratic[2]} is {roots[0]} and {roots[1]}")
            except ValueError:
                print("Invalid input. Please enter numeric values.")

        elif temp_choice == 2:
            # Read from file
            try:
                with open("quadraticSolver.txt", "r") as file:
                    for line in file:
                        line = line.strip()
                        if not line:
                            continue

                        # Split the line by spaces
                        values = line.split()

                        if len(values) < 3:
                            print(f"Skipping invalid line: {line}")
                            continue

                        # Convert strings to floats
                        quadratic = [float(values[0]), float(values[1]), float(values[2])]

                        # Calculate roots
                        roots = self.quadratic_formula(quadratic)

                        # Check if no real roots
                        if roots[0] == 0 and roots[1] == 0:
                            print(f"The quadratic equation y = {quadratic[0]}x^2 + {quadratic[1]}x + {quadratic[2]} has no real roots.")
                        # Check if one real root (discriminant = 0)
                        elif roots[0] == roots[1]:
                            print(f"The root of the quadratic equation y = {quadratic[0]}x^2 + {quadratic[1]}x + {quadratic[2]} is {roots[0]}")
                        # Two different real roots
                        else:
                            print(f"The roots of the quadratic equation y = {quadratic[0]}x^2 + {quadratic[1]}x + {quadratic[2]} is {roots[0]} and {roots[1]}")
            except FileNotFoundError:
                print("Error: quadraticSolver.txt not found.")
            except ValueError:
                print("Error: Invalid data in file.")

    def distance_calculator(self):
        """Calculates distance between 3D points"""
        print("1. Read from System.in")
        print("2. Read from FILE")
        print()

        try:
            temp_choice = int(input())
        except ValueError:
            print("Invalid input.")
            return

        if temp_choice == 1:
            # Read from user input
            try:
                print("Label point A:")
                A = input()

                print("3D Coordinates of point A take the form of (x_A, y_A, z_A).")

                print("Enter x_A:")
                x_A = float(input())

                print("Enter y_A:")
                y_A = float(input())

                print("Enter z_A:")
                z_A = float(input())

                print("Label point B:")
                B = input()

                print("3D Coordinates of point B take the form of (x_B, y_B, z_B).")

                print("Enter x_B:")
                x_B = float(input())

                print("Enter y_B:")
                y_B = float(input())

                print("Enter z_B:")
                z_B = float(input())

                # Calculate distance
                distance = self.three_dimensional_distance_calculator(x_A, y_A, z_A, x_B, y_B, z_B)

                # Print result
                print(f"The distance between {A} ({x_A}, {y_A}, {z_A}) and {B} ({x_B}, {y_B}, {z_B}) is {distance}")
                print()
            except ValueError:
                print("Invalid input. Please enter numeric values for coordinates.")

        elif temp_choice == 2:
            # Read from file
            try:
                print("How many distances would you like to calculate?")
                number_of_distances = int(input())
                number_of_point_pairs = number_of_distances

                # Initialize arrays
                x_A = [0] * number_of_point_pairs
                y_A = [0] * number_of_point_pairs
                z_A = [0] * number_of_point_pairs

                x_B = [0] * number_of_point_pairs
                y_B = [0] * number_of_point_pairs
                z_B = [0] * number_of_point_pairs

                A = [""] * number_of_point_pairs
                B = [""] * number_of_point_pairs

                distances = [0] * number_of_distances

                with open("threeDimensionalDistanceCalculator.txt", "r") as file:
                    for x in range(number_of_point_pairs):
                        line = file.readline().strip()
                        if not line:
                            print("Error: Not enough data in file.")
                            return

                        # Split by spaces
                        data = line.split()

                        if len(data) < 9:
                            print(f"Error: Invalid data format in line {x + 1}")
                            return

                        # Parse data
                        A[x] = data[0]
                        x_A[x] = float(data[1])
                        y_A[x] = float(data[2])
                        z_A[x] = float(data[3])

                        B[x] = data[5]
                        x_B[x] = float(data[6])
                        y_B[x] = float(data[7])
                        z_B[x] = float(data[8])

                        # Calculate distance
                        distances[x] = self.three_dimensional_distance_calculator(
                            x_A[x], y_A[x], z_A[x], x_B[x], y_B[x], z_B[x]
                        )

                # Sorting options
                print("0. No sorting")
                print("1. Lowest to greatest")
                print("2. Greatest to lowest")
                temp_choice = int(input())
                print()

                if temp_choice == 1:
                    # Bubble sort - lowest to greatest
                    pass_count = number_of_point_pairs - 1
                    for y in range(pass_count):
                        for x in range(pass_count - y):
                            if distances[x] > distances[x + 1]:
                                # Swap distances
                                distances[x], distances[x + 1] = distances[x + 1], distances[x]
                                # Swap labels
                                A[x], A[x + 1] = A[x + 1], A[x]
                                B[x], B[x + 1] = B[x + 1], B[x]
                                # Swap coordinates (to maintain correspondence)
                                x_A[x], x_A[x + 1] = x_A[x + 1], x_A[x]
                                y_A[x], y_A[x + 1] = y_A[x + 1], y_A[x]
                                z_A[x], z_A[x + 1] = z_A[x + 1], z_A[x]
                                x_B[x], x_B[x + 1] = x_B[x + 1], x_B[x]
                                y_B[x], y_B[x + 1] = y_B[x + 1], y_B[x]
                                z_B[x], z_B[x + 1] = z_B[x + 1], z_B[x]

                elif temp_choice == 2:
                    # Bubble sort - greatest to lowest
                    pass_count = number_of_point_pairs - 1
                    for y in range(pass_count):
                        for x in range(pass_count - y):
                            if distances[x] < distances[x + 1]:
                                # Swap distances
                                distances[x], distances[x + 1] = distances[x + 1], distances[x]
                                # Swap labels
                                A[x], A[x + 1] = A[x + 1], A[x]
                                B[x], B[x + 1] = B[x + 1], B[x]
                                # Swap coordinates
                                x_A[x], x_A[x + 1] = x_A[x + 1], x_A[x]
                                y_A[x], y_A[x + 1] = y_A[x + 1], y_A[x]
                                z_A[x], z_A[x + 1] = z_A[x + 1], z_A[x]
                                x_B[x], x_B[x + 1] = x_B[x + 1], x_B[x]
                                y_B[x], y_B[x + 1] = y_B[x + 1], y_B[x]
                                z_B[x], z_B[x + 1] = z_B[x + 1], z_B[x]

                # Print results
                for x in range(number_of_point_pairs):
                    print(f"The distance between {A[x]} ({x_A[x]}, {y_A[x]}, {z_A[x]}) and {B[x]} ({x_B[x]}, {y_B[x]}, {z_B[x]}) is {distances[x]}")

            except FileNotFoundError:
                print("Error: threeDimensionalDistanceCalculator.txt not found.")
            except ValueError:
                print("Error: Invalid input or data format.")

    def sine_law_calculator(self):
        """Calculates unknown sides or angles using Sine Law"""
        print("0. Read from System.in")

        try:
            temp_choice = int(input())
            print()
        except ValueError:
            print("Invalid input.")
            return

        if temp_choice == 0:
            print("In any given triangle:")
            print("'a' is a side & 'A' is its corresponding angle.")
            print("'b' is a different side & 'B' is its corresponding angle.")

            print("0. Compute unknown side")
            print("1. Compute unknown angle")

            try:
                temp_choice = int(input())
                print()
            except ValueError:
                print("Invalid input.")
                return

            if temp_choice == 0:
                # Compute unknown side
                print("0. Angles measured in degrees")
                print("1. Angles measured in radians")
                angle_mode = int(input())

                print("Solving for a:")
                print()

                print("Enter A:")
                A = float(input())
                print()

                print("Enter b:")
                b = float(input())
                print()

                print("Enter B:")
                B = float(input())
                print()

                # Convert from degrees to radians if needed
                if angle_mode == 0:
                    A = math.radians(A)
                    B = math.radians(B)

                a = self.sine_law(0, 0, A, b, B)

                print(f"a has a sidelength of {a}")
                print()

            else:
                # Compute unknown angle
                print("0. Angles measured in degrees")
                print("1. Angles measured in radians")
                angle_mode = int(input())

                print("Solving for A:")
                print()

                print("Enter a:")
                a = float(input())
                print()

                print("Enter b:")
                b = float(input())
                print()

                print("Enter B:")
                B = float(input())
                print()

                # Convert from degrees to radians if needed
                if angle_mode == 0:
                    B = math.radians(B)

                A = self.sine_law(1, a, 0, b, B)

                # Convert back to degrees if needed
                if angle_mode == 0:
                    A = math.degrees(A)

                print(f"A has an angle of {A}°")
                print()

    def quadratic_formula(self, quadratic):
        """
        Computes the roots of a quadratic equation using the quadratic formula.

        Args:
            quadratic: A list of three floats [a, b, c] representing the coefficients

        Returns:
            A list of two floats containing the roots
        """
        # Calculate discriminant
        discriminant = (quadratic[1] * quadratic[1]) - (4 * quadratic[0] * quadratic[2])

        # If discriminant is negative, no real roots exist
        if discriminant < 0:
            return [0, 0]

        # Calculate roots using quadratic formula
        sqrt_discriminant = math.sqrt(discriminant)
        root1 = (-quadratic[1] + sqrt_discriminant) / (2 * quadratic[0])
        root2 = (-quadratic[1] - sqrt_discriminant) / (2 * quadratic[0])

        return [root1, root2]

    def three_dimensional_distance_calculator(self, x_A, y_A, z_A, x_B, y_B, z_B):
        """
        Calculates the distance between two three-dimensional points.

        Args:
            x_A, y_A, z_A: Coordinates of point A
            x_B, y_B, z_B: Coordinates of point B

        Returns:
            The distance between the two points
        """
        distance = math.sqrt(
            ((x_B - x_A) ** 2) +
            ((y_B - y_A) ** 2) +
            ((z_B - z_A) ** 2)
        )
        return distance

    def sine_law(self, side_or_angle, side_a, angle_a, side_b, angle_b):
        """
        Solves for a side or angle in a triangle using Sine Law.

        Sine Law: (a / sin(A)) = (b / sin(B)) = (c / sin(C))

        Args:
            side_or_angle: 0 to compute a side, 1 to compute an angle
            side_a: Length of side a
            angle_a: Angle A (in radians)
            side_b: Length of side b
            angle_b: Angle B (in radians)

        Returns:
            The computed side or angle
        """
        if side_or_angle == 0:
            # Solve for side a
            side_a = (side_b / math.sin(angle_b)) * math.sin(angle_a)
            return side_a
        elif side_or_angle == 1:
            # Solve for angle A
            angle_a = math.asin((math.sin(angle_b) / side_b) * side_a)
            return angle_a
        else:
            return 0


def main():
    """Main entry point"""
    print("=" * 60)
    print("MathSolver - Mathematical Functions Calculator")
    print("=" * 60)
    print()

    solver = MathSolver()
    solver.run()


if __name__ == "__main__":
    main()
