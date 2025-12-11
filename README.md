# MathSolver - Assortment of Math Programs

A collection of mathematical calculators converted from Java to Python.

## Project Information

- **Title**: Assortment of Math Programs
- **Purpose**: To learn mathematical programming concepts
- **Original Version**: Java (2019-06-18)
- **Python Conversion**: 2025-12-11
- **Author**: Mohamad Chaker

## Features

This program includes three mathematical calculators:

1. **Quadratic Equation Solver** - Calculates the roots of quadratic equations
2. **3D Distance Calculator** - Calculates distances between points in 3D space
3. **Sine Law Calculator** - Solves for unknown sides or angles in triangles

## How to Run

### Requirements
- Python 3.x

### Starting the Program

```bash
python3 math_solver.py
```

Or simply:

```bash
python math_solver.py
```

## Usage Instructions

When you run the program, you'll be presented with a menu:

```
-1. Quit
0. Calculate roots of a quadratic equation
1. Calculate the distance between points (x_1,y_1,z_1) and (x_2,y_2,z_2) on a Cartesian plane
2. Sine Law calculator
```

### Option 0: Quadratic Equation Solver

Solves equations in the form: y = ax² + bx + c

- **Input from keyboard**: Enter coefficients a, b, and c when prompted
- **Input from file**: Reads from `quadraticSolver.txt`
  - Format: Each line should contain three space-separated numbers (a b c)
  - Example: `1 -5 6` (solves x² - 5x + 6 = 0)

### Option 1: 3D Distance Calculator

Calculates the distance between two points in 3D space using the distance formula.

- **Input from keyboard**: Enter coordinates for points A and B
- **Input from file**: Reads from `threeDimensionalDistanceCalculator.txt`
  - Format: `LabelA x_A y_A z_A to LabelB x_B y_B z_B`
  - Example: `A 1 2 3 to B 4 5 6`
  - Supports sorting results (lowest to greatest or greatest to lowest)

### Option 2: Sine Law Calculator

Calculates unknown sides or angles in triangles using the Sine Law: a/sin(A) = b/sin(B)

- Choose whether to compute an unknown side or angle
- Select angle measurement (degrees or radians)
- Enter the known values when prompted

## Input Files

The program can read from text files for batch processing:

- `quadraticSolver.txt` - For quadratic equations
- `threeDimensionalDistanceCalculator.txt` - For 3D distance calculations
- `quadraticSolverExample.txt` - Example file format
- `threeDimensionalDistanceCalculatorExample.txt` - Example file format

For detailed instructions on file formats, see the `Manual.docx` file.

## Files in This Repository

- `math_solver.py` - Main Python program
- `MathSolver.java` - Original Java version (preserved)
- `quadraticSolver.txt` - Sample input for quadratic solver
- `threeDimensionalDistanceCalculator.txt` - Sample input for distance calculator
- `Manual.docx` - Detailed manual
- `Progress.txt` - Development progress notes
- `README.TXT` - Original README for Java version
- `README.md` - This file (Python version README)

## Notes

- The program runs in a loop until you choose option -1 to quit
- Input validation is included for numeric inputs
- File reading features allow batch processing of multiple calculations
- The 3D distance calculator can also work as a 2D calculator (set z values to 0) or even a simple subtractor (set y and z values to 0)
