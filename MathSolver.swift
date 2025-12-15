#!/usr/bin/env swift
//
//  MathSolver.swift
//  A collection of processes with discrete mathematical functions.
//
//  Original Author: Mohamad Chaker
//  Original Version: 2019-06-18
//  Converted to Swift: 2025-12-15
//

import Foundation

class MathSolver {
    private var choice: Int = 1
    
    func run() {
        while choice != -1 {
            print("\nNotice: This program is a collection of processes with discrete mathematical functions.")
            print("Some of the processes may read from FILE. Please read the corresponding example text file as to how to structure a FILE to its corresponding process.")
            print("\nChoose an option:")
            print("-1. Quit")
            print("0. Calculate roots of a quadratic equation")
            print("1. Calculate the distance between points (x₁,y₁,z₁) and (x₂,y₂,z₂) on a Cartesian plane.")
            print("2. Sine Law calculator.")
            
            if let input = readLine(), let choiceValue = Int(input) {
                choice = choiceValue
                print()
                
                if choice == -1 {
                    print("Exiting program...")
                    break
                } else if choice == 0 {
                    quadraticSolver()
                } else if choice == 1 {
                    distanceCalculator()
                } else if choice == 2 {
                    sineLawCalculator()
                } else {
                    print("Invalid option. Please try again.")
                }
            } else {
                print("Invalid input. Please enter a number.")
            }
        }
    }
    
    // MARK: - Quadratic Solver
    
    func quadraticSolver() {
        print("Choose an option:")
        print("1. Read from System.in")
        print("2. Read from FILE")
        
        guard let input = readLine(), let tempChoice = Int(input) else {
            print("Invalid input.")
            return
        }
        
        print()
        
        if tempChoice == 1 {
            readQuadraticFromInput()
        } else if tempChoice == 2 {
            readQuadraticFromFile()
        } else {
            print("Invalid option.")
        }
    }
    
    private func readQuadraticFromInput() {
        print("Enter a:")
        guard let aInput = readLine(), let a = Double(aInput) else {
            print("Invalid input for a.")
            return
        }
        
        print("Enter b:")
        guard let bInput = readLine(), let b = Double(bInput) else {
            print("Invalid input for b.")
            return
        }
        
        print("Enter c:")
        guard let cInput = readLine(), let c = Double(cInput) else {
            print("Invalid input for c.")
            return
        }
        
        print()
        
        let quadratic = [a, b, c]
        let roots = quadraticFormula(quadratic)
        
        printQuadraticResult(coefficients: quadratic, roots: roots)
    }
    
    private func readQuadraticFromFile() {
        let filename = "quadraticSolver.txt"
        
        guard let content = try? String(contentsOfFile: filename, encoding: .utf8) else {
            print("Error: \(filename) not found.")
            return
        }
        
        let lines = content.components(separatedBy: .newlines)
        
        for line in lines {
            let trimmedLine = line.trimmingCharacters(in: .whitespaces)
            
            if trimmedLine.isEmpty {
                continue
            }
            
            let values = trimmedLine.components(separatedBy: .whitespaces)
                .filter { !$0.isEmpty }
            
            guard values.count >= 3,
                  let a = Double(values[0]),
                  let b = Double(values[1]),
                  let c = Double(values[2]) else {
                print("Skipping invalid line: \(trimmedLine)")
                continue
            }
            
            let quadratic = [a, b, c]
            let roots = quadraticFormula(quadratic)
            
            printQuadraticResult(coefficients: quadratic, roots: roots)
        }
    }
    
    private func printQuadraticResult(coefficients: [Double], roots: [Double]) {
        let a = coefficients[0]
        let b = coefficients[1]
        let c = coefficients[2]
        
        let equation = "y = \(a)x² + \(b)x + \(c)"
        
        if roots[0] == 0 && roots[1] == 0 {
            print("The quadratic equation \(equation) has no real roots.")
        } else if roots[0] == roots[1] {
            print("The root of the quadratic equation \(equation) is \(roots[0])")
        } else {
            print("The roots of the quadratic equation \(equation) are \(roots[0]) and \(roots[1])")
I
            // Check for special mathematical constants
            checkForSpecialConstants(roots: roots)
        }
    }
    
    private func checkForSpecialConstants(roots: [Double]) {
        let phi = (1.0 + sqrt(5.0)) / 2.0  // Golden ratio
        let epsilon = 0.0000001
        
        var specialNotes: [String] = []
        
        for (index, root) in roots.enumerated() {
            let rootNumber = index + 1
            
            // Check for golden ratio
            if abs(root - phi) < epsilon {
                specialNotes.append("  ★ Root \(rootNumber) is the golden ratio φ (phi) ≈ 1.618033988749...")
            }
            
            // Check for -1/φ (reciprocal of golden ratio, negated)
            if abs(root - (-1.0/phi)) < epsilon || abs(root - (1.0 - phi)) < epsilon {
                specialNotes.append("  ★ Root \(rootNumber) is 1 - φ (also equal to -1/φ) ≈ -0.618033988749...")
            }
            
            // Check for other special values
            if abs(root - Double.pi) < epsilon {
                specialNotes.append("  ★ Root \(rootNumber) is π (pi) ≈ 3.14159265359...")
            }
            
            if abs(root - M_E) < epsilon {
                specialNotes.append("  ★ Root \(rootNumber) is e (Euler's number) ≈ 2.71828182846...")
            }
            
            if abs(root - sqrt(2.0)) < epsilon {
                specialNotes.append("  ★ Root \(rootNumber) is √2 ≈ 1.41421356237...")
            }
        }
        
        if !specialNotes.isEmpty {
            print("\n✨ Special Mathematical Constants Detected:")
            for note in specialNotes {
                print(note)
            }
            
            // Extra info for golden ratio equation
            if abs(roots[0] - phi) < epsilon || abs(roots[1] - phi) < epsilon {
                print("\n  🌟 The golden ratio appears in nature, art, and architecture!")
                print("  The equation x² - x - 1 = 0 defines the golden ratio.")
            }
        }
    }
    
    func quadraticFormula(_ coefficients: [Double]) -> [Double] {
        let a = coefficients[0]
        let b = coefficients[1]
        let c = coefficients[2]
        
        // Calculate discriminant
        let discriminant = (b * b) - (4 * a * c)
        
        // If discriminant is negative, no real roots exist
        guard discriminant >= 0 else {
            return [0, 0]
        }
        
        // Calculate roots using quadratic formula
        let sqrtDiscriminant = sqrt(discriminant)
        let root1 = (-b + sqrtDiscriminant) / (2 * a)
        let root2 = (-b - sqrtDiscriminant) / (2 * a)
        
        return [root1, root2]
    }
    
    // MARK: - Distance Calculator
    
    func distanceCalculator() {
        print("Choose an option:")
        print("1. Read from System.in")
        print("2. Read from FILE")
        print()
        
        guard let input = readLine(), let tempChoice = Int(input) else {
            print("Invalid input.")
            return
        }
        
        if tempChoice == 1 {
            readDistanceFromInput()
        } else if tempChoice == 2 {
            readDistanceFromFile()
        } else {
            print("Invalid option.")
        }
    }
    
    private func readDistanceFromInput() {
        print("Label point A:")
        guard let labelA = readLine() else { return }
        
        print("3D Coordinates of point A take the form of (x_A, y_A, z_A).")
        
        print("Enter x_A:")
        guard let xAInput = readLine(), let xA = Double(xAInput) else {
            print("Invalid input for x_A.")
            return
        }
        
        print("Enter y_A:")
        guard let yAInput = readLine(), let yA = Double(yAInput) else {
            print("Invalid input for y_A.")
            return
        }
        
        print("Enter z_A:")
        guard let zAInput = readLine(), let zA = Double(zAInput) else {
            print("Invalid input for z_A.")
            return
        }
        
        print("Label point B:")
        guard let labelB = readLine() else { return }
        
        print("3D Coordinates of point B take the form of (x_B, y_B, z_B).")
        
        print("Enter x_B:")
        guard let xBInput = readLine(), let xB = Double(xBInput) else {
            print("Invalid input for x_B.")
            return
        }
        
        print("Enter y_B:")
        guard let yBInput = readLine(), let yB = Double(yBInput) else {
            print("Invalid input for y_B.")
            return
        }
        
        print("Enter z_B:")
        guard let zBInput = readLine(), let zB = Double(zBInput) else {
            print("Invalid input for z_B.")
            return
        }
        
        let distance = threeDimensionalDistance(
            xA: xA, yA: yA, zA: zA,
            xB: xB, yB: yB, zB: zB
        )
        
        print("The distance between \(labelA) (\(xA), \(yA), \(zA)) and \(labelB) (\(xB), \(yB), \(zB)) is \(distance)")
        print()
    }
    
    private func readDistanceFromFile() {
        print("How many distances would you like to calculate?")
        
        guard let input = readLine(), let numberOfDistances = Int(input) else {
            print("Invalid input.")
            return
        }
        
        let filename = "threeDimensionalDistanceCalculator.txt"
        
        guard let content = try? String(contentsOfFile: filename, encoding: .utf8) else {
            print("Error: \(filename) not found.")
            return
        }
        
        let lines = content.components(separatedBy: .newlines)
            .map { $0.trimmingCharacters(in: .whitespaces) }
            .filter { !$0.isEmpty }
        
        guard lines.count >= numberOfDistances else {
            print("Error: Not enough data in file.")
            return
        }
        
        struct PointPair {
            let labelA: String
            let xA: Double, yA: Double, zA: Double
            let labelB: String
            let xB: Double, yB: Double, zB: Double
            var distance: Double
        }
        
        var pointPairs: [PointPair] = []
        
        for i in 0..<numberOfDistances {
            let data = lines[i].components(separatedBy: .whitespaces)
                .filter { !$0.isEmpty }
            
            guard data.count >= 9,
                  let xA = Double(data[1]),
                  let yA = Double(data[2]),
                  let zA = Double(data[3]),
                  let xB = Double(data[6]),
                  let yB = Double(data[7]),
                  let zB = Double(data[8]) else {
                print("Error: Invalid data format in line \(i + 1)")
                return
            }
            
            let distance = threeDimensionalDistance(
                xA: xA, yA: yA, zA: zA,
                xB: xB, yB: yB, zB: zB
            )
            
            let pair = PointPair(
                labelA: data[0],
                xA: xA, yA: yA, zA: zA,
                labelB: data[5],
                xB: xB, yB: yB, zB: zB,
                distance: distance
            )
            
            pointPairs.append(pair)
        }
        
        // Sorting options
        print("0. No sorting")
        print("1. Lowest to greatest")
        print("2. Greatest to lowest")
        
        if let input = readLine(), let sortChoice = Int(input) {
            print()
            
            if sortChoice == 1 {
                pointPairs.sort { $0.distance < $1.distance }
            } else if sortChoice == 2 {
                pointPairs.sort { $0.distance > $1.distance }
            }
        }
        
        // Print results
        for pair in pointPairs {
            print("The distance between \(pair.labelA) (\(pair.xA), \(pair.yA), \(pair.zA)) and \(pair.labelB) (\(pair.xB), \(pair.yB), \(pair.zB)) is \(pair.distance)")
        }
    }
    
    func threeDimensionalDistance(xA: Double, yA: Double, zA: Double,
                                   xB: Double, yB: Double, zB: Double) -> Double {
        let dx = xB - xA
        let dy = yB - yA
        let dz = zB - zA
        
        return sqrt(dx * dx + dy * dy + dz * dz)
    }
    
    // MARK: - Sine Law Calculator
    
    func sineLawCalculator() {
        print("0. Read from System.in")
        
        guard let input = readLine(), let tempChoice = Int(input) else {
            print("Invalid input.")
            return
        }
        
        print()
        
        if tempChoice == 0 {
            print("In any given triangle:")
            print("'a' is a side & 'A' is its corresponding angle.")
            print("'b' is a different side & 'B' is its corresponding angle.")
            print()
            print("0. Compute unknown side")
            print("1. Compute unknown angle")
            
            guard let computeInput = readLine(), let computeChoice = Int(computeInput) else {
                print("Invalid input.")
                return
            }
            
            print()
            
            if computeChoice == 0 {
                computeUnknownSide()
            } else if computeChoice == 1 {
                computeUnknownAngle()
            } else {
                print("Invalid option.")
            }
        } else {
            print("Invalid option.")
        }
    }
    
    private func computeUnknownSide() {
        print("0. Angles measured in degrees")
        print("1. Angles measured in radians")
        
        guard let input = readLine(), let angleMode = Int(input) else {
            print("Invalid input.")
            return
        }
        
        print("Solving for a:")
        print()
        
        print("Enter A:")
        guard let AInput = readLine(), var angleA = Double(AInput) else {
            print("Invalid input.")
            return
        }
        print()
        
        print("Enter b:")
        guard let bInput = readLine(), let sideB = Double(bInput) else {
            print("Invalid input.")
            return
        }
        print()
        
        print("Enter B:")
        guard let BInput = readLine(), var angleB = Double(BInput) else {
            print("Invalid input.")
            return
        }
        print()
        
        // Convert from degrees to radians if needed
        if angleMode == 0 {
            angleA = angleA * .pi / 180.0
            angleB = angleB * .pi / 180.0
        }
        
        let sideA = sineLaw(sideOrAngle: .side, sideA: 0, angleA: angleA, sideB: sideB, angleB: angleB)
        
        print("a has a side length of \(sideA)")
        print()
    }
    
    private func computeUnknownAngle() {
        print("0. Angles measured in degrees")
        print("1. Angles measured in radians")
        
        guard let input = readLine(), let angleMode = Int(input) else {
            print("Invalid input.")
            return
        }
        
        print("Solving for A:")
        print()
        
        print("Enter a:")
        guard let aInput = readLine(), let sideA = Double(aInput) else {
            print("Invalid input.")
            return
        }
        print()
        
        print("Enter b:")
        guard let bInput = readLine(), let sideB = Double(bInput) else {
            print("Invalid input.")
            return
        }
        print()
        
        print("Enter B:")
        guard let BInput = readLine(), var angleB = Double(BInput) else {
            print("Invalid input.")
            return
        }
        print()
        
        // Convert from degrees to radians if needed
        if angleMode == 0 {
            angleB = angleB * .pi / 180.0
        }
        
        var angleA = sineLaw(sideOrAngle: .angle, sideA: sideA, angleA: 0, sideB: sideB, angleB: angleB)
        
        // Convert back to degrees if needed
        if angleMode == 0 {
            angleA = angleA * 180.0 / .pi
            print("A has an angle of \(angleA)°")
        } else {
            print("A has an angle of \(angleA) radians")
        }
        print()
    }
    
    enum SineLawCompute {
        case side
        case angle
    }
    
    func sineLaw(sideOrAngle: SineLawCompute, sideA: Double, angleA: Double,
                 sideB: Double, angleB: Double) -> Double {
        switch sideOrAngle {
        case .side:
            // Solve for side a: a = (b / sin(B)) * sin(A)
            return (sideB / sin(angleB)) * sin(angleA)
            
        case .angle:
            // Solve for angle A: A = arcsin((sin(B) / b) * a)
            return asin((sin(angleB) / sideB) * sideA)
        }
    }
}

// MARK: - Main Entry Point

print(String(repeating: "=", count: 60))
print("MathSolver - Mathematical Functions Calculator")
print(String(repeating: "=", count: 60))
print()

let solver = MathSolver()
solver.run()
