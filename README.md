# Rubik's Cube Visualization and Solver with Java, JOGL, and Kociemba

This project is a Java application that provides an interactive visualization of a Rubik's Cube. Users can rotate and scramble the cube. Additionally, a button allows the cube to be solved automatically using the **Kociemba Library**, which is executed via an external Python script.

## Features

- **3D Rubik's Cube Visualization** using **JOGL** (Java Binding for OpenGL).
- Interactive controls for rotating and scrambling the cube.
- Solve button to compute a solution using the **Kociemba Library**.
- Integration of Java and Python for seamless functionality.

## Requirements

### Software
- **Java JDK 21**
- **JOGL Library**
- **Python 3** with the **Kociemba package** installed (`kociemba`)

### Libraries and Dependencies
1. **JOGL**: Required for 3D graphics.
2. **Kociemba Python Package**: Used for computing the cube's solution.

## Install the Kociemba package with:
- pip install kociemba

## Setup and Installation
Clone the repository:

git clone https://github.com/Dhtrx/rubiks-cube-visualization.git
cd rubiks-cube-visualization
Install the JOGL library:

Download JOGL from the official website.
Include the JOGL JAR files in your project classpath.
Ensure Python is installed, and the kociemba package is available:

- pip install kociemba

## Compile and run the Java application:

- javac -cp .;path/to/jogl/*.jar MainController.java
- java -cp .;path/to/jogl/*.jar MainController

## How It Works
- The application uses JOGL to render a 3D Rubik's Cube.
- Users can interact with the cube to rotate or scramble it.
- Clicking the Solve button triggers a Python script that:
    - Reads the cube's current state.
    - Computes a solution using the Kociemba algorithm.
    - Returns the solution steps to the Java application.
- The solution is applied visually to the cube in the application.

## Controls
- Keys 1-3: Rotate the cube in 3D space.
- Keys 4-9: Rotate specific faces.
- Solve Button: Automatically solves the cube using the Kociemba algorithm.

## Known Issues
- Ensure Python is correctly configured in the system's PATH for the solver to work.
- The JOGL library must be compatible with your system's architecture (32-bit/64-bit).

## Future Improvements
- Support for different cube sizes (e.g., 4x4, 5x5).
- Show the current move to be performed textually. 
- Add an editor to let users put there own scrambled cubes into the program and see how they are solved.
- Replace Kociemba algorithm with an own algorithm that uses the commonly known algorithm to solve a Rubik's Cube to help users learn solving the cube on their own.

## License
This project is licensed under the MIT License. See the LICENSE file for details.
